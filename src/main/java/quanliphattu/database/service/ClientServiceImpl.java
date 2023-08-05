package quanliphattu.database.service;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quanliphattu.database.Repository.phattuRepo;
import quanliphattu.database.dto.DataMailDTO;
import quanliphattu.database.models.phattu;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private MailService mailService;
    @Autowired
    private phattuRepo ptr;

    @Override
    public Boolean create(String emailAdress) throws MessagingException {
        phattu pt = new phattu();
        List<phattu> ptlist = ptr.findAll();
        for (phattu i : ptlist) {
            if (i.getEmail().equals(emailAdress)) {
                DataMailDTO dataMail = new DataMailDTO();
                dataMail.setTo(emailAdress);
                dataMail.setSubject("PHỤC HỒI MẬT KHẨU");
                Map<String, Object> props = new HashMap<>();
                props.put("name", i.getPhapdanh());
                props.put("username", i.getEmail());
                props.put("password", i.getMatkhaugoc());
                dataMail.setProps(props);
                mailService.sendHtmlMail(dataMail, "client");
                return true;

            }

    }
        return false;
}


}





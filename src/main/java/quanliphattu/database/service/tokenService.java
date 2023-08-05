package quanliphattu.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import quanliphattu.database.Repository.phattuRepo;
import quanliphattu.database.Repository.tokenRepo;
import quanliphattu.database.models.phattu;
import quanliphattu.database.models.token;

import java.util.List;

@Service
public class tokenService implements ItokenService {
    @Autowired
    private phattuRepo ptr;
    @Autowired
    private tokenRepo tkr;
    @Override
    public token getToken(String gmail) {
        List<phattu> phattuList = ptr.findAll();
        List<token> tokenList = tkr.findAll();
        int phattuid = 0;
        for(phattu i:phattuList)
        {
            if(i.getEmail().equals(gmail))
            {
                phattuid = i.getPhattuid();
                break;
            }
        }
        token tk = new token();
        for(token i: tokenList)
        {
            if(i.getPhattuid() == phattuid)
            {
                tk = i;
            }
        }
        return tk;
    }
}

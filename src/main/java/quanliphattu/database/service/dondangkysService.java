package quanliphattu.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import quanliphattu.database.Repository.daotrangRepo;
import quanliphattu.database.Repository.dondangkiRepo;
import quanliphattu.database.Repository.phattuRepo;
import quanliphattu.database.models.daotrangs;
import quanliphattu.database.models.dondangkys;
import quanliphattu.database.models.phattu;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class dondangkysService implements Idondangki {
    @Autowired
    private dondangkiRepo ddkr;
    @Autowired
    private daotrangRepo dtr;
    @Autowired
    private phattuRepo ptr;

    @Override
    public String themdondangki(dondangkys dondangkythem) {
        int userid = 0;
        phattu phattu = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                String username = userDetails.getUsername();
                List<phattu> phattuList = ptr.findAll();
                for (phattu i : phattuList) {
                    if (i.getEmail().equals(username)) {
                        userid = i.getPhattuid();
                        phattu = i;
                    }
                }
            }
        }
            dondangkythem.setTrangthaidon(0);
            Calendar calendar = Calendar.getInstance();
            Date date = calendar.getTime();
            dondangkythem.setNgayguidon(date);
            dondangkythem.setPhattu(phattu);
            ddkr.save(dondangkythem);
            return "thêm thành công, đang đợi duyệt";

        }

    @Override
    public String xoadondangki(int dondangkiid) {
        int dem = 0;
        Optional<dondangkys> dondangkysOptional = ddkr.findById(dondangkiid);
        dondangkys dondangkys = dondangkysOptional.get();
        List<daotrangs> daotrangsList = dtr.findAll();
        for (daotrangs i : daotrangsList) {
            if (dondangkys.getDaotrangs().getDaotrangid() == i.getDaotrangid()) {
                if(dondangkys.getTrangthaidon() == 1)
                {
                    Optional<daotrangs> daotrangsOptional = dtr.findById(dondangkys.getDaotrangs().getDaotrangid());
                    daotrangs daotrangs = daotrangsOptional.get();
                    daotrangs.setSothanhvienthamgia(daotrangs.getSothanhvienthamgia() - 1);
                    dtr.save(daotrangs);
                }

            }
        }
        ddkr.deleteById(dondangkiid);

        return "xóa thành công";
    }

    @Override
    public String duyetdon(int donid, boolean xacnhanduyet) {
        int adminid = 0;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                String username = userDetails.getUsername();
                List<phattu> phattuList = ptr.findAll();
                for (phattu i : phattuList) {
                    if (i.getEmail().equals(username)) {
                        adminid = i.getPhattuid();
                    }
                }
            }
        }
            int dem = 0;
            Optional<dondangkys> dondangkysOptional = ddkr.findById(donid);
            if (dondangkysOptional.isPresent()) {
                dondangkys dondangkys = dondangkysOptional.get();
                dondangkys.setTrangthaidon(xacnhanduyet == true ? 1 : 0);
                dondangkys.setNguoixuly(adminid);
                Calendar calendar = Calendar.getInstance();
                Date date = calendar.getTime();
                dondangkys.setNgayxuly(date);
                if (dondangkys.getTrangthaidon() == 1) {
                    System.out.println(dondangkys.getDaotrangs().getDaotrangid());
                    List<dondangkys> dondangkysList = ddkr.findAll();
                    List<daotrangs> daotrangsList = dtr.findAll();
                    for (dondangkys i : dondangkysList) {
                        for (daotrangs j : daotrangsList) {
                            if (i.getDaotrangs().getDaotrangid() == j.getDaotrangid()) {
                                if (dondangkys.getDaotrangs().getDaotrangid() == j.getDaotrangid()) {
                                    if(i.getTrangthaidon() == 1)
                                    {
                                        dem += 1;
                                    }
                                }
                            }
                        }
                    }
                    Optional<daotrangs> daotrangsOptional = dtr.findById(dondangkys.getDaotrangs().getDaotrangid());
                    daotrangs daotrangs = daotrangsOptional.get();
                    daotrangs.setSothanhvienthamgia(dem);
                    dtr.save(daotrangs);
                    return "Đã duyệt đơn";
                } else {
                    return "Đã không duyệt đơn đăng kí này";
                }
            }
        return "Đơn đăng kí không tồn tại";
        }

    }

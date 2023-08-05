package quanliphattu.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import quanliphattu.database.Repository.kieuthanhvienRepo;
import quanliphattu.database.Repository.phattuRepo;
import quanliphattu.database.models.kieuthanhviens;
import quanliphattu.database.models.phattu;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class phattuService implements Iphattu{
    @Autowired
    private phattuRepo ptr;
    @Autowired
    private kieuthanhvienRepo ktr;
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @Override
    public String addphattu(phattu pt) {
        kieuthanhviens kieuthanhviens = new kieuthanhviens();
        Optional<kieuthanhviens> kieuthanhviensOptional = ktr.findById(2);
        kieuthanhviens = kieuthanhviensOptional.get();

        List<phattu> phattuList = ptr.findAll();
        if(phattuList.size() >0 )
        {
            for(phattu i:phattuList) {
                if (i.getEmail().equals(pt.getEmail())) {
                    return "thêm thất bại tài khoản đã tồn tại";
                }
            }
            pt.setActive(true);
            pt.setKieuthanhviens(kieuthanhviens);
            pt.setMatkhaugoc(pt.getPassword());
            pt.setPassword(new BCryptPasswordEncoder().encode(pt.getPassword()));
            ptr.save(pt);
            return "thêm phật tử thành công";
        }
        else{
            pt.setActive(true);
            pt.setKieuthanhviens(kieuthanhviens);
            pt.setMatkhaugoc(pt.getPassword());
            pt.setPassword(new BCryptPasswordEncoder().encode(pt.getPassword()));
            ptr.save(pt);
            return "thêm phật tử thành công";
        }

    }

    @Override
    public List<phattu> showphattu() {
        List<phattu> phattuList = new ArrayList<>();
        List<phattu> ptl = ptr.findAll();
        for(phattu i:ptl)
        {
            if(i.isActive() == true)
            {
                phattuList.add(i);
            }
        }
        return phattuList;
    }

    @Override
    public String xoaphattu(int id) {
        List<phattu> phattuList = ptr.findAll();
        if(phattuList.size() > 0)
        {
            for(phattu i:phattuList)
            {
                if(i.getPhattuid() == id)
                {
                    i.setActive(false);
                    ptr.save(i);
                }
            }
            return "xóa phật tử thành công";
        }
        return "xóa phật tử không thành công";

    }

    @Override
    public String suaphattu(phattu ptsua) {
        List<phattu> phattuList = ptr.findAll();
        Optional<phattu> phattuOptional = ptr.findById(ptsua.getPhattuid());
        phattu ptgoc = phattuOptional.get();
        for(phattu i:phattuList)
        {
            if(i.getPhattuid() == ptsua.getPhattuid())
            {
                System.out.println("ok");
                if(i.isActive() == true)
                {
                    i = ptsua;
                    i.setMatkhaugoc(ptgoc.getMatkhaugoc());
                    i.setPassword(ptgoc.getPassword());
                    kieuthanhviens kieuthanhviens = new kieuthanhviens();
                    Optional<kieuthanhviens> kieuthanhviensOptional = ktr.findById(2);
                    kieuthanhviens = kieuthanhviensOptional.get();
                    i.setKieuthanhviens(kieuthanhviens);
                    i.setActive(true);
                    ptr.save(i);
                    return "sửa thành công";
                }
            }
        }
    return "sửa thất bại";
    }

    @Override
    public boolean ChangePass(String email,String password) {
       List<phattu> ptlist = ptr.findAll();
       for(phattu i:ptlist)
       {
           if(i.getEmail().equals(email))
           {
               if(i.isActive() == true)
               {
                   i.setMatkhaugoc(password);
                   i.setPassword(new BCryptPasswordEncoder().encode(password));
                   return true;
               }

           }
       }
       return false;
    }



    @Override
    public List<phattu> getphattu(int page, int pageSize) {

        Pageable pageable = PageRequest.of(page-1,pageSize);
        Page<phattu> ppt = ptr.findAll(pageable);
        List<phattu> ptlist = ppt.getContent();
        List<phattu> lptnew = new ArrayList<>();
        for(phattu i:ptlist) {
            if(i.isActive() == true)
            {
            lptnew.add(i);
        }
        }
        List<phattu> lpt = new ArrayList<>();
        lpt = lptnew;
        lpt.sort(Comparator.comparing(phattu::getTen));
        return lpt;
    }
    @Override
    public List<phattu> getphattuTheoTen (int page, int pageSize) {
        Pageable pageable = PageRequest.of(page-1,pageSize);
        Page<phattu> ppt = ptr.findAll(pageable);
        List<phattu> ptlist = ppt.getContent();
        List<phattu> lptnew = new ArrayList<>();
        for(phattu i:ptlist)
        {
            if(i.isActive() == true)
            {
                lptnew.add(i);
            }
        }
        List<phattu> lpt = new ArrayList<>(lptnew);
        lpt.sort(Comparator.comparing(phattu::getTen));
        return lpt;
    }

    @Override
    public List<phattu> getphattuTheoPhapDanh(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page -1,pageSize);
        Page<phattu> ppt = ptr.findAll(pageable);
        List<phattu> ptlist = ppt.getContent();
        List<phattu> lptnew = new ArrayList<>();
        for(phattu i:ptlist)
        {
            if(i.isActive() == true)
            {
                lptnew.add(i);
            }
        }
        List<phattu> lpt = new ArrayList<>(lptnew);
        lpt.sort(Comparator.comparing(phattu::getPhapdanh));
        return lpt;
    }

    @Override
    public List<phattu> getphattuTheoGioiTinh(int page,int pageSize,int gioitinh) {
        Pageable pageable = PageRequest.of(page-1,pageSize);
        List<phattu> ppt = ptr.findAll();

        List<phattu> ptlist = ppt.stream().filter(phattu -> phattu.getGioitinh() == gioitinh).collect(Collectors.toList());
        List<phattu> lpt = new ArrayList<>();
        try{
            int start = (int) pageable.getOffset();
            System.out.println(start);
            int end = Math.min((start + pageable.getPageSize()), ptlist.size());
            System.out.println(end);
            int pages = pageable.getPageSize();
            System.out.println(pages);
            List<phattu> paginatedList = ptlist.subList(start, end);
            return paginatedList;
        }
        catch (IllegalArgumentException e)
        {
            return lpt;
        }
    }

    @Override
    public List<phattu> getphattuTheoChua(int page, int pageSize, int chuaid) {
        Pageable pageable = PageRequest.of(page-1,pageSize);
        List<phattu> ptlist = ptr.findAll();
        List<phattu> ptl = ptlist.stream().filter(phattu -> phattu.getChuaid() == chuaid).collect(Collectors.toList());
        List<phattu> ptrong = new ArrayList<>();
        try{
            int start = (int) pageable.getOffset();
            int end = Math.min(start + pageable.getPageSize(),ptl.size());
            List<phattu> lphattu = ptl.subList(start,end);
            return lphattu;
        }
        catch (IllegalArgumentException e)
        {
            return ptrong;
        }

    }

    @Override
    public List<phattu> getphattuTheoHoanTuc(int page, int pageSize, boolean hoantuc) {
        Pageable pageable = PageRequest.of(page -1,pageSize);
        List<phattu> lpt = ptr.findAll();
        List<phattu> listrong = new ArrayList<>();
        List<phattu> listphattu = lpt.stream().filter(phattu -> phattu.isDahoantuc() == hoantuc).collect(Collectors.toList());
        try{
            int start = (int) pageable.getOffset();
            int end  = Math.min(start + pageable.getPageSize(),listphattu.size());
            List<phattu> ptlist = listphattu.subList(start,end);
            return ptlist;
        }
        catch (IllegalArgumentException e)
        {
            return listrong;
        }

    }

    @Override
    public List<phattu> getphattuTheoKieuThanhVien(int page, int pageSize, int kieuthanhvienid) {
        Pageable pageable = PageRequest.of(page-1,pageSize);
        List<phattu> lpt = ptr.findAll();
        List<phattu> listpt = lpt.stream().filter(phattu -> phattu.getKieuthanhvienid() == kieuthanhvienid).collect(Collectors.toList());
        try{
            int start = (int) pageable.getOffset();
            int end = Math.min(start + pageable.getPageSize(),listpt.size());
            return listpt.subList(start,end);
        }
        catch(IllegalArgumentException e)
        {
            List<phattu> lrong = new ArrayList<>();
            return lrong;
        }
    }
}

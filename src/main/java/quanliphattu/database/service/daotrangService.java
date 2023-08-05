package quanliphattu.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import quanliphattu.database.Repository.daotrangRepo;
import quanliphattu.database.Repository.dondangkiRepo;
import quanliphattu.database.models.daotrangs;
import quanliphattu.database.models.dondangkys;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class daotrangService implements Idaotrangs {
    @Autowired
    private daotrangRepo dtr;
    @Autowired
    private dondangkiRepo dkr;
    @Override
    public String adddaotrang(daotrangs daotrangadd) {
        Calendar calendar = Calendar.getInstance();
        daotrangadd.setSothanhvienthamgia(0);
        daotrangadd.setNguoitrutri(1);
        dtr.save(daotrangadd);
        return "Thêm thành công";
    }

    @Override
    public String xoadaotrangs(int daotrangid) {
        List<dondangkys> dondangkysList = dkr.findAll();
        List<daotrangs> daotrangsList = dtr.findAll();
        for(dondangkys i:dondangkysList)
        {
            if(i.getDaotrangid() == null)
            {
                continue;
            }
            if(i.getDaotrangid() == daotrangid)
            {
                i.setDaotrangs(null);
            }
        }
        for(daotrangs i:daotrangsList)
        {
            if(i.getDaotrangid() == daotrangid)
            {
                dtr.deleteById(daotrangid);
                return "xóa thành công";
            }
        }
        return "không tìm thấy id";

    }

    @Override
    public String suadaotrangs(daotrangs suadaotrang) {
        List<daotrangs> daotrangsList = dtr.findAll();
        for(daotrangs i:daotrangsList)
        {
            if(i.getDaotrangid() == suadaotrang.getDaotrangid())
            {
                dtr.save(suadaotrang);
                return "sửa thành công";
            }
        }
        return "Sửa thất bại";
    }

    @Override
    public List<daotrangs> hienthidaotrang(int page,int pageSize) {
        Pageable pageable = PageRequest.of(page-1, pageSize);
        Page<daotrangs> daotrangsPage = dtr.findAll(pageable);
        List<daotrangs> daotrangsList = daotrangsPage.getContent();
        return daotrangsList;
    }

    @Override
    public List<daotrangs> locdaotrangtheodiachi(String diachi, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page-1,pageSize);
        List<daotrangs> daotrangsList = dtr.findAll();
        List<daotrangs> daotrangsList1 = new ArrayList<>();
        for(daotrangs i:daotrangsList)
        {
            if(i.getNoitochuc().toLowerCase().contains(diachi.toLowerCase()))
            {
                daotrangsList1.add(i);
            }
        }
        try{
            int start = (int) pageable.getOffset();
            int end = Math.min(start + pageable.getPageSize(),daotrangsList1.size());
            List<daotrangs> daotrangsList2 = daotrangsList1.subList(start,end);
            return daotrangsList2;
        }
        catch(IllegalArgumentException e)
        {
            List<daotrangs> lrong = new ArrayList<>();
            return lrong;
        }
    }

    @Override
    public List<daotrangs> locdaotrangtheotrangthai(boolean trangthai, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page-1,pageSize);
        List<daotrangs> daotrangsList = dtr.findAll();
        List<daotrangs> daotrangsList1 = new ArrayList<>();
        for(daotrangs i:daotrangsList)
        {
            if(i.isDaketthuc() == trangthai)
            {
                daotrangsList1.add(i);
            }
        }
        try{
            int start = (int) pageable.getOffset();
            int end = Math.min(start + pageable.getPageSize(),daotrangsList1.size());
            List<daotrangs> daotrangsList2 = daotrangsList1.subList(start,end);
            return daotrangsList2;
        }
        catch(IllegalArgumentException e)
        {
            List<daotrangs> lrong = new ArrayList<>();
            return lrong;
        }
    }

}

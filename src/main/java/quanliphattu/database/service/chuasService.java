package quanliphattu.database.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import quanliphattu.database.Repository.chuaRepo;
import quanliphattu.database.Repository.phattuRepo;
import quanliphattu.database.models.chuas;
import quanliphattu.database.models.phattu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class chuasService implements IchuaService {
    @Autowired
    private chuaRepo crp;
    @Autowired
    private phattuRepo ptr;
    @Override
    public String addchua(chuas chua) {

        List<chuas> chuasList = crp.findAll();
        for(chuas i:chuasList)
        {
            if(i.getTenchua().equals(chua.getTenchua()))
            {
                return "chùa đã tồn tại";
            }
        }
        crp.save(chua);
        return "Thêm chùa thành công";
    }

    @Override
    public String xoachua(int idchua) {
        List<chuas> chuasList = crp.findAll();
        List<phattu> phattuList = ptr.findAll();
        for(phattu i:phattuList)
        {
            if(i.getChuaid() != null)
            {
                if(i.getChuaid() == idchua)
                {

                    i.setChuas(null);
                    ptr.save(i);
                }
            }
        }
        for(chuas chua:chuasList)
        {
            if(chua.getChuaid() == idchua)
            {
                crp.deleteById(idchua);
            }
        }

            return "Xóa thành công";
    }

    @Override
    public String suachua(chuas chuasua) {
     List<chuas> chuasList = crp.findAll();
     for(chuas i:chuasList)
     {
         if(i.getChuaid() == chuasua.getChuaid())
         {
            crp.save(chuasua);
            return "sửa chùa thành công";
         }
     }
            return "Sửa chùa thất bại";
    }

    @Override
    public List<chuas> hienthichua() {
        List<chuas> chuasList = crp.findAll();
        return chuasList;
    }


    @Override
    public List<chuas> phantrangchua(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page-1, pageSize);
        Page<chuas> chuasPage=  crp.findAll(pageable);
        List<chuas> chuasList = chuasPage.getContent();
        return chuasList;
    }

    @Override
    public List<chuas> phantrangtheodiachi(String diachi, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page-1,pageSize);
        List<chuas> chuasList = crp.findAll();
        List<chuas> diachiList = new ArrayList<>();
        List<chuas> lrong = new ArrayList<>();
        for(chuas i:chuasList)
        {
            if(i.getDiachi().toLowerCase().contains(diachi.toLowerCase()))
            {
                diachiList.add(i);
            }
        }
        try{
            int start = (int) pageable.getOffset();
            int end = Math.min(start + pageable.getPageSize(),diachiList.size());
            List<chuas> chuasList1 = diachiList.subList(start,end);
            return chuasList1;
        }catch (IllegalArgumentException e)
        {
            return lrong;
        }

    }

    @Override
    public List<chuas> loctheotenchua(String tenchua, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page-1,pageSize);
        List<chuas> chuasList = crp.findAll();
        List<chuas> tenchuaList = new ArrayList<>();
        for(chuas i:chuasList)
        {
            if(i.getTenchua().toLowerCase().contains(tenchua.toLowerCase()))
            {
                tenchuaList.add(i);
            }
        }
        try{
            int start = (int) pageable.getOffset();
            int end = Math.min(start + pageable.getPageSize(),tenchuaList.size());
            List<chuas> tenchuaphantrang = tenchuaList.subList(start,end);
            return tenchuaphantrang;
        }
        catch (IllegalArgumentException e)
        {
            List<chuas> lrong = new ArrayList<>();
            return lrong;
        }


    }

    @Override
    public List<chuas> loctheosotrutri(int trutri, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page-1,pageSize);
        List<chuas> chuasList = crp.findAll();
        List<chuas> trutriList = new ArrayList<>();
        for(chuas i:chuasList)
        {
            if(i.getTrutri() == trutri)
            {
                trutriList.add(i);
            }
        }
        try {
            int start = (int) pageable.getOffset();
            int end = Math.min(start + pageable.getPageSize(),trutriList.size());
            List<chuas> chuasList1 = trutriList.subList(start,end);
            return chuasList1;
        }
        catch(IllegalArgumentException e)
        {
            List<chuas> lrong = new ArrayList<>();
            return lrong;
        }
    }
}

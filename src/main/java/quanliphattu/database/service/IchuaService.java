package quanliphattu.database.service;

import quanliphattu.database.models.chuas;

import java.util.List;

public interface IchuaService {
    public String addchua(chuas chua);
    public String xoachua(int idchua);
    public String suachua(chuas chuasua);
    public List<chuas> hienthichua ();
    public List<chuas> phantrangchua(int page,int pageSize);
    public List<chuas> phantrangtheodiachi(String diachi,int page,int pageSize);
    public List<chuas> loctheotenchua(String tenchua,int page, int pageSize);
    public List<chuas> loctheosotrutri(int trutri,int page,int pageSize);
}

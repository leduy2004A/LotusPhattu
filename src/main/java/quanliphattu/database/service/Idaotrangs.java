package quanliphattu.database.service;

import quanliphattu.database.models.daotrangs;

import java.util.List;

public interface Idaotrangs {
    public String adddaotrang(daotrangs daotrangadd);
    public String xoadaotrangs(int daotrangid);
    public String suadaotrangs(daotrangs suadaotrang);
    public List<daotrangs> hienthidaotrang(int page,int pageSize);
    public List<daotrangs> locdaotrangtheodiachi(String diachi,int page,int pageSize);
    public List<daotrangs> locdaotrangtheotrangthai(boolean trangthai,int page, int pageSize);
}

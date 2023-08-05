package quanliphattu.database.service;


import quanliphattu.database.models.phattu;

import java.util.List;

public interface Iphattu {
    public String addphattu(phattu pt);
    public List<phattu> showphattu();
    public String xoaphattu(int id);
    public String suaphattu(phattu ptsua);
    public boolean ChangePass(String email,String password);
    public List<phattu> getphattu(int page,int pageSize);
    public List<phattu> getphattuTheoTen(int page,int pageSize);
    public List<phattu> getphattuTheoPhapDanh(int page,int pageSize);
    public List<phattu> getphattuTheoGioiTinh(int page, int pageSize,int gioitinh);
    public List<phattu> getphattuTheoChua(int page,int pageSize,int gioitinh);
    public List<phattu> getphattuTheoHoanTuc(int page,int pageSize,boolean hoantuc);
    public List<phattu> getphattuTheoKieuThanhVien(int page,int pageSize,int kieuthanhvienid);
}

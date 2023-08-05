package quanliphattu.database.service;

import quanliphattu.database.models.dondangkys;

public interface Idondangki {
    public String themdondangki(dondangkys dondangkythem);
    public String xoadondangki(int dondangkiid);
    public String duyetdon(int donid,boolean xacnhanduyet);
}

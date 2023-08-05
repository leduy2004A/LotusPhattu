package quanliphattu.database.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import quanliphattu.database.models.daotrangs;
import quanliphattu.database.service.daotrangService;

import java.util.List;

@RestController
@RequestMapping(value = "api/vers1")
public class daotrangsController {
    @Autowired
    private daotrangService dts;
    @RequestMapping(value = "adddaotrang",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public String adddaotrang(@RequestBody String daotrangadd)
    {
        Gson gson = new Gson();
        daotrangs daotrang = gson.fromJson(daotrangadd,daotrangs.class);
        return dts.adddaotrang(daotrang);
    }
    @DeleteMapping("xoadaotrang")
    public String xoadaotrang(@RequestParam int daotrangid)
    {
        return dts.xoadaotrangs(daotrangid);
    }
    @RequestMapping(value = "suadaotrang",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    public String suadaotrang(@RequestBody String suadaotrang)
    {
        Gson gson = new Gson();
        daotrangs daotrang = gson.fromJson(suadaotrang, daotrangs.class);
        return dts.suadaotrangs(daotrang);
    }
    @GetMapping("hienthidaotrang")
    public List<daotrangs> hienthidaotrang(@RequestParam int page,@RequestParam int pageSize)
    {
        return dts.hienthidaotrang(page, pageSize);
    }
    @GetMapping("locdaotrangtheodiachi")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<daotrangs> locdaotrangtheodiachi(@RequestParam String diachi,@RequestParam int page,@RequestParam int pageSize)
    {
        return dts.locdaotrangtheodiachi(diachi, page, pageSize);
    }
    @GetMapping("locdaotrangtheotrangthai")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<daotrangs> locdaotrangtheotrangthai(@RequestParam boolean trangthai,@RequestParam int page,@RequestParam int pageSize)
    {
        return dts.locdaotrangtheotrangthai(trangthai, page, pageSize);
    }
}

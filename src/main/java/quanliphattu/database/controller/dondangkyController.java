package quanliphattu.database.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import quanliphattu.database.models.dondangkys;
import quanliphattu.database.service.dondangkysService;

@RestController
@RequestMapping("api/vers1")
public class dondangkyController {
    @Autowired
    private dondangkysService ddks;
    @RequestMapping(value = "themdondangki",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public String themdondangki(@RequestBody String dondangkiadd)
    {
        Gson gson = new Gson();
        dondangkys dondangkys = gson.fromJson(dondangkiadd, dondangkys.class);
        return ddks.themdondangki(dondangkys);
    }
    @DeleteMapping("xoadondangki")
    public String xoadondangki(@RequestParam int dondangkiid)
    {
        return ddks.xoadondangki(dondangkiid);
    }
    @PutMapping("duyetdon")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String duyetdon(@RequestParam int donid,@RequestParam boolean xacnhandon)
    {
        return ddks.duyetdon(donid, xacnhandon);
    }
}

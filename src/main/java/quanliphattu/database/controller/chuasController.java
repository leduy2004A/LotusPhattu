package quanliphattu.database.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import quanliphattu.database.models.chuas;
import quanliphattu.database.service.chuasService;

import java.util.List;

@RestController
@CrossOrigin(value = "*",allowedHeaders = "*")
@RequestMapping(value = "api/vers1")
public class chuasController {
    @Autowired
    private chuasService csv;
    @RequestMapping(value = "themchua",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public String addchua(@RequestBody String chuaadd)
    {
        Gson gson = new Gson();
        chuas  chua = gson.fromJson(chuaadd,chuas.class);
        return csv.addchua(chua);
    }
    @DeleteMapping(value = "xoachua")
    public String xoachua(@RequestParam int chuaid)
    {
        return csv.xoachua(chuaid);
    }
    @RequestMapping(value = "suachua",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    public String suachua(@RequestBody String chuasua)
    {
        Gson gson = new Gson();
        chuas chua = gson.fromJson(chuasua,chuas.class);
        return csv.suachua(chua);
    }
    @GetMapping("phantrangchua")
    public List<chuas> phantrangchua(@RequestParam int page,@RequestParam int pageSize)
    {
        return csv.phantrangchua(page,pageSize);
    }
    @GetMapping("loctheodiachi")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<chuas> loctheodiachi(@RequestParam String diachi,@RequestParam int page,@RequestParam int pageSize)
    {
        return csv.phantrangtheodiachi(diachi, page, pageSize);
    }
    @GetMapping("loctheotenchua")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<chuas> loctheotenchua(@RequestParam String tenchua,@RequestParam int page,@RequestParam int pageSize){
        return csv.loctheotenchua(tenchua, page, pageSize);

    }
    @GetMapping("loctheosotrutri")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public List<chuas> loctheosotrutri(@RequestParam int trutri,@RequestParam int page, @RequestParam int pageSize)
    {
        return csv.loctheosotrutri(trutri, page, pageSize);
    }
    @GetMapping("hienthichua")
    public List<chuas> hienthichua(){
        return csv.hienthichua();
    }
}

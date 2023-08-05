package quanliphattu.database.controller;

import com.google.gson.Gson;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import quanliphattu.database.Repository.phattuRepo;
import quanliphattu.database.dto.AuthRequest;
import quanliphattu.database.models.phattu;
import quanliphattu.database.service.jwtService;
import quanliphattu.database.service.phattuService;

import java.util.List;


@RestController
@CrossOrigin(value = "*",allowedHeaders = "*")
@RequestMapping("api/vers1")
public class phattuController {
    @Autowired
    private phattuService pts;
    @Autowired
    private phattuRepo ptr;
    @Autowired
    private jwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

   @RequestMapping(value = "addphattu",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public String addphattu(@RequestBody String ptadd) {
       System.out.println(ptadd);
        Gson gson = new Gson();
        phattu pt = gson.fromJson(ptadd,phattu.class);
        return pts.addphattu(pt);
    }

    @GetMapping(value = "phantrangphat")
    public List<phattu> getphattu(
            @RequestParam int page,
            @RequestParam int pageSize

    ) {
        return pts.getphattu(page, pageSize);
    }

    @GetMapping (value = "hienthiphattu")
    public List<phattu> showPhatTu() {
        return pts.showphattu();

    }
    @RequestMapping(value="suaphat",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    public String suaphattu(@RequestBody String phattu)
    {
        Gson gson = new Gson();
        phattu phattusua = gson.fromJson(phattu, phattu.class);
        return pts.suaphattu(phattusua);
    }
    @GetMapping(value = "phantrangtheoten")
    public List<phattu> getphattuTheoTen(
            @RequestParam int page,
            @RequestParam int pageSize

    ) {
        return pts.getphattuTheoTen(page, pageSize);
    }
    @GetMapping(value = "phantrangtheophapdanh")
    public List<phattu> getphattuTheoPhapDanh(
            @RequestParam int page,
            @RequestParam int pageSize

    ) {
        return pts.getphattuTheoPhapDanh(page, pageSize);
    }
   
    @GetMapping(value = "phantrangtheogioitinh")
    public List<phattu> getphattuTheoGioiTinh(
            @RequestParam int page,
            @RequestParam int pageSize,
            @RequestParam int gioitinh
    ){
        return pts.getphattuTheoGioiTinh(page,pageSize,gioitinh);
    }
    @GetMapping(value = "phantrangtheochua")
    public List<phattu> getphattuTheoChua(
            @RequestParam int page,
            @RequestParam int pageSize,
            @RequestParam int chuaid
    ){
        return pts.getphattuTheoChua(page,pageSize,chuaid);
    }
    @GetMapping(value = "phantrangtheohoantuc")
    public List<phattu> getphattuTheoHoanTuc(
            @RequestParam int page,
            @RequestParam int pageSize,
            @RequestParam boolean hoantuc
    ){
        return pts.getphattuTheoHoanTuc(page,pageSize,hoantuc);
    }
    @GetMapping(value = "phantrangtheokieuthanhvien")
    public List<phattu> getphattuTheoKieuThanhVien(
            @RequestParam int page,
            @RequestParam int pageSize,
            @RequestParam int kieuthanhvienid
    ){
        return pts.getphattuTheoKieuThanhVien(page,pageSize,kieuthanhvienid);
    }
    @PutMapping("xoaphattu")
    public String xoaphattu(@RequestParam int id)
    {
        return pts.xoaphattu(id);
    }
    @PutMapping("thaydoimatkhau")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public boolean changePass(@RequestParam String email,@RequestParam String password)
    {
        return pts.ChangePass(email, password);
    }

    @RequestMapping(value = "/authenticate",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public String authenticateAndGetToken(@RequestParam String gmail ,@RequestParam String password){
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(gmail,password));
            if(authentication.isAuthenticated())
            {
                jwtService.addTokenDb(gmail);
                return jwtService.genarateToken(gmail);
            }else{
                throw new UsernameNotFoundException("Không tìm thấy yêu cầu người dùng");
            }

    }
//    @RequestMapping(value = "/authenticate",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
//    public String authenticateAndGetToken(@RequestBody String authRequest){
//        Gson gson = new Gson();
//        AuthRequest dataRequest = gson.fromJson(authRequest, AuthRequest.class);
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dataRequest.getGmail(),dataRequest.getPassword()));
//        if(authentication.isAuthenticated())
//        {
//            jwtService.addTokenDb(dataRequest.getGmail());
//            return jwtService.genarateToken(dataRequest.getGmail());
//        }else{
//            throw new UsernameNotFoundException("Không tìm thấy yêu cầu người dùng");
//        }
//
//    }
}

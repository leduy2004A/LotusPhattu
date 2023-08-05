package quanliphattu.database.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import quanliphattu.database.models.token;
import quanliphattu.database.service.tokenService;

@RestController
@RequestMapping("api/vers1")
public class tokenController {
    @Autowired
    private tokenService tks;
    @GetMapping("laytoken")
    public token getToken(@RequestParam String gmail)
    {
        return tks.getToken(gmail);
    }
}

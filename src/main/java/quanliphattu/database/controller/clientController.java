package quanliphattu.database.controller;

import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import quanliphattu.database.service.ClientServiceImpl;

@RestController
@RequestMapping("/api/vers1")
public class clientController {
    @Autowired
    private ClientServiceImpl clientService;
    @PostMapping(value = "create")
    public boolean create(
            @RequestParam String emailAdress
    ) throws MessagingException {
        return clientService.create(emailAdress);
    }
}

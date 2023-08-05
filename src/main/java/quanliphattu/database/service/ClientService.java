package quanliphattu.database.service;

import jakarta.mail.MessagingException;

public interface ClientService {
    Boolean create(String emailAdress) throws MessagingException;
}

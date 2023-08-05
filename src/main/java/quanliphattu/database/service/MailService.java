package quanliphattu.database.service;

import jakarta.mail.MessagingException;
import quanliphattu.database.dto.DataMailDTO;


public interface MailService {
    void sendHtmlMail(DataMailDTO dataMail, String templateName) throws MessagingException;
}
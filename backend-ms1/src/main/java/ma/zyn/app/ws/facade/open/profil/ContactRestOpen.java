package ma.zyn.app.ws.facade.open.profil;

import ma.zyn.app.service.impl.admin.accompagnement.EmailService;
import ma.zyn.app.ws.dto.accompagnement.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/open/contact/")
public class ContactRestOpen {
    @PostMapping("send-email/")
    public ResponseEntity<?> sendEmail(@RequestBody EmailDto email) {
        emailService.sendSimpleMessage(email.getTo(), email.getSubject(), email.getMessage());
        return ResponseEntity.ok().build();
    }

    @Autowired
    private EmailService emailService;
}

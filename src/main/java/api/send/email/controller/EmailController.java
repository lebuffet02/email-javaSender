package api.send.email.controller;

import api.send.email.dto.EmailDTO;
import api.send.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    EmailService service;

    @PostMapping
    public ResponseEntity<?> simpleMailMessage(@RequestBody EmailDTO emailDTO) {
        service.sendEmailSimpleMailMessage(emailDTO);
        return ResponseEntity.status(200).build();
    }

    @PostMapping("/mime")
    public ResponseEntity<?> mimeMessageHelper(@RequestBody EmailDTO emailDTO) {
        service.sendEmailMimeMessageHelper(emailDTO);
        return ResponseEntity.status(200).build();
    }
}

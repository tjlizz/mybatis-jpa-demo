package lz.cim.api.jpa.attchment.controller;

import lz.cim.api.jpa.attchment.server.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {


    @Autowired
    AttachmentService attachmentService;


}

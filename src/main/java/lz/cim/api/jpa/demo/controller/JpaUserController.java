package lz.cim.api.jpa.demo.controller;

import lz.cim.api.jpa.demo.model.Attachment;
import lz.cim.api.jpa.demo.model.UserModel;
import lz.cim.api.jpa.demo.repository.AttachmentRepository;
import lz.cim.api.jpa.demo.service.JpaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class JpaUserController {

    @Autowired
    JpaUserService jpaUserService;

    @Autowired
    AttachmentRepository attachmentRepository;

    @RequestMapping("/get")
    public List<Attachment> getAll() {

        UserModel userModel = new UserModel();
        userModel.setId("1");

        return attachmentRepository.findAllByCreate(userModel);
    }
}

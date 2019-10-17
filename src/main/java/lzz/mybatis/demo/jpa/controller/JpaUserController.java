package lzz.mybatis.demo.jpa.controller;

import lzz.mybatis.demo.jpa.model.UserModel;
import lzz.mybatis.demo.jpa.service.JpaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class JpaUserController {

    @Autowired
 JpaUserService jpaUserService;

    @RequestMapping("/get")
     public List<UserModel> getAll(){

        return  jpaUserService.getAll();
     }
}

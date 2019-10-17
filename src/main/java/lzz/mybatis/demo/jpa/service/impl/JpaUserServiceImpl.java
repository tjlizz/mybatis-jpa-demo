package lzz.mybatis.demo.jpa.service.impl;

import lzz.mybatis.demo.jpa.epository.UserRepository;
import lzz.mybatis.demo.jpa.model.UserModel;
import lzz.mybatis.demo.jpa.service.JpaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaUserServiceImpl  implements JpaUserService {


    @Autowired
    UserRepository userRepository;
    @Override
    public List<UserModel> getAll() {
        return userRepository.findAll();
    }
}

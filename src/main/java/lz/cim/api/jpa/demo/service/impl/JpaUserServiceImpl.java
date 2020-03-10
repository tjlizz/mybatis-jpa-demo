package lz.cim.api.jpa.demo.service.impl;

import lz.cim.api.jpa.demo.repository.UserRepository;
import lz.cim.api.jpa.demo.model.UserModel;
import lz.cim.api.jpa.demo.service.JpaUserService;
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

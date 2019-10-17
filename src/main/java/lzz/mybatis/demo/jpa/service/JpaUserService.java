package lzz.mybatis.demo.jpa.service;

import lzz.mybatis.demo.jpa.model.UserModel;

import java.util.List;

public interface  JpaUserService {

    List<UserModel> getAll();
}

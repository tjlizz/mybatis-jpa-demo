package lz.cim.api.jpa.demo.service;

import lz.cim.api.jpa.demo.model.UserModel;

import java.util.List;

public interface  JpaUserService {

    List<UserModel> getAll();
}

package lz.cim.api.jpa.demo.repository;

import lz.cim.api.jpa.demo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<UserModel,Integer> {
}

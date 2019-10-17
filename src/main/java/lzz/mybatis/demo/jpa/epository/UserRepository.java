package lzz.mybatis.demo.jpa.epository;

import lzz.mybatis.demo.jpa.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<UserModel,Integer> {
}

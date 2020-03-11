package lz.cim.api.jpa.demo.repository;

import lz.cim.api.jpa.demo.model.Attachment;
import lz.cim.api.jpa.demo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttachmentRepository1 extends JpaRepository<Attachment, String> {

    List<Attachment> findAllByCreate(UserModel userModel);
}

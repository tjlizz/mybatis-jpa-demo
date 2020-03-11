package lz.cim.api.jpa.attchment.repository;

import lz.cim.api.jpa.attchment.model.AttachmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Max;
import java.util.List;

public interface AttachmentRepository extends JpaRepository<AttachmentModel, String> {

    List<AttachmentModel> findAllByKey(String key);

     List<AttachmentModel> findAllById(String id);

}

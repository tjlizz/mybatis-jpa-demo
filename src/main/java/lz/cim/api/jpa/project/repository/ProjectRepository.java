package lz.cim.api.jpa.project.repository;

import lz.cim.api.jpa.attchment.model.AttachmentModel;
import lz.cim.api.jpa.project.model.ProjectModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectModel, String> , JpaSpecificationExecutor<ProjectModel> {

    List<ProjectModel> findAllByAttachment_FileName(String fileName);


}

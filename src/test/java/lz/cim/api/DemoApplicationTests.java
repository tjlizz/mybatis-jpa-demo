package lz.cim.api;

import lz.cim.api.jpa.attchment.model.AttachmentModel;
import lz.cim.api.jpa.project.model.ProjectModel;
import lz.cim.api.jpa.project.repository.ProjectRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {


    @Autowired
    ProjectRepository projectRepository;


    @Test
      public void contextLoads() {

        AttachmentModel attachmentModel = new AttachmentModel();
        attachmentModel.setId("567");
        ProjectModel projectModel = new ProjectModel();
        projectModel.setAttachment(attachmentModel);
        projectModel.setId("789");
        projectRepository.save(projectModel);


    }

}

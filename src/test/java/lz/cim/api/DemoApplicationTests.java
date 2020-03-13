package lz.cim.api;

import lz.cim.api.core.upload.IoHelper;
import lz.cim.api.jpa.attchment.model.AttachmentModel;
import lz.cim.api.jpa.project.model.ProjectModel;
import lz.cim.api.jpa.project.repository.ProjectRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class DemoApplicationTests {


    @Test
    public void contextLoads() {
        IoHelper.judeDirExists("E:\\lz\\upload\\2020-03-11");

    }

}

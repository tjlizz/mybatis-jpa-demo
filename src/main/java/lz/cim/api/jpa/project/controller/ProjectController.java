package lz.cim.api.jpa.project.controller;

import lz.cim.api.core.query.searchmodel.SearchModel;

import lz.cim.api.core.tool.Common;
import lz.cim.api.core.view.ReslutView;
import lz.cim.api.jpa.attchment.model.AttachmentModel;
import lz.cim.api.jpa.attchment.server.AttachmentService;
import lz.cim.api.jpa.project.model.ProjectModel;
import lz.cim.api.jpa.project.repository.ProjectRepository;
import lz.cim.api.jpa.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/model")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    AttachmentService attachmentService;

    @RequestMapping(path = {"/getall"})
    public ResponseEntity<?> finfAll(@RequestHeader("token") String token, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit,
                                     @RequestParam("projectCode") String projectCode, @RequestParam("fileName") String fileName) {
        ReslutView reslutView = new ReslutView();
//
        Page<ProjectModel> projectModels = projectService.getListByPage(page, limit, projectCode, fileName);
        reslutView.setData(projectModels.getContent());
        reslutView.setCount(projectModels.getTotalElements());

        return ResponseEntity.status(HttpStatus.OK).body(reslutView);
    }


    @RequestMapping(
            value = "/updatedesign",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<?> saveDesign(@RequestBody ProjectModel projectModel) {

        ReslutView reslutView=new ReslutView();
        AttachmentModel attachmentModel = attachmentService.getById(projectModel.getSubCodeName());

        projectModel.setId(Common.GetKey());
        projectModel.setAttachment(attachmentModel);

        projectService.save(projectModel);
        reslutView.setData(projectModel);
        return ResponseEntity.status(HttpStatus.OK).body(reslutView);
    }


}

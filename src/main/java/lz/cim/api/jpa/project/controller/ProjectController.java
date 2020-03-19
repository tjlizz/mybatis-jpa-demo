package lz.cim.api.jpa.project.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import lz.cim.api.core.query.searchmodel.SearchModel;

import lz.cim.api.core.tool.Common;
import lz.cim.api.core.tool.ErrorTool;
import lz.cim.api.core.view.ModelType;
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

@Slf4j
@Api(tags = "CIM基础数据", description = "文件上传")
@RestController
@RequestMapping("/api/model")
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @Autowired
    AttachmentService attachmentService;

    @ApiOperation("设计模型查询")
    @RequestMapping(path = {"/getall"}, method = RequestMethod.GET)
    public ResponseEntity<?> finfAll(@RequestParam("page") Integer page, @RequestParam("limit") Integer limit,
                                     @RequestParam("projectCode") String projectCode, @RequestParam("fileName") String fileName,
                                     @RequestParam("layerCode") String layerCode, @RequestHeader("token") String token) throws Exception {
        ReslutView reslutView = new ReslutView();


        Page<ProjectModel> projectModels = projectService.getListByPage(page, limit, projectCode, fileName, layerCode);
        reslutView.setData(projectModels.getContent());
        reslutView.setCount(projectModels.getTotalElements());

        return ResponseEntity.status(HttpStatus.OK).body(reslutView);
    }

    @ApiOperation("设计模型保存")
    @RequestMapping(
            value = "/updatedesign",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<?> saveDesign(@RequestBody ProjectModel projectModel) {
        ReslutView reslutView = new ReslutView();
        AttachmentModel attachmentModel = attachmentService.getById(projectModel.getSubCodeName());
        if (attachmentModel == null) {
            reslutView.setMsg("请上传附件");
            reslutView.setCode("1");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reslutView);
        }
        projectModel.setAttachment(attachmentModel);
        if (projectModel.getId() != null && !projectModel.getId().trim().isEmpty()) {
            projectService.update(projectModel);
        } else {

            saveProject(projectModel);
        }
        reslutView.setData(projectModel);
        return ResponseEntity.status(HttpStatus.OK).body(reslutView);
    }

    @ApiOperation("场布模型保存")
    @RequestMapping(
            value = "/updatelayout",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<?> updatelayout(@RequestBody ProjectModel projectModel) {
        ReslutView reslutView = new ReslutView();
        AttachmentModel attachmentModel = attachmentService.getById(projectModel.getSubCodeName());
        if (attachmentModel == null) {
            reslutView.setMsg("请上传附件");
            reslutView.setCode("1");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reslutView);
        }
        fieldCloth(projectModel, reslutView, attachmentModel);
        if (reslutView.getCode().equals("1"))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reslutView);

        return ResponseEntity.status(HttpStatus.OK).body(reslutView);
    }

    @ApiOperation("施工模型及进度保存")
    @RequestMapping(
            value = "/updateconstruction",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<?> updateconstruction(@RequestBody ProjectModel projectModel) {
        ReslutView reslutView = new ReslutView();
        AttachmentModel attachmentModel = attachmentService.getById(projectModel.getSubCodeName());
        if (attachmentModel == null) {
            reslutView.setMsg("请上传附件");
            reslutView.setCode("1");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reslutView);
        }
        projectModel.setSubCodeName(null);
        AttachmentModel xml = attachmentService.getById(projectModel.getStage());//XML附件
        projectModel.setStage(null);
        if (null == xml) {
            reslutView.setMsg("请上传XML文件");
            reslutView.setCode("1");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reslutView);

        }
        if (projectModel.getId() == null || projectModel.getId().trim().equals("")) {
            projectModel.setAttachmentXml(xml);
            projectModel.setAttachment(attachmentModel);
            saveProject(projectModel);
        } else {
            ProjectModel projectData = projectService.getById(projectModel.getId());
            projectData.setBuildingCode(projectModel.getBuildingCode());
            projectData.setSpeciality(projectModel.getSpeciality());
            projectData.setAttachmentXml(attachmentModel);
            projectData.setAttachmentXml(xml);
            projectService.update(projectData);
        }
        return ResponseEntity.status(HttpStatus.OK).body(reslutView);
    }

    @ApiOperation("倾斜摄影和航拍录像保存")
    @RequestMapping(
            value = "/updateimagemap",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<?> updateimagemap(@RequestBody ProjectModel projectModel) {
        ReslutView reslutView = new ReslutView();
        AttachmentModel attachmentModel = attachmentService.getById(projectModel.getSubCodeName());
        if (attachmentModel == null) {
            reslutView.setMsg("请上传附件");
            reslutView.setCode("1");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reslutView);
        }
        projectModel.setSubCodeName(null);
        projectModel.setAttachment(attachmentModel);
        ProjectModel projectData = projectService.getByProjectCodeAndImageDate(projectModel.getProjectCode(), projectModel.getImageDate());

        if (projectData == null) {
            saveProject(projectModel);
        } else {
            projectData.setVersion(projectData.getVersion() + 1);
            projectData.setImageDate(projectModel.getImageDate());
            projectData.setAttachment(attachmentModel);
            projectService.update(projectData);
        }
        return ResponseEntity.status(HttpStatus.OK).body(reslutView);
    }

    @ApiOperation("竣工模型及验收资料保存")
    @RequestMapping(
            value = "/updatecompletion",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<?> updatecompletion(@RequestBody ProjectModel projectModel) {
        ReslutView reslutView = new ReslutView();
        AttachmentModel attachmentModel = attachmentService.getById(projectModel.getSubCodeName());
        if (attachmentModel == null) {
            reslutView.setMsg("请上传附件");
            reslutView.setCode("1");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reslutView);
        }
        projectModel.setSubCodeName(null);
        AttachmentModel xml = attachmentService.getById(projectModel.getStage());//XML附件
        projectModel.setStage(null);
        if (null == xml) {
            reslutView.setMsg("请上传文件");
            reslutView.setCode("1");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reslutView);

        }
        if (projectModel.getId() == null || projectModel.getId().trim().equals("")) {
            projectModel.setAttachmentXml(xml);
            projectModel.setAttachment(attachmentModel);
            saveProject(projectModel);
        } else {
            ProjectModel projectData = projectService.getById(projectModel.getId());
            projectData.setBuildingCode(projectModel.getBuildingCode());
            projectData.setSpeciality(projectModel.getSpeciality());
            projectData.setAttachmentXml(attachmentModel);
            projectData.setAttachmentXml(xml);
            projectService.update(projectData);
        }
        return ResponseEntity.status(HttpStatus.OK).body(reslutView);
    }

    private void saveProject(@RequestBody ProjectModel projectModel) {
        projectModel.setId(Common.GetKey());
        projectModel.setCreateTime(Common.GetDateTime());
        projectService.save(projectModel);
    }

    @ApiOperation("设计模型删除")
    @RequestMapping(
            value = "/design/{id}",
            method = RequestMethod.DELETE,
            consumes = "application/json")
    public ResponseEntity<?> deleteDesign(@PathVariable String id) {
        ReslutView reslutView = new ReslutView();
        try {

            projectService.delete(id);

        } catch (Exception ex) {
            log.error(ErrorTool.getErrerInfo(ex));
            reslutView.setCode("1");
            reslutView.setData(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reslutView);
        }
        return ResponseEntity.status(HttpStatus.OK).body(reslutView);
    }

    @ApiOperation("根据ID设计模型查询")
    @RequestMapping(path = {"/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable(value = "id", required = true) String id) {

        ReslutView reslutView = new ReslutView();
        try {

            ProjectModel projectModel = projectService.getById(id);
            reslutView.setData(projectModel);
        } catch (Exception ex) {
            log.error(ErrorTool.getErrerInfo(ex));
            reslutView.setCode("1");
            reslutView.setData(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reslutView);
        }
        return ResponseEntity.status(HttpStatus.OK).body(reslutView);
    }

    /**
     * 场布类型保存逻辑
     *
     * @param projectModel
     * @param reslutView
     * @param attachmentModel
     */
    private void fieldCloth(@RequestBody ProjectModel projectModel, ReslutView reslutView, AttachmentModel
            attachmentModel) {
        ProjectModel projectData = projectService.getByProjectCodeAndLayerCode(projectModel.getProjectCode(), projectModel.getLayerCode());
        AttachmentModel xml = attachmentService.getById(projectModel.getBuildingCode());//XML附件
        projectModel.setBuildingCode(null);
        if (null == xml) {
            reslutView.setMsg("请上传XML文件");
            reslutView.setCode("1");
            return;

        }
        projectModel.setSubCodeName(null);
        projectModel.setAttachmentXml(xml);
        projectModel.setAttachment(attachmentModel);
        if (null == projectData) {
            saveProject(projectModel);
            reslutView.setData(projectModel);
        } else {
            projectModel.setId(projectData.getId());
            projectModel.setCreateTime(Common.GetDateTime());
            projectModel.setVersion(projectData.getVersion() + 1);

            projectService.update(projectModel);
            reslutView.setData(projectModel);
        }

    }
}

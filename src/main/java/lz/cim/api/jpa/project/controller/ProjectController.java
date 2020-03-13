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
                                     @RequestParam("layerCode") String layerCode, @RequestHeader("token") String token) {
        ReslutView reslutView = new ReslutView();
        try {
            Page<ProjectModel> projectModels = projectService.getListByPage(page, limit, projectCode, fileName, layerCode);
            reslutView.setData(projectModels.getContent());
            reslutView.setCount(projectModels.getTotalElements());
        } catch (Exception ex) {
            log.error(ErrorTool.getErrerInfo(ex));
        }
        return ResponseEntity.status(HttpStatus.OK).body(reslutView);
    }

    @ApiOperation("设计模型保存")
    @RequestMapping(
            value = "/updatedesign",
            method = RequestMethod.POST,
            consumes = "application/json")
    public ResponseEntity<?> saveDesign(@RequestBody ProjectModel projectModel) {
        ReslutView reslutView = new ReslutView();
        try {
            AttachmentModel attachmentModel = attachmentService.getById(projectModel.getSubCodeName());
            if (attachmentModel == null) {
                reslutView.setMsg("请上传附件");
                reslutView.setCode("1");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(reslutView);
            }
            switch (projectModel.getLayerCode()) {

                case "1"://场布
                    fieldCloth(projectModel, reslutView, attachmentModel);
                    break;

                default:
                    projectModel.setAttachment(attachmentModel);
                    if (projectModel.getId() != null) {
                        projectService.update(projectModel);
                    } else {
                        projectModel.setId(Common.GetKey());
                        projectModel.setSubCodeName(null);
                        projectModel.setCreateTime(Common.GetDateTime());
                        projectService.save(projectModel);
                    }
                    reslutView.setData(projectModel);
                    break;
            }


        } catch (Exception ex) {
            log.error(ErrorTool.getErrerInfo(ex));
            reslutView.setCode("1");
            reslutView.setData(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(reslutView);
        }
        return ResponseEntity.status(HttpStatus.OK).body(reslutView);
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

    /**
     * 场布类型保存逻辑
     *
     * @param projectModel
     * @param reslutView
     * @param attachmentModel
     */
    private void fieldCloth(@RequestBody ProjectModel projectModel, ReslutView reslutView, AttachmentModel attachmentModel) {
        ProjectModel projectData = projectService.getByProjectCodeAndLayerCode(projectModel.getProjectCode(), projectModel.getLayerCode());
        projectModel.setSubCodeName(null);

        if (null == projectData) {
            projectModel.setId(Common.GetKey());
            projectModel.setAttachment(attachmentModel);
            projectModel.setCreateTime(Common.GetDateTime());
            projectService.save(projectModel);
            reslutView.setData(projectModel);
        } else {
            projectData.setAttachment(attachmentModel);
            projectModel.setCreateTime(Common.GetDateTime());
            projectData.setBuildingCode(projectModel.getBuildingCode());
            projectData.setSpeciality(projectModel.getSpeciality());
            projectData.setVersion(projectData.getVersion() + 1);
            projectService.update(projectData);
            reslutView.setData(projectData);
        }

    }
}

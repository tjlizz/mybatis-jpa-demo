package lz.cim.api.jpa.project.service;

import lz.cim.api.core.query.searchmodel.SearchModel;
import lz.cim.api.jpa.project.model.ProjectModel;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;

public interface ProjectService {
    Page<ProjectModel> getListByPage(int pageIndex, int pageSize, String projectCode, String fileName, String layerCode) throws Exception;


    ProjectModel getByProjectCodeAndLayerCode(String projectCode, String layerCode);

     ProjectModel getByProjectCodeAndImageDate(String projectCode, Date imageDate);
    ProjectModel save(ProjectModel projectModel);

    ProjectModel update(ProjectModel projectModel);

    void delete(String id);

     ProjectModel getById(String id);

}

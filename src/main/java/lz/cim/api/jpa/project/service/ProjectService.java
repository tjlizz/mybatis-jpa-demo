package lz.cim.api.jpa.project.service;

import lz.cim.api.core.query.searchmodel.SearchModel;
import lz.cim.api.jpa.project.model.ProjectModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProjectService {
    Page<ProjectModel> getListByPage(int pageIndex,int pageSize,String projectCode,String fileName);

}

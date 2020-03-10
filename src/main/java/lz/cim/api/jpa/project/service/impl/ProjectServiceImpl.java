package lz.cim.api.jpa.project.service.impl;

import lz.cim.api.core.query.Criteria;
import lz.cim.api.core.query.Restrictions;
import lz.cim.api.core.query.searchmodel.ConditionItem;
import lz.cim.api.core.query.searchmodel.QueryModel;
import lz.cim.api.core.query.searchmodel.SearchModel;
import lz.cim.api.jpa.attchment.model.AttachmentModel;
import lz.cim.api.jpa.project.model.ProjectModel;
import lz.cim.api.jpa.project.repository.ProjectRepository;
import lz.cim.api.jpa.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public Page<ProjectModel> getListByPage( int pageIndex, int pageSize, String projectCode, String fileName) {


        PageRequest pageRequest = new PageRequest(pageIndex - 1, pageSize);

        Specification<ProjectModel> spec = new Specification<ProjectModel>() {
            @Override
            public Predicate toPredicate(Root<ProjectModel> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                if (fileName != null && !fileName.isEmpty()) {
                    Join<ProjectModel, AttachmentModel> fuJoin = root.join(root.getModel().getSingularAttribute("attachment", AttachmentModel.class), JoinType.INNER);
                    Predicate p2 = cb.like((Expression<String>) fuJoin.get("fileName").as(String.class), "%" + fileName + "%");
                    criteriaQuery.where(cb.and(p2));
                }
                return criteriaQuery.getRestriction();
            }
        };

        return projectRepository.findAll(spec, pageRequest);
    }
}

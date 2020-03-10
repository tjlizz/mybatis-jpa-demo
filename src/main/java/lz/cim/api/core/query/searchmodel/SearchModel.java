package lz.cim.api.core.query.searchmodel;

import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public class SearchModel {


    public SearchModel() {

    }

    private Integer limit;

    private Integer page;

    private String orderField;
    private String isAsc;


    private List<ConditionItem> fields;

    private List<Restrictions> restrictions;

    public QueryModel GetQueryModel() {
        QueryModel queryModel = new QueryModel();
        if (this.fields.size() > 0) {

            for (ConditionItem item : this.fields
            ) {
                if (item.getValue() != null) {
                    queryModel.AddItem(item);
                }

            }
        }
        return queryModel;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }


    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(String isAsc) {
        this.isAsc = isAsc;
    }



    public List<ConditionItem> getFields() {
        return fields;
    }

    public void setFields(List<ConditionItem> fields) {
        this.fields = fields;
    }


    public PageRequest buildPageRequest() {
        Sort sort = null;

        if (Boolean.parseBoolean(this.isAsc)) {
            sort = new Sort(Sort.Direction.ASC, this.orderField);
        } else {
            sort = new Sort(Sort.Direction.DESC, this.orderField);
        }
        // sort.and(new Sort(Sort.Direction.ASC,"userId"));
        return new PageRequest(this.page - 1, this
                .limit, sort);
    }

    public List<Restrictions> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(List<Restrictions> restrictions) {
        this.restrictions = restrictions;
    }
}

package lz.cim.api.core.query;


import lz.cim.api.core.query.searchmodel.ConditionItem;
import lz.cim.api.core.query.searchmodel.QueryMethod;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;


public class SimpleExpression implements Criterion {

    private ConditionItem conditionItem;

    public SimpleExpression(ConditionItem item) {
        this.conditionItem = item;
    }

    protected SimpleExpression(String fieldName, Object value, QueryMethod operator) {
        this.conditionItem = new ConditionItem(fieldName,operator,value);
    }



    @SuppressWarnings({"rawtypes", "unchecked"})
    public Predicate toPredicate(Root<?> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        Path expression = null;
        if (this.conditionItem.getField().contains(".")) {
            String[] names = StringUtils.split(this.conditionItem.getField(), ".");
            expression = root.get(names[0]);
            for (int i = 1; i < names.length; i++) {
                expression = expression.get(names[i]);
            }
        } else {
            expression = root.get(this.conditionItem.getField());
        }
        switch (this.conditionItem.getMethod()) {
            case Equal:
                return builder.equal(expression, this.conditionItem.getValue());
            case NotEqual:
                return builder.notEqual(expression, this.conditionItem.getValue());
            case Like:
                return builder.like((Expression<String>) expression, "%" + this.conditionItem.getValue() + "%");
            case LessThan:
                return builder.lessThan(expression, (Comparable) this.conditionItem.getValue());
            case GreaterThan:
                return builder.greaterThan(expression, (Comparable) this.conditionItem.getValue());
            case LessThanOrEqual:
                return builder.lessThanOrEqualTo(expression, (Comparable) this.conditionItem.getValue());
            case GreaterThanOrEqual:
                return builder.greaterThanOrEqualTo(expression, (Comparable) this.conditionItem.getValue());
            default:
                return null;
        }

    }
}

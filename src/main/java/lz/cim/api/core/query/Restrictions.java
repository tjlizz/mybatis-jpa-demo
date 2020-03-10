package lz.cim.api.core.query;


import lz.cim.api.core.query.searchmodel.ConditionItem;
import lz.cim.api.core.query.searchmodel.QueryMethod;
import org.hibernate.criterion.MatchMode;
import org.springframework.util.StringUtils;

import java.util.Collection;


public class Restrictions {


    public static Criterion AddItem(ConditionItem conditionItem) {
        switch (conditionItem.getMethod()) {

            case NotEqual:
                return ne(conditionItem.getField(), conditionItem.getValue(), true);
            case Like:
                return like(conditionItem.getField(), conditionItem.getValue().toString(), true);
            case IN:
                return in(conditionItem.getField(), conditionItem.getCollection(), true);
            case Equal:
                return eq(conditionItem.getField(), conditionItem.getValue().toString(), true);

            default:
                return eq(conditionItem.getField(), conditionItem.getValue().toString(), true);
        }
    }

    /**
     * 等于
     *
     * @param fieldName
     * @param value
     * @param ignoreNull
     * @return
     */
    public static SimpleExpression eq(String fieldName, Object value, boolean ignoreNull) {
        if (StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(fieldName, value, QueryMethod.Equal);
    }

    /**
     * 不等于
     *
     * @param fieldName
     * @param value
     * @param ignoreNull
     * @return
     */
    public static SimpleExpression ne(String fieldName, Object value, boolean ignoreNull) {
        if (StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(fieldName, value, QueryMethod.NotEqual);
    }

    /**
     * 模糊匹配
     *
     * @param fieldName
     * @param value
     * @param ignoreNull
     * @return
     */
    public static SimpleExpression like(String fieldName, String value, boolean ignoreNull) {
        if (StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(fieldName, value, QueryMethod.Like);
    }

    /**
     * @param fieldName
     * @param value
     * @param matchMode
     * @param ignoreNull
     * @return
     */
    public static SimpleExpression like(String fieldName, String value,
                                        MatchMode matchMode, boolean ignoreNull) {
        if (StringUtils.isEmpty(value)) return null;
        return null;
    }

    /**
     * 大于
     *
     * @param fieldName
     * @param value
     * @param ignoreNull
     * @return
     */
    public static SimpleExpression gt(String fieldName, Object value, boolean ignoreNull) {
        if (StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(fieldName, value, QueryMethod.GreaterThan);
    }

    /**
     * 小于
     *
     * @param fieldName
     * @param value
     * @param ignoreNull
     * @return
     */
    public static SimpleExpression lt(String fieldName, Object value, boolean ignoreNull) {
        if (StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(fieldName, value, QueryMethod.LessThan);
    }

    /**
     * 大于等于
     *
     * @param fieldName
     * @param value
     * @param ignoreNull
     * @return
     */
    public static SimpleExpression lte(String fieldName, Object value, boolean ignoreNull) {
        if (StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(fieldName, value, QueryMethod.GreaterThanOrEqual);
    }

    /**
     * 小于等于
     *
     * @param fieldName
     * @param value
     * @param ignoreNull
     * @return
     */
    public static SimpleExpression gte(String fieldName, Object value, boolean ignoreNull) {
        if (StringUtils.isEmpty(value)) return null;
        return new SimpleExpression(fieldName, value, QueryMethod.LessThanOrEqual);
    }

    /**
     * 并且
     *
     * @param criterions
     * @return
     */
    public static LogicalExpression and(Criterion... criterions) {
        return new LogicalExpression(criterions, QueryMethod.AND);
    }

    /**
     * 或者
     *
     * @param criterions
     * @return
     */
    public static LogicalExpression or(Criterion... criterions) {
        return new LogicalExpression(criterions, QueryMethod.OR);
    }

    /**
     * 包含于
     *
     * @param fieldName
     * @param value
     * @return
     */
    @SuppressWarnings("rawtypes")
    public static LogicalExpression in(String fieldName, Collection value, boolean ignoreNull) {
        if (ignoreNull && (value == null || value.isEmpty())) {
            return null;
        }
        SimpleExpression[] ses = new SimpleExpression[value.size()];
        int i = 0;
        for (Object obj : value) {
            ses[i] = new SimpleExpression(fieldName, obj, QueryMethod.Equal);
            i++;
        }
        return new LogicalExpression(ses, QueryMethod.OR);
    }
}
package lz.cim.api.core.query.searchmodel;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class ConditionItem {

    public ConditionItem() {
        this.method = QueryMethod.Equal;
    }

    public ConditionItem(String field, QueryMethod operator, Object value) {
        this.field = field;
        this.method = operator;
        this.value = value;
    }


    private String field;
    private QueryMethod method;
    private Object value;
    private Collection collection;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public QueryMethod getMethod() {
        return method;
    }

    public void setMethod(QueryMethod method) {
        this.method = method;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Collection getCollection() {
        setItems(value);
        return collection;
    }

    public void setCollection(Collection cValue) {
        this.collection = cValue;
    }

    private void setItems(Object items) {
        if (items instanceof Collection) {
            collection = (Collection) items;
        }
        if (items instanceof Map) {
            Map map = (Map) items;
            collection = map.entrySet();
        }
        if (items.getClass().isArray()) {
            this.collection = new ArrayList();
            int length = Array.getLength(items);
            for (int i = 0; i < length; i++) {
                Object value = Array.get(items, i);
                this.collection.add(value);
            }
        }
    }

}

package lz.cim.api.core.query.searchmodel;

import java.util.ArrayList;
import java.util.List;

public class QueryModel {
    public QueryModel() {
        this.items = new ArrayList<>();
    }

    private List<ConditionItem> items;

    public List<ConditionItem> getItems() {
        return items;
    }

    public void AddItem(ConditionItem item) {
        this.items.add(item);

    }


}

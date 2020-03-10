package lz.cim.api.core.query.searchmodel;

public enum QueryMethod {

    Equal(0), LessThan(1), GreaterThan(2), LessThanOrEqual(3), GreaterThanOrEqual(4), Like(5) , NotEqual(6), AND(7),OR(8),IN(9);

    QueryMethod(Integer type) {
        this.type = type;

    }

    private Integer type;


    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


}
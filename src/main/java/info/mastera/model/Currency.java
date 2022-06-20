package info.mastera.model;

public class Currency extends BaseEntity {

    private String code;

    public String getCode() {
        return code;
    }

    public Currency setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + getId() +
                ", code='" + code + '\'' +
                '}';
    }
}

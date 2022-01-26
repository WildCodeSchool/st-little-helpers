package dev.wcs.tutoring.littlehelpers.pojo;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class NoLombokPojo {

    private String attribute;
    private LocalDate created;
    private BigDecimal value;

    public NoLombokPojo() {}

    public NoLombokPojo(String attribute, LocalDate created, BigDecimal value) {
        this.attribute = attribute;
        this.created = created;
        this.value = value;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoLombokPojo that = (NoLombokPojo) o;
        return attribute.equals(that.attribute) && created.equals(that.created) && value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attribute, created, value);
    }


    @Override
    public String toString() {
        return "NoLombokPojo{" +
                "attribute='" + attribute + '\'' +
                ", created=" + created +
                ", value=" + value +
                '}';
    }

}

package entity;

import java.time.LocalDate;

public class Data {
    private LocalDate date;
    private String latin;
    private String russian;
    private Integer intValue;
    private Double doubleValue;

    public Data() {
    }

    public Data(LocalDate date, String latin, String russian, Integer intValue, Double doubleValue) {
        this.date = date;
        this.latin = latin;
        this.russian = russian;
        this.intValue = intValue;
        this.doubleValue = doubleValue;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getLatin() {
        return latin;
    }

    public void setLatin(String latin) {
        this.latin = latin;
    }

    public String getRussian() {
        return russian;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }

    public Integer getIntValue() {
        return intValue;
    }

    public void setIntValue(Integer intValue) {
        this.intValue = intValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Data data = (Data) o;

        if (date != null ? !date.equals(data.date) : data.date != null) return false;
        if (latin != null ? !latin.equals(data.latin) : data.latin != null) return false;
        if (russian != null ? !russian.equals(data.russian) : data.russian != null) return false;
        if (intValue != null ? !intValue.equals(data.intValue) : data.intValue != null) return false;
        return doubleValue != null ? doubleValue.equals(data.doubleValue) : data.doubleValue == null;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (latin != null ? latin.hashCode() : 0);
        result = 31 * result + (russian != null ? russian.hashCode() : 0);
        result = 31 * result + (intValue != null ? intValue.hashCode() : 0);
        result = 31 * result + (doubleValue != null ? doubleValue.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Data{");
        sb.append("date=").append(date);
        sb.append(", latin='").append(latin).append('\'');
        sb.append(", russian='").append(russian).append('\'');
        sb.append(", intValue=").append(intValue);
        sb.append(", doubleValue=").append(doubleValue);
        sb.append('}');
        return sb.toString();
    }
}

package calculator.entity;

public class SeparatorEntity {
    private final String separator;
    private final String numberPart;

    public SeparatorEntity(String separator, String numberPart) {
        this.separator = separator;
        this.numberPart = numberPart;
    }

    public String getSeparator() {
        return separator;
    }
    public String getNumberPart() {
        return numberPart;
    }
}

package daa.utils;

public enum InputType {

    RANDOM("random"),
    SORTED("sorted"),
    DUPLICATES("duplicates");

    private final String value;

    InputType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
package info.mastera.console.command.core;

import java.util.Arrays;

public enum Operation {
    HELP("?"),
    GET_ALL("getAll");

    private final String code;

    public String getCode() {
        return code;
    }

    Operation(String code) {
        this.code = code;
    }

    public static Operation parse(String code) {
        return Arrays.stream(Operation.values())
                .filter(enumValue -> enumValue.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }
}

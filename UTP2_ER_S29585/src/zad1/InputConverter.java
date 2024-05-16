package zad1;

import java.util.function.Function;

public class InputConverter<T> {
    private final T initialInput;

    public InputConverter(T initialInput) {
        this.initialInput = initialInput;
    }

    public <R> R convertBy(Function... functions) {

        Object result = initialInput;

        for (Function<T, ?> function : functions) {
            result = function.apply((T) result);
        }

        return (R) result;
    }
}
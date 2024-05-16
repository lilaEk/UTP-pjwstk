package zad2;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {
    T something;

    public Maybe() { }

    public Maybe(T t) { this.something = t; }

    public static <R> Maybe<R> of(R something) {
        return new Maybe<>(something);
    }

    public void ifPresent(Consumer cons) {
        if (something != null) {
            cons.accept(something);
        }
    }

    public <R> Maybe<R> map(Function<T, R> func) {
        if (something != null) {
            R result = func.apply(something);

            if (result == null) {
                return new Maybe<>();
            }
            return new Maybe<>(result);
        }
        return new Maybe<>();
    }

    public T get() throws NoSuchElementException {
        if (something == null) {
            throw new NoSuchElementException("maybe is empty");
        }
        return something;
    }

    public boolean isPresent() {
        return something != null;
    }

    public T orElse(T defVal) {
        if (something == null) {
            return defVal;
        }
        return something;
    }

    public Maybe<T> filter(Predicate<T> pred) {
        if (pred.test((T) this.something) && something != null) {
            return this;
        }
        return new Maybe<>();
    }

    @Override
    public String toString() {
        if (something != null) {
            return "Maybe has value " + something.toString();
        }
        return "Maybe is empty";
    }
}
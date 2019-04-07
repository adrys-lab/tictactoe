package com.alice.adrian.rebollo.tictactoe.model;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class Result<T> {

    private final boolean isValid;
    private final Optional<T> instance;

    private Result(boolean isValid, T instance) {
        this.isValid = isValid;
        this.instance = Optional.of(instance);
    }

    private Result(boolean isValid) {
        this.isValid = isValid;
        this.instance = Optional.empty();
    }

    public boolean isValid() {
        return isValid;
    }

    public boolean isPresent() {
        return instance.isPresent();
    }

    public static <T> Result<T> of(boolean valid, T instance) {
        return new Result<>(valid, instance);
    }

    public static <T> Result<T> valid() {
        return new Result<>(true);
    }

    public static <T> Result<T> valid(T instance) {
        return new Result<>(true, instance);
    }

    public static <T> Result<T> failure() {
        return new Result<>(false);
    }

    public static <T> Result<T> failure(T instance) {
        return new Result<>(false, instance);
    }

    public<U> Optional<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (!instance.isPresent()) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(mapper.apply(instance.get()));
        }
    }

    public Result<T> ifValid(Consumer<T> consumer) {

        if (isValid && instance.isPresent()) {
            consumer.accept(instance.get());
            return this;
        } else {
            return this;
        }
    }

    public Optional<T> getInstance() {
        return instance;
    }
}

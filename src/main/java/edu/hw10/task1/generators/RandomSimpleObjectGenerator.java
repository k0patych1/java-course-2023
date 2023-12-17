package edu.hw10.task1.generators;

import java.lang.reflect.Parameter;

public interface RandomSimpleObjectGenerator<T> {
    T generate(Parameter parameter);
}

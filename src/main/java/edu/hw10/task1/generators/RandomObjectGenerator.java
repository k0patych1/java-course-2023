package edu.hw10.task1.generators;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.Map;

public class RandomObjectGenerator {
    private final Map<String, RandomSimpleObjectGenerator<?>> simpleGenerators;

    public RandomObjectGenerator(Map<String, RandomSimpleObjectGenerator<?>> simpleGenerators) {
        this.simpleGenerators = simpleGenerators;
    }

    public <T> T nextObject(Class<?> className)
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?> constructor = getPublicConstructor(className);
        Parameter[] parameters = constructor.getParameters();

        Object[] arguments = getArguments(parameters);

        return (T) constructor.newInstance(arguments);
    }

    public <T> T nextObject(Class<?> className, String factoryMethodName)
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method factoryMethod = className.getDeclaredMethod(factoryMethodName);
        Parameter[] parameters = factoryMethod.getParameters();

        Object[] arguments = getArguments(parameters);

        return (T) factoryMethod.invoke(arguments);
    }

    private Constructor<?> getPublicConstructor(Class<?> className) {
        Constructor<?>[] constructors = className.getConstructors();

        for (Constructor<?> constructor : constructors) {
            if (Modifier.isPublic(constructor.getModifiers())) {
                return constructor;
            }
        }

        return null;
    }

    private Object[] getArguments(Parameter[] parameters) {
        int numOfParameters = parameters.length;
        Object[] arguments = new Object[numOfParameters];
        for (int i = 0; i < numOfParameters; ++i) {
            String parameterTypeName = parameters[i].getType().getName();
            RandomSimpleObjectGenerator<?> simpleGenerator = simpleGenerators.get(parameterTypeName);
            arguments[i] = simpleGenerator.generate(parameters[i]);
        }

        return arguments;
    }
}

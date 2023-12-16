package edu.hw10.Task1;

import edu.hw10.Task1.Annotations.Max;
import edu.hw10.Task1.Annotations.Min;
import edu.hw10.Task1.Annotations.NotNull;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.Random;

public class RandomObjectGenerator {
    private final Random random = new Random();

    public Object createRandomObject(Class<?> object) throws Exception {
        var constructors = object.getConstructors();
        var constructor = getSingleConstructor(constructors);
        var randomValues = generateRandomValues(constructor.getParameters());
        return object.cast(constructor.newInstance(randomValues));
    }

    private java.lang.reflect.Constructor<?> getSingleConstructor(java.lang.reflect.Constructor<?>[] constructors)
        throws NoSuchMethodException {
        if (constructors.length != 1) {
            throw new NoSuchMethodException("Class does not have a single public constructor.");
        }
        return constructors[0];
    }

    public Object createRandomObject(Class<?> object, String factoryMethodName) throws Exception {
        var factoryMethod = getFactoryMethod(object, factoryMethodName);
        var parameters = factoryMethod.getParameters();
        var randomValues = generateRandomValues(parameters);
        return object.cast(factoryMethod.invoke(object, randomValues));
    }

    private Method getFactoryMethod(Class<?> object, String fabricMethodName) throws NoSuchMethodException {
        Method[] methods = object.getDeclaredMethods();
        for (Method method : methods) {
            if (method.getName().equals(fabricMethodName)) {
                return method;
            }
        }
        throw new NoSuchMethodException("Class does not contain a method with that name");
    }

    private Object[] generateRandomValues(Parameter[] parameters) {
        return Arrays.stream(parameters)
            .map(parameter -> {
                try {
                    return getRandomValue(parameter);
                } catch (Exception e) {
                    return null;
                }
            })
            .toArray(Object[]::new);
    }

    private Object getRandomValue(Parameter parameter) {
        Class<?> parameterType = parameter.getType();
        if (parameterType == int.class || parameterType == Integer.class) {
            return generateRandomInt(parameter);
        } else if (parameterType == boolean.class || parameterType == Boolean.class) {
            return generateRandomBoolean(parameter);
        } else if (parameterType == String.class) {
            return generateRandomString(parameter);
        } else {
            throw new IllegalArgumentException("Generation of a variable of this type is not supported");
        }
    }

    private int generateRandomInt(Parameter parameter) {
        var minValue = Integer.MIN_VALUE;
        Min minAnnotation = parameter.getAnnotation(Min.class);
        if (minAnnotation != null) {
            minValue = minAnnotation.value();
        }

        var maxValue = Integer.MAX_VALUE;
        Max maxAnnotation = parameter.getAnnotation(Max.class);
        if (maxAnnotation != null) {
            maxValue = maxAnnotation.value();
        }
        return random.nextInt(minValue, maxValue);
    }

    private boolean generateRandomBoolean(Parameter parameter) {
        return random.nextBoolean();
    }

    @SuppressWarnings("MagicNumber")
    private String generateRandomString(Parameter parameter) {
        var notNullAnnotation = parameter.getAnnotation(NotNull.class);
        if (random.nextBoolean() && notNullAnnotation == null) {
            return null;
        }

        int minLength = 2;
        int maxLength = 15;
        int startSymbolIndex = 48;
        int endSymbolIndex = 122;

        var length = random.nextInt(maxLength - minLength) + minLength;
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char randomChar = (char) random.nextInt(startSymbolIndex, endSymbolIndex);
            // Ограничимся буквами латинского алфавита и цифрами
            while (!Character.isLetterOrDigit(randomChar)) {
                randomChar = (char) random.nextInt(startSymbolIndex, endSymbolIndex);
            }
            builder.append(randomChar);
        }
        return builder.toString();
    }

}

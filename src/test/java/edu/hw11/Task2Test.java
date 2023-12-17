package edu.hw11;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.junit.jupiter.api.Test;

import static net.bytebuddy.matcher.ElementMatchers.isDeclaredBy;
import static net.bytebuddy.matcher.ElementMatchers.returns;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    public void testArithmeticUtils()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Method method = ArithmeticUtils.class.getDeclaredMethod("sum", int.class, int.class);
        ArithmeticUtils instance = new ByteBuddy()
            .subclass(ArithmeticUtils.class)
            .method(ElementMatchers.named(method.getName())
                .and(isDeclaredBy(ArithmeticUtils.class)
                    .and(returns(method.getReturnType()))))
            .intercept(MethodDelegation.to(UpdatedArithmeticUtils.class))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded()
            .getDeclaredConstructor()
            .newInstance();

        int receivedResult = instance.sum(3, 2);

        assertThat(receivedResult).isEqualTo(6);
    }

    public static class ArithmeticUtils {
        public int sum(int a, int b) {
            return a + b;
        }
    }

    public static class UpdatedArithmeticUtils {
        public static int sum(int a, int b) {
            return a * b;
        }
    }
}

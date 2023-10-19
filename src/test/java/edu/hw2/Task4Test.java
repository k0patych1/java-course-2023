package edu.hw2;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static edu.hw2.Task4.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
public final class Task4Test {
    @Test
    @DisplayName("No calling method")
    public void noCallingInfoTest() {
        final CallingInfo expected = new CallingInfo(
            "jdk.internal.reflect.DirectMethodHandleAccessor",
            "invoke");

        assertThat(callingInfo()).isEqualTo(expected);
    }

    @Test
    @DisplayName("This test's class and this test's method")
    public void thisCallingInfoTest() {

        final CallingInfo expected = new CallingInfo(
                "edu.hw2.Task4Test",
                "callingMethod");

        class NestedClass {
            public void method() {
                assertThat(callingInfo()).isEqualTo(expected);
            }
        }
    }

    @Test
    @DisplayName("Name of nested class and calling method's name")
    public void callingInfoNestedTest() {
         class NestedClass {
            public static void callingMethod() {
                nestedMethod();
            }

            private static final CallingInfo expected = new CallingInfo(
                "edu.hw2.Task4Test$2NestedClass",
                "callingMethod");
            public static void nestedMethod() {
                assertThat(callingInfo()).isEqualTo(expected);
            }
        }

        NestedClass.callingMethod();
    }
}

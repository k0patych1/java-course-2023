package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    public static Class<?> createClass() {
        return new ByteBuddy()
            .subclass(Object.class)
            .name("FibonacciCalculator")
            .defineMethod("fib", long.class, Modifier.PUBLIC)
            .withParameter(int.class)
            .intercept(new Implementation.Simple(new FibonacciCalculator()))
            .make()
            .load(Task3Test.class.getClassLoader())
            .getLoaded();
    }

    private static class FibonacciCalculator implements ByteCodeAppender {
        @Override
        public @NotNull Size apply(@NotNull MethodVisitor methodVisitor, @NotNull Implementation.Context context,
            @NotNull MethodDescription methodDescription
        ) {
            Label l = new Label();

            methodVisitor.visitCode();

            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitInsn(Opcodes.ICONST_2);
            methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGE, l);

            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitInsn(Opcodes.I2L);
            methodVisitor.visitInsn(Opcodes.LRETURN);

            methodVisitor.visitLabel(l);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitInsn(Opcodes.ICONST_1);
            methodVisitor.visitInsn(Opcodes.ISUB);
            methodVisitor.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                "FibonacciCalculator",
                "fib",
                "(I)J");
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitInsn(Opcodes.ICONST_2);
            methodVisitor.visitInsn(Opcodes.ISUB);
            methodVisitor.visitMethodInsn(
                Opcodes.INVOKEVIRTUAL,
                "FibonacciCalculator",
                "fib",
                "(I)J");
            methodVisitor.visitInsn(Opcodes.LADD);
            methodVisitor.visitInsn(Opcodes.LRETURN);
            methodVisitor.visitEnd();

            return new Size(5, 2);
        }
    }

    @Test
    public void fibonacciCalculatorByteBodyTest()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?> createdClass = createClass();
        Method method = createdClass.getDeclaredMethod("fib", int.class);
        long receivedResult = (long) method.invoke(createdClass.getDeclaredConstructor().newInstance(), 10);
        assertThat(receivedResult).isEqualTo(55);
    }
}

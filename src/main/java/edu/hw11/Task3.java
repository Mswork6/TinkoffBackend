package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.scaffold.InstrumentedType;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;

public class Task3 {

    private Task3() {
    }

    public static Class<?> create() {
        DynamicType.Builder<Object> builder = new ByteBuddy()
            .subclass(Object.class)
            .name("FibCalculator");

        builder = builder.defineMethod("fib", long.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
            .withParameter(int.class)
            .intercept(new FibImplementation());

        return builder.make()
            .load(Task3.class.getClassLoader())
            .getLoaded();
    }

    public static class FibImplementation implements Implementation {

        @Override
        public @NotNull InstrumentedType prepare(@NotNull InstrumentedType instrumentedType) {
            return instrumentedType;
        }

        @Override
        public @NotNull ByteCodeAppender appender(Implementation.Target implementationTarget) {
            return new FibonacciCalculator();
        }
    }

    @SuppressWarnings("MagicNumber")
    public static class FibonacciCalculator implements ByteCodeAppender {

        @Override
        public @NotNull Size apply(MethodVisitor mv, Implementation.@NotNull Context ct,
            @NotNull MethodDescription mdp) {
            mv.visitCode();
            Label loopStart = new Label();
            Label loopEnd = new Label();
            mv.visitInsn(Opcodes.LCONST_0);
            mv.visitVarInsn(Opcodes.LSTORE, 1);
            mv.visitInsn(Opcodes.LCONST_1);
            mv.visitVarInsn(Opcodes.LSTORE, 3);
            mv.visitInsn(Opcodes.LCONST_1);
            mv.visitVarInsn(Opcodes.LSTORE, 5);
            mv.visitIincInsn(0, -1);
            mv.visitVarInsn(Opcodes.ILOAD, 0);
            mv.visitJumpInsn(Opcodes.IFEQ, loopEnd);
            mv.visitLabel(loopStart);
            mv.visitFrame(Opcodes.F_FULL, 4, new Object[]{Opcodes.INTEGER, Opcodes.LONG,
                Opcodes.LONG, Opcodes.LONG}, 0, null);
            mv.visitVarInsn(Opcodes.LLOAD, 3);
            mv.visitVarInsn(Opcodes.LLOAD, 1);
            mv.visitInsn(Opcodes.LADD);
            mv.visitVarInsn(Opcodes.LSTORE, 5);
            mv.visitIincInsn(0, -1);
            mv.visitVarInsn(Opcodes.ILOAD, 0);
            mv.visitVarInsn(Opcodes.LLOAD, 5);
            mv.visitVarInsn(Opcodes.LLOAD, 3);
            mv.visitVarInsn(Opcodes.LSTORE, 1);
            mv.visitVarInsn(Opcodes.LSTORE, 3);
            mv.visitJumpInsn(Opcodes.IFNE, loopStart);
            mv.visitFrame(Opcodes.F_FULL, 4, new Object[]{Opcodes.INTEGER, Opcodes.LONG,
                Opcodes.LONG, Opcodes.LONG}, 0, null);
            mv.visitLabel(loopEnd);
            mv.visitVarInsn(Opcodes.LLOAD, 5);
            mv.visitInsn(Opcodes.LRETURN);
            mv.visitEnd();
            return new Size(5, 7);
        }
    }
}


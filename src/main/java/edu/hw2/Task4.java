package edu.hw2;

public final class Task4 {
    private Task4() {}

    private static final int INDEX_OF_CALLING = 2;

    public static CallingInfo callingInfo() {
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();

        StackTraceElement caller = stackTrace[INDEX_OF_CALLING];
        String className = caller.getClassName();
        String methodName = caller.getMethodName();

        return new CallingInfo(className, methodName);
    }

    public record CallingInfo(String className, String methodName) {}
}

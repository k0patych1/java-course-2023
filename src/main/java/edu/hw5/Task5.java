package edu.hw5;

public final class Task5 {
    private Task5() {}

    public static boolean isValidLicensePlate(String licensePlate) {
        return licensePlate.matches("[АВЕКМНОРСТУХ]\\d{3}[АВЕКМНОРСТУХ]{2}\\d{2,3}");
    }
}

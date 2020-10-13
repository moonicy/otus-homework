package org.example;

public enum Drivers {
    CHROME("chrome"),
    FIREFOX("firefox");

    private String driverName;

    Drivers(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public String toString() {
        return driverName;
    }
}

package edu.hw2.Task2;

public class Rectangle {
    protected final int width;
    protected final int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle() {
        width = 0;
        height = 0;
    }

    public Rectangle setWidth(int newWidth) {
        return new Rectangle(newWidth, height);
    }

    public Rectangle setHeight(int newHeight) {
        return new Rectangle(width, newHeight);
    }

    public double area() {
        return width * height;
    }
}

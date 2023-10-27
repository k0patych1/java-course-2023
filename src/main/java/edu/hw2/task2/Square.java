package edu.hw2.task2;

public class Square extends Rectangle {
    public Square(int side) {
        super(side, side);
    }

    public Square() {
        super(0, 0);
    }

    @Override
    public Rectangle setWidth(int newWidth) {
        return new Rectangle(newWidth, height);
    }

    @Override
    public Rectangle setHeight(int newHeight) {
        return new Rectangle(width, newHeight);
    }
}

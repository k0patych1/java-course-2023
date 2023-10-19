package edu.hw2.Task2;

public class Square extends Rectangle {
    public Square(int side) {
        super(side, side);
    }

    @Override
    public Rectangle setWidth(int newWidth) {
        return new Square(newWidth);
    }

    @Override
    public Rectangle setHeight(int newHeight) {
        return new Square(newHeight);
    }
}

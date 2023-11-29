package edu.project2.models;

public class Cell {
    private int row;
    private int col;
    private Type type;

    public Cell(int row, int col, Type type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setCoordinate(Coordinate coordinate) {
        row = coordinate.row();
        col = coordinate.col();
    }

    public enum Type {
        WALL, PASSAGE
    }
}

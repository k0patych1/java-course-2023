package edu.project2;

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

    public Coordinate getCoordinate() {
        return new Coordinate(row, col);
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
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

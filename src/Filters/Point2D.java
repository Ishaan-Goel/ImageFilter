package Filters;

public class Point2D {
    private short red, green, blue;
    private int row, col;
    public Point2D(short red, short green, short blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.row = row;
        this.col = col;
    }
    public short getRed() {
        return red;
    }

    public short getGreen() {
        return green;
    }

    public short getBlue() {
        return blue;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRed(short red) {
        this.red = red;
    }

    public void setGreen(short green) {
        this.green = green;
    }

    public void setBlue(short blue) {
        this.blue = blue;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int[] getColor() {
        return new int[]{red, green, blue};
    }

    public double colorDistanceTo(Point other) {
        int r = this.red - other.getRed();
        int g = this.green - other.getGreen();
        int b = this.blue - other.getBlue();
        return Math.sqrt(r*r + g*g + b*b);
    }

}

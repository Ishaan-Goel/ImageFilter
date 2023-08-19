//package Filters;
//
//public class Point2 {
//    private short white;
//    private int row, col;
//    public Point2(short white) {
//        this.white = white;
//        this.row = row;
//        this.col = col;
//    }
//
//    public short getWhite(){return white;}
//    public int getRow() {
//        return row;
//    }
//
//    public int getCol() {
//        return col;
//    }
//
//    public void setWhite(short white){
//        this.white = white;
//    }
//    public void setRow(int row) {
//        this.row = row;
//    }
//
//    public void setCol(int col) {
//        this.col = col;
//    }
//
//    public int[] getColor() {
//        return new int[]{white};
//    }
//
//    public double colorDistanceTo(Point other) {
//        int r = this.red - other.getRed();
//        int g = this.green - other.getGreen();
//        int b = this.blue - other.getBlue();
//        return Math.sqrt(r*r + g*g + b*b);
//    }
//
//}

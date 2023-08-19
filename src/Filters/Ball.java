package Filters;

public class Ball {
    private int x;
    private int y;
    private int count;
    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.count = 1;
    }

    public void updateCoordinates(int x, int y) {
        this.x = (this.x * count + x) / (count + 1);
        this.y = (this.y * count + y) / (count + 1);
        count++;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

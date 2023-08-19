package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class Polychrome implements PixelFilter {
    private int t1;
    private int t2;
    private int t3;
    private int t4;
    public Polychrome() {
        t1 = 31;
        t2 = 95;
        t3 = 159;
        t4 = 223;
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if ( grid[r][c] <= t1){
                    grid[r][c] = 0;
                }else if(grid[r][c] > t1 && grid[r][c] <= 63) {
                    grid[r][c] = 63;
                }else if(grid[r][c] >= 63 && grid[r][c] < t2) {
                    grid[r][c] = 63;
                }else if(grid[r][c] > t2 && grid[r][c] <= 127) {
                    grid[r][c] = 127;
                }else if(grid[r][c] >= 127 && grid[r][c] < t3) {
                    grid[r][c] = 127;
                }else if(grid[r][c] > t3 && grid[r][c] <= 191) {
                    grid[r][c] = 191;
                }else if(grid[r][c] >= 191 && grid[r][c] < t4) {
                    grid[r][c] = 191;
                }else if(grid[r][c] > t4 && grid[r][c] <= 255) {
                    grid[r][c] = 255;
                }
                else {
                    grid[r][c] = 0;
                }
            }
        }

        img.setPixels(grid);
        return img;
    }
}


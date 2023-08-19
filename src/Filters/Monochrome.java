package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class Monochrome implements PixelFilter {
    private int threshold;

    public Monochrome() {
        threshold = 127;
    }

    @Override
    public DImage processImage(DImage img) {
        threshold++;
        if(threshold >255) threshold = 0;
        short[][] grid = img.getBWPixelGrid();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] > threshold) {
                    grid[r][c] = 255;
                } else {
                    grid[r][c] = 0;
                }
            }
        }

        img.setPixels(grid);
        return img;
    }
}


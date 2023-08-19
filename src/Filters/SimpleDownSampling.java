package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class SimpleDownSampling implements PixelFilter {

    public DImage processImage(DImage img) {
        short[][] grid1 = img.getBWPixelGrid();
        short[][] grid2 = new short[grid1.length/2][grid1[0].length/2];


        for (int i = 0; i < grid2.length; i+=2) {
            for (int j = 0; j < grid2[i].length; j+=2) {
                grid2[i][j] = grid1[i*2][j*2];
            }
        }

        img.setPixels(grid2);
        return img;
    }
}
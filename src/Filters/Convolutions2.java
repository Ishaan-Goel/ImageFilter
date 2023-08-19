package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class Convolutions2 implements PixelFilter {

    private double[][] blurKernel = {{1/9.0, 1/9.0, 1/9.0},
            {1/9.0, 1/9.0, 1/9.0},
            {1/9.0, 1/9.0, 1/9.0}};

    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();
        short[][] output = new short[grid.length][grid[0].length];

        for (int r = 1; r < grid.length - 1; r++) {
            for (int c = 1; c < grid[r].length - 1; c++) {
                short val = applyKernel(grid, r, c, blurKernel);
                if (val < 0) val = 0;
                if (val > 255) val = 255;
                output[r][c] =  val;
            }
        }

        img.setPixels(output);
        return img;
    }

    private short applyKernel(short[][] pixels, int r, int c, double[][] kernel) {
        double kernelWeight = 0;
        double sum = 0;
        int a = 0;
        int b = 0;
        for (int i = r-1; i < r + 2; i++) {
            for (int j = c-1 ; j < c + 2; j++) {
                kernelWeight += kernel[a][b];
                sum += (pixels[i][j] *  kernel[a][b]);
                b++;
            }
            b = 0;
            a++;
        }
        return (short) (sum / kernelWeight);
    }
}

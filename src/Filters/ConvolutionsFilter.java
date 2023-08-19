package Filters;

import Interfaces.PixelFilter;
import core.DImage;

import javax.swing.*;

public class ConvolutionsFilter implements PixelFilter {

   private double [][] blurKernel = {{1/9.0,1/9.0,1/9.0},
         {1/9.0,1/9.0,1/9.0},
         {1/9.0,1/9.0,1/9.0}};
    private double [][] emboss = {{-4, 0, 0},
            {0,0,0},
            {0,0,4}};

    public DImage processImage(DImage img) {
        short[][] pixels = img.getBWPixelGrid();
        short[][] output = new short[pixels.length][pixels[0].length];

        for (int r = 0; r < pixels.length; r++) {
            for (int c = 0; c < pixels[r].length; c++) {
                if (r == 0 || r == pixels.length - 1 || c == 0 || c == pixels[r].length - 1) {
                    output[r][c] = pixels[r][c];
                } else {
                    int averageVal = applyKernel(pixels, r, c, blurKernel);
                    if (averageVal < 0)  averageVal = 0;
                    if (averageVal > 255) averageVal = 255;
                    output[r][c] = (short) averageVal;
                }
            }
        }
        img.setPixels(output);
        return img;
    }

    public int applyKernel(short[][] pixels, int r, int c, double[][] kernel){
        double kWeight = 0;
        int sum = 0;
        int a = 0;
        int b = 0;
        for ( int i = r-1 ; i <= r+1 ; i++) {
            for ( int j = c-1; j <= c+1 ; j++) {
                double val = pixels[i][j];
                double weight = kernel[a][b];
                kWeight += kernel[a][b];
                sum += (int) (val*weight);
                b++;
            }
            b = 0;
            a++;
        }
        return (int) (sum/kWeight);
    }

}
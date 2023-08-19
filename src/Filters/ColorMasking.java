package Filters;

import Interfaces.Drawable;
import Interfaces.PixelFilter;
import core.DImage;
import processing.core.PApplet;

import javax.swing.*;

public class ColorMasking implements PixelFilter, Drawable {
    private static int THRESHOLD = 0;
    private static int Red = 255;
    private static int Green = 0;
    private static int Blue = 0;

    private int x, y;

    public ColorMasking() {
        String response = JOptionPane.showInputDialog("What is the threshold?");
        THRESHOLD = Integer.parseInt(response);
        x = 0;
        y = 0;
    }

    public DImage processImage(DImage img) {
        x++;
        y+=3;
        if (x > img.getWidth()) x = 0;
        if (y > img.getHeight()) y = 0;

        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();

        for (int i = 0; i < red.length; i++) {
            for (int j = 0; j < red[i].length; j++) {
                double distance = getColorDistance(red[i][j], green[i][j], blue[i][j], Red, Green, Blue);
                if (distance > THRESHOLD) {
                    red[i][j] = 0;
                    green[i][j] = 0;
                    blue[i][j] = 0;
                } else {
                    red[i][j] = 255;
                    green[i][j] = 255;
                    blue[i][j] = 255;
                }
            }
        }
        img.setColorChannels(red, green, blue);
        return img;
    }

    private static double getColorDistance(int r1, int g1, int b1, int r2, int g2, int b2) {
        int r = r1 - r2;
        int g = g1 - g2;
        int b = b1 - b2;
        return Math.sqrt(r * r + g * g + b * b);
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        window.fill(window.color(0, 255, 0));
        window.ellipse(x, y , 50, 50);
    }
}
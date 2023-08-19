package Filters;

import Interfaces.PixelFilter;
import core.DImage;

import javax.swing.*;

public class AddVerticalBlinds implements PixelFilter {
    private int width;

    public AddVerticalBlinds() {
        String response = JOptionPane.showInputDialog("What is the width?");
        width = Integer.parseInt(response);
    }

    @Override
    public DImage processImage(DImage img) {

        short[][] grid = img.getBWPixelGrid();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (j % width == 0) {
                    grid[i][j] = 0;
                }
            }
        }
        img.setPixels(grid);
        return img;
    }
    public static void drawBar(short[][] grid, int width) {
    }
}


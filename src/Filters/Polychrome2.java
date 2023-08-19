package Filters;

import Interfaces.PixelFilter;
import core.DImage;

import javax.swing.*;

public class Polychrome2 implements PixelFilter {
    private int n;
    private int count;
    public Polychrome2() {
        String response = JOptionPane.showInputDialog("What is the grayscale?");
        n = Integer.parseInt(response);
        count = 255/n;
    }

    @Override
    public DImage processImage(DImage img) {
        short[][] grid = img.getBWPixelGrid();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                    for (int j = 0; j < n; j++) {
                        if(grid[r][c] >= count*j && grid[r][c] <= (count*(j+1)-count/2)) {
                            grid[r][c] = (short) (count*j);
                        }else if(grid[r][c] > (count*(j+1)-count/2) && grid[r][c] <= count*(j+1)) {
                            grid[r][c] = (short) (count*(j+1));
                        }
                    }
            }
        }

        img.setPixels(grid);
        return img;
    }
}


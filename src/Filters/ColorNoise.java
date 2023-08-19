package Filters;


import Interfaces.PixelFilter;
import core.DImage;

import javax.swing.*;

public class ColorNoise implements PixelFilter {
    private double n;
    public ColorNoise(){
        String r = JOptionPane.showInputDialog(null, "eneter a noise probability");
        n = Double.parseDouble(r);
    }
    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();
        for (int i = 0; i < red.length; i++) {
            for (int j = 0; j < red[i].length; j++) {
                if(Math.random() < n){
                    red[i][j] = (short)(Math.random()*256);
                    green[i][j] = (short)(Math.random()*256);
                    blue[i][j] = (short)(Math.random()*256);
                }
            }
        }
        img.setColorChannels(red, green, blue); //If I swap green and red, red in the picture becomes green
        // and green become red when implementing this filter
        return img;
    }
}

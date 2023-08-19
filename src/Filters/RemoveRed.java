package Filters;

import Interfaces.PixelFilter;
import core.DImage;

public class RemoveRed implements PixelFilter {
    @Override
    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();
        for (int i = 0; i < red.length; i++) {
            for (int j = 0; j < red[i].length; j++) {
                red[i][j] = 0;

            }

        }
        img.setColorChannels(red, green, blue); //If I swap green and red, red in the picture becomes green
        // and green become red when implementing this filter
        return img;
    }
}

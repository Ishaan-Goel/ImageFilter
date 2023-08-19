package Filters;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Filters.Ball;
import processing.core.PApplet;

import Interfaces.Drawable;
import Interfaces.PixelFilter;
import core.DImage;

public class ColorMasking20 implements PixelFilter, Drawable {
    private static int THRESHOLD = 0;
    private static int Red = 255;
    private static int Green = 0;
    private static int Blue = 0;
    private static int NUMBER_OF_BALLS = 0;

    private List<Ball> balls;

    public ColorMasking20() {
        String response = JOptionPane.showInputDialog("What is the threshold?");
        THRESHOLD = Integer.parseInt(response);
        response = JOptionPane.showInputDialog("How many balls do you want to track?");
        NUMBER_OF_BALLS = Integer.parseInt(response);

        balls = new ArrayList<>();
    }

    public DImage processImage(DImage img) {
        short[][] red = img.getRedChannel();
        short[][] green = img.getGreenChannel();
        short[][] blue = img.getBlueChannel();

        balls.clear();

        for (int i = 0; i < red.length; i++) {
            for (int j = 0; j < red[i].length; j++) {
                double distance = getColorDistance(red[i][j], green[i][j], blue[i][j], Red, Green, Blue);
                if (distance <= THRESHOLD) {
                    Ball ball = findBall(j, i, balls);
                    if (ball == null) {
                        if (balls.size() < NUMBER_OF_BALLS) {
                            balls.add(new Ball(j, i));
                        }
                    } else {
                        ball.updateCoordinates(j, i);
                    }
                }
            }
        }

        img.setColorChannels(red, green, blue);
        return img;
    }

    private Ball findBall(int x, int y, List<Ball> balls) {
        for (Ball ball : balls) {
            if (ball.contains(x, y)) {
                return ball;
            }
        }
        return null;
    }

    private static double getColorDistance(int r1, int g1, int b1, int r2, int g2, int b2) {
        int r = r1 - r2;
        int g = g1 - g2;
        int b = b1 - b2;
        return Math.sqrt(r * r + g * g + b * b);
    }

    @Override
    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
        for (Ball ball : balls) {
            window.fill(window.color(0, 255, 0));
            window.ellipse(ball.getX(), ball.getY(), 50, 50);
        }
    }
}
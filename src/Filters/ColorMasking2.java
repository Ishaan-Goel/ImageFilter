//package Filters;
//
//import Interfaces.Drawable;
//import Interfaces.PixelFilter;
//import core.DImage;
//import processing.core.PApplet;
//
//import javax.swing.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ColorMasking2 implements PixelFilter, Drawable {
//    private static int THRESHOLD = 0;
//    private static int Red = 255;
//    private static int Green = 0;
//    private static int Blue = 0;
//
//    private int x, y;
//    private int avgX, avgY;
//    private int whitePixelCount = 0;
//    private ArrayList<Cluster> clusters;
//
//    private ArrayList<Point> allPoints = new ArrayList<>();
//    public ColorMasking2() {
//        String response = JOptionPane.showInputDialog("What is the threshold?");
//        THRESHOLD = Integer.parseInt(response);
//        x = 0;
//        y = 0;
//        avgX = 0;
//        avgY = 0;
//        clusters = new ArrayList<>();
//    }
//
//    public DImage processImage(DImage img) {
//        x++;
//        y += 3;
//        if (x > img.getWidth()) x = 0;
//        if (y > img.getHeight()) y = 0;
//
//        short[][] red = img.getRedChannel();
//        short[][] green = img.getGreenChannel();
//        short[][] blue = img.getBlueChannel();
//
//        avgX = 0;
//        avgY = 0;
//        whitePixelCount = 0;
//
//        for (int i = 0; i < red.length; i++) {
//            for (int j = 0; j < red[i].length; j++) {
//                double distance = getColorDistance(red[i][j], green[i][j], blue[i][j], Red, Green, Blue);
//                if (distance > THRESHOLD) {
//                    red[i][j] = 0;
//                    green[i][j] = 0;
//                    blue[i][j] = 0;
//                } else {
//                    red[i][j] = 255;
//                    green[i][j] = 255;
//                    blue[i][j] = 255;
//                    avgX += j;
//                    avgY += i;
//                    whitePixelCount++;
//                }
//            }
//        }
//
//      /*  if (whitePixelCount > 0) {
//            avgX /= whitePixelCount;
//            avgY /= whitePixelCount;
//        }*/
//
//        //========================
//        // do clustering
//        //=======================
//
//        //initialize k clusters with random centers.
//        //make point list of all white points
//        //
//        for (int i = 0; i < 10; i++) {
//            assignPointsToClusters(allPoints, clusters);
//            reCalculateCenter( clusters);
//        }
//
//        img.setColorChannels(red, green, blue);
//        return img;
//    }
//
//    private void assignPointsToClusters(ArrayList<Point> allPoints, ArrayList<Cluster> clusters) {
//        for (Point point : allPoints) {
//            Cluster closest = clusters.get(0);
//            double closestDistance = point.colorDistanceTo(closest.getCenter());
//            for (int i = 1; i < clusters.size(); i++) {
//                Cluster current = clusters.get(i);
//                double currentDistance = point.colorDistanceTo(current.getCenter());
//                if (currentDistance < closestDistance) {
//                    closest = current;
//                    closestDistance = currentDistance;
//                }
//            }
//            closest.addPoint(point);
//        }
//    }
//
//    public void reCalculateCenter(ArrayList<Cluster> clusters) {
//        for (Cluster cluster : clusters) {
//            int red = 0;
//            int green = 0;
//            int blue = 0;
//            for (Point point : cluster.points) {
//                red += point.getRed();
//                green += point.getGreen();
//                blue += point.getBlue();
//            }
//            int size = cluster.getSize();
//            cluster.center.setRed((short) (red / size));
//            cluster.center.setGreen((short) (green / size));
//            cluster.center.setBlue((short) (blue / size));
//        }
//    }
//
//    private static double getColorDistance(int r1, int g1, int b1, int r2, int g2, int b2) {
//        int r = r1 - r2;
//        int g = g1 - g2;
//        int b = b1 - b2;
//        return Math.sqrt(r * r + g * g + b * b);
//    }
//
//    @Override
//    public void drawOverlay(PApplet window, DImage original, DImage filtered) {
//        for (Cluster cluster : clusters) {
//            if (whitePixelCount > 0) {
//                window.fill(window.color(0, 255, 0));
//                window.ellipse(clusters.getCenter(), clusters.getCenter(), 50, 50);
//
//            }
//
//
//        }
//    }
//}

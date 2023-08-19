//package Filters;
//
//import Interfaces.PixelFilter;
//import core.DImage;
//
//import java.util.ArrayList;
//
//public class KMeansClustering implements PixelFilter {
//    public KMeansClustering() {
//
//    }
//    public DImage processImage(DImage img) {
//        short[][] red = img.getRedChannel();
//        short[][] green = img.getGreenChannel();
//        short[][] blue = img.getBlueChannel();
//        int k = 3;
//        ArrayList<Cluster> clusters = Cluster.createRandomClusters(k);
//        ArrayList<Point> allPoints = makePointList(red, green, blue);
//
//
//        for (int i = 0; i < 10; i++) {
//            clearClusters(clusters);
//            assignPointsToClusters(allPoints, clusters);
//            reCalculateCenter(clusters);
//        }
//        img.setColorChannels(red, green, blue);
//        return img;
//    }
//    public static ArrayList<Cluster> createRandomClusters(int k){
//        ArrayList<Cluster> clusterList = new ArrayList<Cluster>();
//        for (int i = 0; i < k; i++) {
//            short x = (short) (Math.random()*256);
//            short y = (short) (Math.random()*256);
//            short z = (short) (Math.random()*256);
//            Point p = new Point(x, y, z);
//            Cluster c = new Cluster (p);
//            clusterList.add(c);
//        }
//        return clusterList;
//    }
//
//    private ArrayList<Point> makePointList(short[][] red, short[][] green, short[][] blue) {
//        ArrayList<Point> allPoints = new ArrayList<Point>();
//        for (int i = 0; i < red.length; i++) {
//            for (int j = 0; j < red[i].length; j++) {
//                Point p = new Point(red[i][j], green[i][j], blue[i][j]);
//                allPoints.add(p);
//            }
//        }
//        return allPoints;
//    }
//
//    private void clearClusters(ArrayList<Cluster> clusters) {
//        for (Cluster cluster : clusters) {
//            cluster.clear();
//        }
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
//}

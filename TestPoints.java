import edu.princeton.cs.algs4.StdDraw;
import java.util.Arrays;
import java.util.ArrayList;

public class TestPoints
{
	public static void main(String[] args) {
        Point basePoint = new Point(1, 1);
        Point myPoint_1 = new Point(1, 7);
        Point myPoint_2 = new Point(1, 3);
        Point myPoint_3 = new Point(1, -3);
        Point myPoint_4 = new Point(-1, 3);
        Point myPoint_5 = new Point(3, 1);
        Point myPoint_6 = new Point(3, 2);
        Point myPoint_7 = new Point(5, 3);
        Point myPoint_8 = new Point(7, 4);
        Point myPoint_9 = new Point(9, 5);
        Point myPoint_10 = new Point(3, 3);
        Point myPoint_11 = new Point(3, 4);
        //check compareTo() method
        System.out.println("Compare 1: " + basePoint.compareTo(myPoint_1));
        System.out.println("Compare 2: " + basePoint.compareTo(myPoint_2));
        System.out.println("Compare 3: " + basePoint.compareTo(myPoint_3));
        System.out.println("Compare 4: " + basePoint.compareTo(myPoint_4));
        System.out.println("Compare 5: " + basePoint.compareTo(myPoint_5));
        //check slopeTo() method
        System.out.println("Slope 1: " + basePoint.slopeTo(myPoint_1));
        System.out.println("Slope 2: " + basePoint.slopeTo(myPoint_2));
        System.out.println("Slope 3: " + basePoint.slopeTo(myPoint_3));
        System.out.println("Slope 4: " + basePoint.slopeTo(myPoint_4));
        System.out.println("Slope 5: " + basePoint.slopeTo(myPoint_5));
        System.out.println("Slope 5: " + basePoint.slopeTo(myPoint_6));
        System.out.println("Slope 5: " + basePoint.slopeTo(myPoint_7));
        /***************************************************************/
        Point[] points1 = new Point[12];
        points1[0] = basePoint;
        points1[1] = myPoint_1;
        points1[2] = myPoint_2;
        points1[3] = myPoint_3;
        points1[4] = myPoint_4;
        points1[5] = myPoint_5;
        points1[6] = myPoint_6;
        points1[7] = myPoint_7;
        points1[8] = myPoint_8;
        points1[9] = myPoint_9;
        points1[10] = myPoint_10;
        points1[11] = myPoint_11;

        BruteCollinearPoints Brute = new BruteCollinearPoints(points1);
        FastCollinearPoints Fast = new FastCollinearPoints(points1);
        System.out.println("Brute number of segments: " + Brute.numberOfSegments());
        System.out.println("Fast number of segments: " + Fast.numberOfSegments());

        Point[] points2 = new Point[16];
        for(int i = 0; i < 4; i ++){
        	points2[i] = new Point(i+1, i+1);
        }
        for(int i = 4; i < 8; i ++){
        	points2[i] = new Point(i+2, i+3);
        }
        for(int i = 8; i < 12; i ++){
        	points2[i] = new Point(i+4, i+8);
        }
        for(int i = 12; i < 16; i ++){
        	points2[i] = new Point(i+12, i+1);
        }
        /*for(int i = 12; i < 15; i ++){
        	points2[i] = new Point(i+3, i+7);
        }
        points2[15] = new Point(15, 15);*/

        BruteCollinearPoints Brute_1 = new BruteCollinearPoints(points2);
        FastCollinearPoints Fast_1 = new FastCollinearPoints(points2);
        System.out.println("Brute number of segments: " + Brute_1.numberOfSegments());
        System.out.println("Fast number of segments: " + Fast_1.numberOfSegments());

        ArrayList<Point> vertices = new ArrayList<>();
        for(int i = 0; i < 4; i ++){
            vertices.add(new Point((i+1), (i+1)));
            System.out.println(i+1 + " " + (i+1));
        }
        //System.out.println(Fast_1.findPoint(vertices, (new Point(2, 2))));

        /*
        // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    StdDraw.setPenRadius(0.1);
	    for (Point p : points1) {
	        p.draw();
	    }
	    for (LineSegment line : Fast.segments()) {
	    	StdDraw.setPenRadius(0.1);
	        //line.draw();
	    }
	    StdDraw.show();*/
    }
}
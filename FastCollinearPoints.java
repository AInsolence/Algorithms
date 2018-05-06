import java.util.Arrays;
import java.util.ArrayList;

public class FastCollinearPoints {
   private ArrayList<LineSegment> Lines = new ArrayList<>();
   private boolean findPoint(ArrayList<Point> vertices, Point p)
   {
    for(Point v : vertices){
        if(v.compareTo(p) == 0) return true;
    }
    return false;
   }

   public FastCollinearPoints(Point[] _points)     // finds all line segments containing 4 or more points
   {
       /*Throw a java.lang.IllegalArgumentException if the argument to the constructor is null, if any 
        point in the array is null, or if the argument to the constructor contains a repeated point.*/
        if(_points == null) throw new IllegalArgumentException();
        for(int i = 0; i < _points.length; i++){
            if(_points[i] == null) throw new IllegalArgumentException();
            for (int j = i+1; j < _points.length; j++) {
                if(_points[j] == null) throw new IllegalArgumentException();
                if (_points[i].compareTo(_points[j]) == 0){
                    throw new IllegalArgumentException();
                } 
            }   
        }
        if(_points.length < 4) return;
        Point[] points = _points;
        ArrayList<Point> vertices = new ArrayList<>();
        
        for(int i = 0; i < points.length - 3; i++)
        {
            Arrays.sort(points);
            Point origin = points[i];
            Arrays.sort(points, i + 1, points.length, origin.slopeOrder());

            /*Check if any 3 (or more) adjacent points in the sorted order 
            have equal slopes with respect to origin*/
            Double firstPointSlope = new Double(origin.slopeTo(points[i + 1]));
            int lineSize = 2;
            for(int j = i + 2; j < points.length; j++){
                if(origin.slopeTo(points[j]) == firstPointSlope)lineSize++;
                if(j == points.length - 1 || origin.slopeTo(points[j]) != firstPointSlope){
                        if(lineSize > 3){
                            if(!findPoint(vertices,(points[j-1]))){
                                vertices.add(points[j-1]);
                                LineSegment NewLine = new LineSegment(origin, points[j]);
                                Lines.add(NewLine);
                            }
                        }
                        firstPointSlope = origin.slopeTo(points[j]);
                        lineSize = 2;
                    }
            } 
        }
    }
   public int numberOfSegments()        // the number of line segments
   {
        return Lines.size();
   }
   public LineSegment[] segments()                // the line segments
   {
        LineSegment[] segment = new LineSegment[Lines.size()];
        segment = Lines.toArray(segment);
        return segment;
   }
}
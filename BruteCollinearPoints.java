import java.util.Arrays;
import java.util.ArrayList;

public class BruteCollinearPoints {
   private ArrayList<LineSegment> Lines = new ArrayList<>();
   public BruteCollinearPoints(Point[] _points)    // finds all line segments containing 4 points
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
        //iterate through each points combination
        for(int i = 0; i < points.length - 3; i++)
        {
            for(int j = i + 1; j < points.length - 2; j++)
            {
                for(int k = j + 1; k < points.length - 1; k++)
                {
                    for(int m = k + 1; m < points.length; m++)
                    {//if 2 sopes are equal - check last
                        if(points[i].slopeTo(points[j]) == points[j].slopeTo(points[k])){
                            if(points[j].slopeTo(points[k]) == points[k].slopeTo(points[m])){
                                Point[] line = new Point[4];
                                line[0] = points[i];
                                line[1] = points[j];
                                line[2] = points[k];
                                line[3] = points[m];
                                Arrays.sort(line);
                                LineSegment NewLine = new LineSegment(line[0], line[3]);
                                if(!Lines.contains(NewLine)) Lines.add(NewLine);
                            }
                        }
                    }
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
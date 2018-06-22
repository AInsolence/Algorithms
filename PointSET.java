import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {
   private SET<Point2D> Points;

   public PointSET(){              // construct an empty set of points 
   		Points = new SET<Point2D>();
   }
   public boolean isEmpty(){      // is the set empty? 
        return Points.isEmpty();
   }
   public int size(){             // number of points in the set 
        return Points.size();
   }
   public void insert(Point2D p){ // add the point to the set (if it is not already in the set)
        if(p == null) throw new IllegalArgumentException();
        if(!Points.contains(p)){
            Points.add(p);
        }
   }
   public boolean contains(Point2D p){ // does the set contain point p? 
        if(p == null) throw new IllegalArgumentException();
        return Points.contains(p);
   }
   public void draw(){                 // draw all points to standard draw
        if(!isEmpty()){
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            for(Point2D _point : Points){
                StdDraw.point(_point.x(), _point.y());
            }
        }
   }
   public Iterable<Point2D> range(RectHV rect){ // all points that are inside the rectangle (or on the boundary)
        if(rect == null) throw new IllegalArgumentException();
        if(Points.isEmpty()) return Points;
        Stack<Point2D> result = new Stack<Point2D>();
        for(Point2D _point : Points){
                if(rect.contains(_point)) result.push(_point);
        }
        return result;
   }
   public Point2D nearest(Point2D p){    // a nearest neighbor in the set to point p; null if the set is empty 
        if(p == null) throw new IllegalArgumentException();
        Point2D result = null;
        if(!isEmpty()){
          result = Points.min();
            for(Point2D _point : Points){
                if(squaredDistance(_point, p) < squaredDistance(result, p)) result = _point;
            }
        }
        return result;
   }
   private double squaredDistance(Point2D one, Point2D two){
      double result = (two.x() - one.x())*(two.x() - one.x()) + (two.y() - one.y())*(two.y() - one.y());
      return result;
   }
   public static void main(String[] args) // unit testing of the methods (optional) 
   {

   }
}
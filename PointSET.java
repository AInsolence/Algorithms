import edu.princeton.cs.algs4.SET<Key>;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class PointSET {
   private SET<Point2D> Points;

   public PointSET(){                               // construct an empty set of points 
   		Points = new SET<Point2D>();
   }
   public boolean isEmpty(){                      // is the set empty? 
        return Points.isEmpty();
   }
   public int size(){                         // number of points in the set 
        return Points.size();
   }
   public void insert(Point2D p){              // add the point to the set (if it is not already in the set)
        if(p == null) throw new IllegalArgumentException();
        if(!Points.contains(p)){
            Points.add(p);
        }
   }
   public boolean contains(Point2D p){           // does the set contain point p? 
        if(p == null) throw new IllegalArgumentException();
        return Points.contains(p);
   }
   public void draw(){                         // draw all points to standard draw
        if(!isEmpty()){
            for(Point2D _point : Points){
                StdDraw.setPenColor(StdDraw.BLACK);
                StdDraw.setPenRadius(0.01);
                StdDraw.point(_point.x(), _point.y());
            }
        }
   }
   public Iterable<Point2D> range(RectHV rect){ // all points that are inside the rectangle (or on the boundary)
        if(rect == null) throw new IllegalArgumentException();
        if(Points.isEmpty()) return Points;
        return Points;//TODO
   }
   public Point2D nearest(Point2D p){             // a nearest neighbor in the set to point p; null if the set is empty 
        if(p == null) throw new IllegalArgumentException();
        if(!isEmpty()){
        }
        else return null;
   }
   public static void main(String[] args)                  // unit testing of the methods (optional) 
}
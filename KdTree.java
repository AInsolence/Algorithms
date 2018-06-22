import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree{
	private static class Node implements Comparable<Node>{
	   public Point2D point;
	   public Node lb;        // the left/bottom subtree
	   public Node rt;        // the right/top subtree
      public boolean verticalOriented = true;
      public double xmin = 0.0;
      public double ymin = 0.0;
      public double xmax = 1.0;
      public double ymax = 1.0;


      public Node(Point2D _point){
         point = _point;
         lb = null;
         rt = null;
      }
      public int compareTo(Node that){
         if(this.verticalOriented){
            return Point2D.X_ORDER.compare(this.point, that.point);
         }
         else{
            return Point2D.Y_ORDER.compare(this.point, that.point);
         }
      }
	}
   private Node _root = null;
   private int _size = 0;
   private boolean orientation = true;
   private Point2D champion = null;
   private double championDistance = 1.0;

   private Iterable<Node> Points(){
      Stack<Node> points = new Stack<>();
      inorder(_root, points);
      return points;
   }
   private void inorder(Node x, Stack<Node> points){
      if(x == null) return;
      inorder(x.lb, points);
      points.push(x);
      inorder(x.rt, points);
   }
   private void rangeTraversal(Node x, Stack<Point2D> result, RectHV _rect){
      if(x == null) return;
      if(SplitingLineIntersect(x, _rect) == 0) return;
      if(SplitingLineIntersect(x, _rect) == -1){
         rangeTraversal(x.lb, result, _rect);
      }
      else if(SplitingLineIntersect(x, _rect) == 1){
         rangeTraversal(x.rt, result, _rect);
      }
      else{ //(SplitingLineIntersect(x, _rect) == 2)
         if(_rect.contains(x.point)) result.push(x.point); 
         rangeTraversal(x.lb, result, _rect);
         rangeTraversal(x.rt, result, _rect);
      }
   }
   private int SplitingLineIntersect(Node _node, RectHV _rect){
      boolean onTheLineOne = false;
      boolean onTheLineTwo = false;

      double nX = _node.point.x();
      double nY = _node.point.y();
      double nXmin = _node.xmin;
      double nXmax = _node.xmax;
      double nYmin = _node.ymin;
      double nYmax = _node.ymax;

      double rXmin = _rect.xmin();
      double rXmax = _rect.xmax();
      double rYmin = _rect.ymin();
      double rYmax = _rect.ymax();

      if(_node.verticalOriented){// check line intersection possibility by line coordinate
         if(nX >= rXmin && nX <= rXmax) onTheLineOne = true;
         if(nYmin <= rYmax && nYmax >= rYmin) onTheLineTwo = true;
      } 
      else{
         if(nY >= rYmin && nY <= rYmax) onTheLineOne = true;
         if(nXmin <= rXmax && nXmax >= rXmin) onTheLineTwo = true;
      }
      // check all possibilities
      if (onTheLineOne && !onTheLineTwo) return 0;//does not intersect
      else if(onTheLineOne && onTheLineTwo) return 2;//intersects
      else{// rectangles intersects
         if(_node.verticalOriented){
            if(nX > rXmax) return -1;// go left from the line
            else return 1;// go right from the line
         }
         else{
            if(nY > rYmax) return -1;// go to bottom from the line
            else return 1;// go to top from the line
         }
      }
   }
   private void nearestTraversal(Node x, Point2D p){
      if(x == null) return;
      RectHV rect = new RectHV(x.xmin, x.ymin, x.xmax, x.ymax);
      if(rect.distanceSquaredTo(p) > championDistance) return;
      nearestTraversal(x.lb, p);
      double newDistance = squaredDistance(x.point, p);
      if(newDistance < championDistance){
         champion = x.point;
         championDistance = newDistance;
         //System.out.println("new nearest neighbor at " + champion.x() + " " + champion.y());
      }
      nearestTraversal(x.rt, p);
   }
   private double squaredDistance(Point2D one, Point2D two){
      double result = (two.x() - one.x())*(two.x() - one.x()) + (two.y() - one.y())*(two.y() - one.y());
      return result;
   }
   public KdTree(){              // construct an empty set of points
   }
   public boolean isEmpty(){      // is the set empty? 
        return _size == 0;
   }
   public int size(){             // number of points in the set 
        return _size;
   }
   public void insert(Point2D p){ // push the point to the set (if it is not already in the set)
        if(p == null) throw new IllegalArgumentException();
        Node insertedNode = new Node(p);
        if(!contains(p)){
            _root = insert(_root, insertedNode);
            _size++;
        }
   }
   private Node insert(Node x, Node insertedNode){
      if(x == null){
         insertedNode.verticalOriented = orientation;
         return insertedNode;
      }
      orientation = !x.verticalOriented;
      int cmp = x.compareTo(insertedNode);
      if(cmp > 0){
         if(x.verticalOriented) insertedNode.xmax = x.point.x();
         else insertedNode.ymax = x.point.y();
         x.lb = insert(x.lb, insertedNode);
      } 
      else{
         if(x.verticalOriented) insertedNode.xmin = x.point.x();
         else insertedNode.ymin = x.point.y();
         x.rt = insert(x.rt, insertedNode);
      }
      return x;
   }
   public boolean contains(Point2D p){ // does the set contain point p? 
        if(p == null) throw new IllegalArgumentException();
        if(_root == null) return false;
        Node x = _root;
        double pX = p.x();
        double pY = p.y();
        while(x != null){
         int cmp;
         if(x.verticalOriented) cmp = compareCoordinates(pX, x.point.x());
         else cmp = compareCoordinates(pY, x.point.y());
         if(cmp > 0) x = x.rt;
         else if(cmp < 0) x = x.lb;
         else {
            if(x.point.equals(p)) return true;
            else x = x.rt;
         }
        }
        return false;
   }
   private int compareCoordinates(double queryPoint, double thatPoint){
      if(queryPoint > thatPoint) return 1;
      else if(queryPoint < thatPoint) return -1;
      else return 0;
   }
   public void draw(){                // draw all points to standard draw
      if(!isEmpty()){
         //System.out.println("Size of the tree equals: " + size());
         //int _inSize = 0;
          for(Node _node : Points()){
            //_inSize++;
             StdDraw.setPenRadius(0.003);
             if(_node.verticalOriented){
                StdDraw.setPenColor(StdDraw.RED);
               StdDraw.line(_node.point.x(), _node.ymin, _node.point.x(), _node.ymax);
             }
            else{
                StdDraw.setPenColor(StdDraw.BLUE);
               StdDraw.line(_node.xmin, _node.point.y(), _node.xmax, _node.point.y());
            }
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            StdDraw.point(_node.point.x(), _node.point.y());
         }
         //System.out.println("InSize equals: " + _inSize);
      }
   }
   public Iterable<Point2D> range(RectHV rect){ // all points that are inside the rectangle (or on the boundary)
        if(rect == null) throw new IllegalArgumentException();
        Stack<Point2D> result = new Stack<>();
        rangeTraversal(_root, result, rect);
        return result;
   }
   public Point2D nearest(Point2D p){    // a nearest neighbor in the set to point p; null if the set is empty 
        if(p == null) throw new IllegalArgumentException();
        if(isEmpty()) return null;
        champion = _root.point;
        championDistance = squaredDistance(champion, p);
        nearestTraversal(_root, p);
        return champion;
   }
   public static void main(String[] args) // unit testing of the methods (optional) 
   {
      Point2D point_1 = new Point2D(0.5, 0.5);
      Point2D point_2 = new Point2D(0.25, 0.25);
      Point2D point_3 = new Point2D(0.75, 0.75);
      Point2D point_4 = new Point2D(0.25, 0.75);
      Point2D point_5 = new Point2D(0.15, 0.6);
      //Point2D point_1 = new Point2D(0.75, 0.5);
      //Point2D point_2 = new Point2D(0.25, 0.25);
      //Point2D point_3 = new Point2D(0.75, 0.25);
      //Point2D point_4 = new Point2D(0.75, 0.75);
      //Point2D point_5 = new Point2D(0.25, 0.0);
      //Point2D point_6 = new Point2D(0.75, 1.0);
      //Point2D point_7 = new Point2D(0.0 ,0.5);
      //Point2D point_8 = new Point2D(0.0 ,0.25);
      //Point2D point_9 = new Point2D(0.5 ,0.0);
      //Point2D point_10 = new Point2D(0.5 ,0.25);
      //Point2D point_6 = new Point2D(0.7, 0.4);
      KdTree Collection = new KdTree();
      
      Collection.insert(point_1);
      //System.out.println(Collection._root.point.y());
      //System.out.println(Collection.orientation);
      Collection.insert(point_2);
      //System.out.println(Collection._root.point.y());
      //System.out.println(Collection.orientation);
      Collection.insert(point_3);
      //System.out.println(Collection._root.point.y());
      //System.out.println(Collection.orientation);
      Collection.insert(point_4);
      Collection.insert(point_5);
      //Collection.insert(point_6);
      //Collection.insert(point_7);
      //Collection.insert(point_8);
      //Collection.insert(point_9);
      //Collection.insert(point_10);

      System.out.println(Collection.contains(point_1));
      System.out.println(Collection.contains(point_2));
      System.out.println(Collection.contains(point_3));
      System.out.println(Collection.contains(point_4));
      System.out.println(Collection.contains(point_5));
      //System.out.println(Collection.contains(point_6));
      //System.out.println(Collection.contains(point_7));
      //System.out.println(Collection.contains(point_8));
      //System.out.println(Collection.contains(point_9));
      //System.out.println(Collection.contains(point_10));
      
      
      Collection.draw();
      RectHV queryRectangle = new RectHV(0.2, 0.15, 0.4, 0.8);
      for(Point2D _point : Collection.range(queryRectangle)){
         System.out.println(_point.x() + " " + _point.y());
      }
      Point2D nearP = new Point2D(0.9, 0.7);
      StdDraw.setPenColor(StdDraw.RED);
      StdDraw.setPenRadius(0.02);
      StdDraw.point(nearP.x(), nearP.y());
      nearP = Collection.nearest(nearP);
      System.out.print(nearP.x() + " " + nearP.y());
   }
}
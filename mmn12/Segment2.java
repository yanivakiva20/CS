/**
 * The Segment2 class
 *  Segment2 represents a line (parallel to the x-axis) using a center point and length.
 *
 * @author  Yaniv Akiva
 * @version 1.0
 * @since   2020-11-28
 */
public class Segment2 {
    // private variables of the class
    private Point _poCenter;
    private double _length;

    public Segment2(Point left, Point right){
        /**
         * Constructs a new segment using a left point and right point.
         */
        this._poCenter = new Point((left.getX()+right.getX())/2, left.getY());
        this._length = Math.abs(right.getX() - left.getX());
    }
    public Segment2(Point center, double length){
        /**
         * Constructs a new segment using a center point and the segment length.
         */
        this._poCenter = center;
        this._length = length;
    }
    public Segment2(double lx, double ly, double rx, double ry){
        /**
         * Constructs a new segment using 4 specified x y coordinates: two coordinates for the left point and two coordinates for the right point.
         */
        this._poCenter = new Point((lx+rx)/2, ly);
        this._length = Math.abs(rx - lx);
    }
    public Segment2(Segment2 segment){
        /**
         * Copy Constructor.
         */
        this._poCenter = segment._poCenter;
        this._length = segment._length;
    }
    public Point getPoLeft(){
        /**
         * - Returns the left point of the segment.
         *
         * @param
         * @return Point left
         */
        return new Point(this._poCenter.getX() - (this._length/2), this._poCenter.getY());
    }
    public Point getPoRight(){
        /**
         * - Returns the right point of the segment.
         *
         * @param
         * @return Point right
         */
        return new Point(this._poCenter.getX() + (this._length/2), this._poCenter.getY());
    }
    private Point get_poCenter(){
        /**
         * - Returns the center point of the segment.
         *
         * @param
         * @return Point center
         */
        return new Point(this._poCenter);
    }
    public double getLength(){
        /**
         * - Returns the segment length.
         *
         * @param
         * @return double length
         */
        return this._length;
    }
    public boolean equals(Segment2 segment){
        /**
         * - Check if the reference segment is equal to this segment.
         *
         * @param  Segment2 segment
         * @return boolean
         */
        return this._poCenter.equals(segment.get_poCenter()) && this._length == segment.getLength();
    }
    public boolean isAbove(Segment2 segment){
        /**
         * - Check if this segment is above a reference segment.
         *
         * @param  Segment2 segment
         * @return boolean
         */
        return this._poCenter.isAbove(segment.get_poCenter());
    }
    public boolean isUnder(Segment2 segment){
        /**
         * -  Check if this segment is under a reference segment.
         *
         * @param  Segment2 segment
         * @return boolean
         */
        return segment.get_poCenter().isAbove(this._poCenter);
    }
    public boolean isLeft(Segment2 segment){
        /**
         * -  Check if this segment is left of a received segment.
         *
         * @param  Segment2 segment
         * @return boolean
         */
        return this._poCenter.isLeft(segment.get_poCenter());
    }
    public boolean isRight(Segment2 segment){
        /**
         * - Check if this segment is right of a received segment.
         *
         * @param  Segment2 segment
         * @return boolean
         */
        return segment.isLeft(this);
    }
    public void moveHorizontal(double delta){
        /**
         * - Move the segment horizontally by delta.
         *
         * @param  double delta
         * @return
         */
        if (this.getPoLeft().getX() + delta >= 0 && this.getPoRight().getX() + delta >= 0){
            this._poCenter.setX(this._poCenter.getX() + delta);
        }
    }
    public void moveVertical(double delta){
        /**
         * - Move the segment vertically by delta.
         *
         * @param  double delta
         * @return
         */

        if (this._poCenter.getY() + delta >= 0){
            this._poCenter.setY(this._poCenter.getY() + delta);
        }
    }
    public void changeSize(double delta){
        /**
         * - Change the segment size by moving the right point by delta.
         *
         * @param  double delta
         * @return
         */
        if (this._length + delta >= 0){
            this._length += delta;
        }
    }
    public boolean pointOnSegment(Point p){
        /**
         * - Check if a point is located on the segment.
         *
         * @param  Point p
         * @return boolean
         */
        return this.getPoLeft().isLeft(p) && this.getPoRight().isRight(p) && (!this.getPoLeft().isAbove(p) && !this.getPoLeft().isUnder(p));
    }
    public boolean isBigger(Segment2 segment){
        /**
         * -   Check if this segment is bigger than a reference segment.
         *
         * @param  Segment2 segment
         * @return boolean
         */
        return this.getLength() > segment.getLength();
    }
    public double overlap(Segment2 segment){
        /**
         * -  Returns the overlap size of this segment and a reference segment.
         *
         * @param  Segment2 segment
         * @return overlap of segments
         */
        if (this.getPoRight().isLeft(segment.getPoLeft()) || this.getPoLeft().isRight(segment.getPoRight())){
            return 0.0;
        }
        return Math.abs(Math.min(this.getPoRight().getX(), segment.getPoRight().getX()) - Math.max(this.getPoLeft().getX(), segment.getPoLeft().getX()));
    }
    public double trapezePerimeter(Segment2 segment){
        /**
         * - Compute the trapeze perimeter, which constructed by this segment and a reference segment.
         *
         * @param  Segment2 segment
         * @return permieter of trapeze
         */
        double height = segment.getPoLeft().getY() - this.getPoLeft().getY();
        double right_side = Math.sqrt(Math.pow(height, 2) + Math.pow(Math.abs(this.getPoRight().getX() - segment.getPoRight().getX()), 2));
        double left_side = Math.sqrt(Math.pow(height, 2) + Math.pow(Math.abs(this.getPoLeft().getX() - segment.getPoLeft().getX()), 2));
        return right_side + left_side + segment.getLength() + this.getLength();
    }
    public String toString() {
        /**
         * - Return a string representation of this segment in the format (3.0,4.0)---(3.0,6.0).
         *
         * @return string representation of segment
         */

        return this.getPoLeft()
                + "---"
                + this.getPoRight();
    }
}

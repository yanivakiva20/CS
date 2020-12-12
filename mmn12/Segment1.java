/**
 * The Segment1 class
 * represents a line (parallel to the x-axis) using two Points.
 *
 * @author  Yaniv Akiva
 * @version 1.0
 * @since   2020-11-28
 */

public class Segment1 {
    // private varibales of the class
    private Point _poLeft;
    private Point _poRight;

    public Segment1(Point left, Point right){
        /**
         * Constructs a new segment using two Points.
         */
        this._poLeft = left;
        this._poRight = new Point(right.getX(), left.getY());
    }
    public Segment1(double lx, double ly, double rx, double ry){
        /**
         * Constructs a new segment using 4 specified x y coordinates: Two coordinates for the left point and two coordinates for the right point.
         */
        this._poLeft = new Point(lx, ly);
        this._poRight = new Point(rx, ly);
    }
    public Segment1(Segment1 segment){
        /**
         * Copy Constructor.
         */
        this._poRight = segment._poRight;
        this._poLeft = segment._poLeft;
    }
    public Point getPoLeft(){
        /**
         * - Returns the left point of the segment.
         *
         * @param
         * @return Point left
         */
        return new Point(this._poLeft);
    }
    public Point getPoRight(){
        /**
         * - Returns the right point of the segment.
         *
         * @param
         * @return Point left
         */
        return new Point(this._poRight);
    }
    public double getLength(){
        /**
         * - Returns the segment length.
         *
         * @param
         * @return double length
         */
        return Math.abs(this._poLeft.getX() - this._poRight.getX());
    }
    public boolean equals(Segment1 segment){
        /**
         * - Check if the reference segment is equal to this segment.
         *
         * @param  Segment1 segment
         * @return boolean
         */
        return this._poRight.equals(segment._poRight) && this._poLeft.equals(segment._poLeft);
    }
    public boolean isAbove(Segment1 segment){
        /**
         * - Check if the reference segment is above to this segment.
         *
         * @param  Segment1 segment
         * @return boolean
         */
        return this._poLeft.isAbove(segment.getPoLeft());
    }
    public boolean isUnder(Segment1 segment){
        /**
         * - Check if the reference segment is under to this segment.
         *
         * @param  Segment1 segment
         * @return boolean
         */
        return segment.getPoLeft().isAbove(this._poLeft);
    }
    public boolean isLeft(Segment1 segment){
        /**
         * - Check if the reference segment is left to this segment.
         *
         * @param  Segment1 segment
         * @return boolean
         */
        return this._poRight.isLeft(segment.getPoLeft());
    }
    public boolean isRight(Segment1 segment){
        /**
         * - Check if the reference segment is right to this segment.
         *
         * @param  Segment1 segment
         * @return boolean
         */
        return this._poLeft.isRight(segment.getPoRight());
    }
    public void moveHorizontal(double delta){
        /**
         * - Move the segment horizontally by delta.
         *
         * @param  double delta
         * @return
         */
        if (this._poLeft.getX() + delta >= 0 && this._poRight.getX() + delta >= 0){
            this._poRight.setX(this._poRight.getX() + delta);
            this._poLeft.setX(this._poLeft.getX() + delta);
        }
    }
    public void moveVertical(double delta){
        /**
         * - Move the segment vertically by delta.
         *
         * @param  double delta
         * @return
         */
        if (this._poLeft.getY() + delta >= 0 && this._poRight.getY() + delta >= 0){
            this._poRight.setY(this._poRight.getY() + delta);
            this._poLeft.setY(this._poLeft.getY() + delta);
        }
    }
    public void changeSize(double delta){
        /**
         * - Change the segment size by moving the right point by delta.
         *
         * @param  double delta
         * @return
         */
        if (this._poRight.getX() + delta >= this._poLeft.getX()){
            this._poRight.setX(this._poRight.getX() + delta);
        }
    }
    public boolean pointOnSegment(Point p){
        /**
         * - Check if a point is located on the segment.
         *
         * @param  Point p
         * @return boolean
         */
        return this._poLeft.isLeft(p) && this._poRight.isRight(p) && (!this._poLeft.isAbove(p) && !this._poLeft.isUnder(p));
    }
    public boolean isBigger(Segment1 segment){
        /**
         * -   Check if this segment is bigger than a reference segment.
         *
         * @param  Segment1 segment
         * @return boolean
         */
        return this.getLength() > segment.getLength();
    }
    public double overlap(Segment1 segment){
        /**
         * -  Returns the overlap size of this segment and a reference segment.
         *
         * @param  Segment1 segment
         * @return overlap of segments
         */
        if (this._poRight.isLeft(segment.getPoLeft()) || this._poLeft.isRight(segment.getPoRight())){
            return 0.0;
        }
        return Math.abs(Math.min(this._poRight.getX(), segment.getPoRight().getX()) - Math.max(this._poLeft.getX(), segment.getPoLeft().getX()));
    }
    public double trapezePerimeter(Segment1 segment){
        /**
         * - Compute the trapeze perimeter, which constructed by this segment and a reference segment.
         *
         * @param  Segment2 segment
         * @return permieter of trapeze
         */
        double height = segment.getPoLeft().getY() - this._poLeft.getY();
        double right_side = Math.sqrt(Math.pow(height, 2) + Math.pow(Math.abs(this._poRight.getX() - segment.getPoRight().getX()), 2));
        double left_side = Math.sqrt(Math.pow(height, 2) + Math.pow(Math.abs(this._poLeft.getX() - segment.getPoLeft().getX()), 2));
        return right_side + left_side + segment.getLength() + this.getLength();
    }
    public String toString() {
        /**
         * - Return a string representation of this segment in the format (3.0,4.0)---(3.0,6.0).
         *
         * @return string representation of segment
         */
        return _poLeft
                + "---"
                + _poRight;
    }
}
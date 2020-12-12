/**
 * The Point class is an application that
 * Represents 2 dimensional points.
 *
 * @author  Yaniv Akiva
 * @version 1.0
 * @since   2020-11-28
 */


public class Point {
    private double _radius;
    private double _alpha;
    final private double _to_degrees = 180.0/Math.PI;
    final private double _to_radians = Math.PI/180.0;

    public Point(double x, double y){
        /**
         * Constructor for objects of class Point.
         */
        this._radius = this.calculate_radius(x, y);
        this._alpha = this.calculate_alpha(x, y);
    }
    public Point(Point point){
        /**
         * Constructor for objects of class Point.
         */
        this._alpha = point._alpha;
        this._radius = point._radius;
    }
    private double calculate_radius(double x, double y){
        /**
         * - calculates the radius by x and y received
         *
         * @param double x, y
         * @return double radius
         */
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)); // return radius
    }
    private double calculate_alpha(double x, double y){
        /**
         * - calculates the alpha by x and y received
         *
         * @param double x, y
         * @return double alpha
         */
        return Math.atan(y/x) * this._to_degrees; // return alpha
    }
    public double getX(){
        /**
         * - This method returns the x coordinate of the point.
         *
         * @param
         * @return double x
         */
        //
        return Math.cos(this._alpha * this._to_radians) * this._radius;
    }
    public double getY(){
        /**
         * - This method returns the y coordinate of the point.
         *
         * @param
         * @return double y
         */
        return Math.sin(this._alpha * this._to_radians) * this._radius;
    }
    private double get_radius(){
        /**
         * - This method returns the radius
         *
         * @param
         * @return double radius
         */
        //
        return this._radius;
    }
    private double get_alpha(){
        /**
         * - This method returns the alpha
         *
         * @param
         * @return double radius
         */
        return this._alpha;
    }
    public void setX(double x){
        /**
         * - This method sets the x coordinate of the point.
         *
         * @param x
         * @return
         */
        if(x >= 0) {
            this._radius = this.calculate_radius(x, this.getY());
            this._alpha = this.calculate_alpha(x, this.getY()) * this._to_degrees;
        }
    }
    public void setY(double y){
        /**
         * - This method sets the y coordinate of the point.
         *
         * @param y
         * @return
         */
        if(y >= 0) {
            this._radius = this.calculate_radius(this.getX(), y);
            this._alpha = this.calculate_alpha(this.getX(), y) * this._to_degrees;
        }
    }
    public boolean equals(Point point){
        /**
         * - Check if the given point is equal to this point.
         *
         * @param  Point point
         * @return boolean
         */
        return this._radius == point.get_radius() && this._alpha == point.get_alpha();
    }
    public boolean isAbove(Point point){
        /**
         * - Check if this point is above a received point.
         *
         * @param  Point point
         * @return boolean
         */
        return this.getY() > point.getY();
    }
    public boolean isUnder(Point point){
        /**
         * - Check if this point is below a received point.
         *
         * @param  Point point
         * @return boolean
         */
        return point.isAbove(this);
    }
    public boolean isLeft(Point point){
        /**
         * - Check if this point is left of a received point.
         *
         * @param  Point point
         * @return boolean
         */
        return this.getX() < point.getX();
    }
    public boolean isRight(Point point){
        /**
         * - Check if this point is right of a received point.
         *
         * @param  Point point
         * @return boolean
         */
        return point.isLeft(this);
    }
    public double distance(Point point){
        /**
         * - Check the distance between this point and a given point.
         *
         * @param  Point point
         * @return double distance
         */
        return Math.sqrt(Math.pow(this.getY() - point.getY(), 2) + Math.pow(this.getX() - point.getX(), 2));
    }
    public void move(double dx, double dy){
        /**
         * - Moves a point.
         *
         * @param  double dx, dy
         * @return
         */
        double x = this.getX();
        double y = this.getY();
        if(x + dx >= 0 && y + dy >= 0){
            this.setX(this.getX() + dx);
            this.setY(this.getY() + dy);
        }
    }
    public String toString() {
        /**
         * - Returns a string representation of Point in the format (x,y).
         *
         * @return string representation of Point
         */
        return "(" + Math.round(this.getX()) + "," + Math.round(this.getY()) + ')';
    }
}

package hwplace;

public class Circle {

	public double radius;
	public Point center;

	public Circle() {// constructor that initializes the radius to 0 and center to point 0,0)
		radius = 0;
		center = new Point(0, 0);
	}

	public Circle(double r) {// This constructor initializes the radius with a given value and center point
								// to (0,0)
		setRadius(r);// Use the setRadius methods to handle radius
		center = new Point(0, 0);
	}

	public Circle(Point c, double r) {// Constructors that initializes the radius and center point with given values
		center = c;
		setRadius(r);// Use the setRadius methods to handle radius
	}
	// Mutator that sets the radius of the circle, it makes sure the value is not
	// negative

	public void setRadius(double r) {
		if (r < 0) {
			radius = 0;
		} else {
			radius = r;
		}
	}

	public double getArea() {// area formula
		return 3.14159 * radius * radius;
	}

	public double getCircumference() {// circumference of the circle
		return 2 * 3.14159 * radius;
	}

	public boolean intersect(Point p) {
		double dx = p.X - center.X;// calculates horizontal distance between the point and the center
		double dy = p.Y - center.Y;// calculates vertical distance between the point and the center
		double distanceSquared = dx * dx + dy * dy;// calculate the square of the distance between the point and the
													// center
		return distanceSquared <= radius * radius;// check if the distance is less or equal to the radius squared
	}

	public boolean intersect(Circle other) {// Method that checks if another circle intersects with this circle
		double dx = other.center.X - center.X;// calculates horizontal distance
		double dy = other.center.Y - center.Y;// calculates vertical distances
		double distanceSquared = dx * dx + dy * dy;// calculates the square of the distances
		double sumOfRadii = radius + other.radius;// calculates the sum of the two radii
		return distanceSquared <= sumOfRadii * sumOfRadii;// check if the distance is less than or equal to the sum of
															// the radii squared
	}
}

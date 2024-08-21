package hwplace;

public class Point {
	public double X;// x coordinates
	public double Y;// y coordinates

	public Point() {// constructor that sets x and y values to 0
		X = 0;
		Y = 0;

	}

	public Point(double x, double y) {// constructor that allows caller to specify the x and y coordinates of the
										// point
		X = x;
		Y = y;

	}

}
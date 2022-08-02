
public interface Shape {
	double getPerimeter();
    double getArea();
}

class Ellipse implements Shape {
    private double radius1;
    private double radius2;
    private double perimeter;	//Trades a bit more space to avoid repeating calculations(time complexity)
    private double area;

    Ellipse(double inputRadius1 , double inputRadius2) {	//Base code if the Shape needed to be instantiated and used repeatedly.
        this.radius1 = inputRadius1;
        this.radius2 = inputRadius2;
        this.perimeter = -1;
        this.area = -1;
    }

    @Override
	public double getPerimeter() {
    	if(this.perimeter == -1)	//Calculates and stores the result for quick retrieval.
    		this.perimeter = computePerimeter(this.radius1, this.radius2);	//Uses the static method to calculate avoiding duplicate code

    	return this.perimeter;
    }

    @Override
	public double getArea() {
    	if(this.area == -1)
    		this.area = computeArea(this.radius1, this.radius2);

        return this.area;
    }

    static double computePerimeter(double inputRadius1 , double inputRadius2) {	//Using Ramanujan 2nd Approximation for a extremely precise approximation
    	try {
    		double h = Math.pow(Math.abs(inputRadius1 - inputRadius2),2)/Math.pow(inputRadius1 + inputRadius2,2);	//Possible to divide by zero if a length is negative
    		return Math.PI*(inputRadius1 + inputRadius2)*(1.0 + (3*h/(10 + Math.sqrt(4 - 3*h))));
    	} catch (ArithmeticException e) {
    		System.out.println("Ellipse Input Data inculdes a negative number");
    		return computePerimeter(Math.abs(inputRadius1), Math.abs(inputRadius2));	//Forces the doubles to be (Can be done in the calculateShape method).
    	}
    }

    /*
    //Method for another more precise way to Calculate Perimeter
    //If more accurate calculations are needed, this method can be used at the cost of more time complexity
    static double computeExactPerimeter(double inputRadius1 , double inputRadius2) {	//Best way to get a more precise calculation is up to debate (need to ask senior)
    	try {	//Place holder code, just a copy of the normal method to compute, Method's logic can be changed as required
    		double h = Math.pow(Math.abs(inputRadius1 - inputRadius2),2)/Math.pow(inputRadius1 + inputRadius2,2);	//Can use integration or infinite series
    		return Math.PI*(inputRadius1 + inputRadius2)*(1.0 + (3*h/(10 + Math.sqrt(4 - 3*h))));
    	} catch (ArithmeticException e) {
    		System.out.println("Ellipse Input Data inculdes a negative number");
    		return computePerimeter(Math.abs(inputRadius1), Math.abs(inputRadius2));
    	}
    }
    */

    static double computeArea(double inputRadius1 , double inputRadius2) {
    	return Math.PI*inputRadius1*inputRadius2;
    }
}

class Triangle implements Shape {
	private double side1;
	private double side2;
	private double side3;
	private double perimeter;
	private double area;

    Triangle(double inputSide1, double inputSide2, double inputSide3) {
        this.side1 = inputSide1;
        this.side2 = inputSide2;
        this.side3 = inputSide3;
        this.perimeter = -1;
        this.area = -1;
    }

    @Override
	public double getPerimeter() {
    	if(this.perimeter == -1)
    		this.perimeter = computePerimeter(this.side1, this.side2, this.side3);

    	return this.perimeter;
    }

    @Override
	public double getArea() {
    	if(this.area == -1)
    		this.area = computeArea(this.side1, this.side2, this.side3);

        return this.area;
    }

    static double computePerimeter(double inputSide1, double inputSide2, double inputSide3) {
    	return inputSide1 + inputSide2 + inputSide3;
    }

    static double computeArea(double inputSide1, double inputSide2, double inputSide3) {	//Heron's Formula to calculate area from 3 sides
    	double s = (inputSide1 + inputSide2 + inputSide3)/2.0;	//Store this to potentially take advantage of cache make the calculation faster.
    	return Math.sqrt(s*(s-inputSide1)*(s-inputSide2)*(s-inputSide3));	//Used thos version of the formula for the above reason.
    }
}

class Circle implements Shape {
	private double radius;
	private double perimeter;
	private double area;

    Circle(double inputRadius) {
    	 this.radius = inputRadius;
         this.perimeter = -1;
         this.area = -1;
    }

    @Override
	public double getPerimeter() {
    	if(this.perimeter == -1)
    		this.perimeter = computePerimeter(this.radius);

    	return this.perimeter;
    }

    @Override
	public double getArea() {
    	if(this.area == -1)
    		this.area = computeArea(this.radius);

        return this.area;
    }

    static double computePerimeter(double inputRadius) {
    	return 2.0*Math.PI*inputRadius;
    }

    static double computeArea(double inputRadius) {
    	return Math.PI*Math.pow(inputRadius, 2);
    }
}

class Rectangle implements Shape {
	private double length;
	private double width;
	private double perimeter;
	private double area;

    Rectangle(double inputLength, double inputWidth) {
        this.length = inputLength;
        this.width = inputWidth;
        this.perimeter = -1;
        this.area = -1;
    }

    @Override
	public double getPerimeter() {
    	if(this.perimeter == -1)
    		this.perimeter = computePerimeter(this.length, this.width);

    	return this.perimeter;
    }

    @Override
	public double getArea() {
    	if(this.area == -1)
    		this.area = computeArea(this.length, this.width);

        return this.area;
    }

    static double computePerimeter(double inputLength, double inputWidth) {
    	return 2.0*(inputLength + inputWidth);
    }

    static double computeArea(double inputLength, double inputWidth) {
    	return inputLength*inputWidth;
    }
}

class Square implements Shape {
	private double side;
	private double perimeter;
	private double area;

    Square(double inputSide) {
    	this.side = inputSide;
        this.perimeter = -1;
        this.area = -1;
    }

    @Override
	public double getPerimeter() {
    	if(this.perimeter == -1)
    		this.perimeter = computePerimeter(this.side);

    	return this.perimeter;
    }

    @Override
	public double getArea() {
    	if(this.area == -1)
    		this.area = computeArea(this.side);

        return this.area;
    }

    static double computePerimeter(double inputSide) {
    	return 4.0*inputSide;
    }

    static double computeArea(double inputSide) {
    	return Math.pow(inputSide,2);
    }
}

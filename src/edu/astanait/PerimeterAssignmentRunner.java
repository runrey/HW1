package edu.astanait;
import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
    	int res  = 0;
    
    	for (Point currPt : s.getPoints()) {
    		res++;
    	}
        // Put code here
        return res;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        return getPerimeter(s)/getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
    	double largest = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if(largest<currDist)
            	largest=currDist;
            prevPt = currPt;
        }
        return largest;
    }

    public double getLargestX(Shape s) {
    	 double largest=Double.MIN_VALUE;
    	 for (Point currPt : s.getPoints()) {
    		 largest = largest<currPt.getX()?currPt.getX():largest;
    	 }
        return largest;
    }

    public double getLargestPerimeterMultipleFiles() {
    	double res = 0.0;
    	DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
        	FileResource fr = new FileResource(f);
        	Shape s = new Shape(fr);
            double length = getPerimeter(s);
            res = res<length?length:res;
        }
        return res;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        double res = -1.0;
    	DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
        	FileResource fr = new FileResource(f);
        	Shape s = new Shape(fr);
            double length = getPerimeter(s);
            if(res<length) {
            	res = length;
            	temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int nb_of_points = getNumPoints(s);
        double avg_length = getAverageLength(s);
        double largest_side = getLargestSide(s);
        double largest_x = getLargestX(s);
        System.out.println("number of points="+nb_of_points+", avg_length="+avg_length+", largest_side="+largest_side+", largest_x="+largest_x+",perimeter = " + length);
    }
    
    public void testPerimeterMultipleFiles() {
    	System.out.println("testPerimeterMultipleFiles()");
    	double largest_perimeter = getLargestPerimeterMultipleFiles();
    	String file_perimeter = getFileWithLargestPerimeter();
    	System.out.println("largest_perimeter="+largest_perimeter+", file_perimeter="+file_perimeter);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
        pr.testPerimeterMultipleFiles();
    }
}

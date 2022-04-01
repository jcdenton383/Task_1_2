import java.awt.*;
import java.util.ArrayList;

public class pPolygon {
    //NOTE: drawing actual graphics CANNOT be done outside of paintComponent and paint methods
    //NOTE: makes no sense to make a method to draw a polygon in HERE, since it's just a class and drawing polygon happens in gui
    //Note: java apparently already has a polygon class, which apparently basically is this but with additional npoints value

    private int[] xPoints;    //note:  WE WILL NOT be checking whether the number of points is the same
    private int[] yPoints;    // those are arraylists so we can change them over the course of program, even if task does explicitly state it is not needed
    //idea stolen from drawPolygon: add first point again as the last
    //points to be added in a clockwise fashion

    //POINTS TO BE ADDED IN A CLOCKWISE FASHION
    private int npoints;


    public double area() {    //shamelessly stolen from russian wikihow
        double numOne = 0;
        double numTwo = 0;
        /*for (int i = 0; i < xPoints.length; i++) {
            System.out.print(xPoints[i] + " ");

        }
        for (int i = 0; i < xPoints.length; i++) {
            System.out.print(yPoints[i] + " ");

        }*/
        for (int i = 0; i < xPoints.length - 1; i++) {
            /*System.out.println(xPoints[i]);
            System.out.println(yPoints[i + 1]);
            System.out.println(xPoints[i] * yPoints[i + 1]);*/
            numOne += xPoints[i] * yPoints[i + 1];
            //System.out.println("** " + numOne);
        }
        for (int i = 0; i < yPoints.length - 1; i++) {
           /* System.out.println(yPoints[i] * xPoints[i + 1]);*/
            numTwo += yPoints[i] * xPoints[i + 1];
            //System.out.println("*** " + numTwo);
        }
        return 0.5 * Math.abs(numOne - numTwo);
    }

    public double perimeter() {
        double perimeter = 0;
        for (int i = 0; i < xPoints.length - 1; i++) {
            perimeter += Math.sqrt(Math.pow((xPoints[i + 1] - xPoints[i]), 2) + Math.pow((yPoints[i + 1] - yPoints[i]), 2));
        }

        return perimeter;
    }

    public void move(int valueX, int valueY) {
       /* for (int i : xPoints) {
            i += valueX;
        }
        for (int i : yPoints) {
            i += valueY;
        }*/

        for (int i = 0; i < xPoints.length; i++) {
            xPoints[i]+=valueX;
            yPoints[i]+=valueY;;
        }

    }

     public void scale(int num) {    //we'll try scaling polygon hinging on the first(and last) point
         for (int i = 1; i < xPoints.length-1; i++) {

             /*setSolexPoint(i, getSoleXpoint(i)*num);
             setSoleYPoint(i, getSoleYpoint(i)*num);*/

         }
     }
//NOTE: as per stackowerflow it turns out that rectangle is apparently represented as nxm in calculations, but will be rendered as (n+1)x(m+1)   WTF
    public double areaSurroundRectangle() {   //spoiler: the part of the task saying there is plenty of algorithms for this is a fng LIE
        //we ahsll operate under assumption that the task DOES NOT require us to find the SMALLEST rectangle, otherwise we will have to make 360 rectangles turning them like 1 degree
//take lowest and highest y and leftmost and rightmost x, use them as bounds to calculate intersection points
        //we did not even start on affine transformations, expecting it to be done in first task of the semester is absurd.
        double xmin = xPoints[0], ymin = yPoints[0], xmax = xPoints[0], ymax = yPoints[0];

        for (int i = 0; i < xPoints.length; i++) {
            if (xPoints[i] > xmax) xmax = xPoints[i];
            if (xPoints[i] < xmin) xmin = xPoints[i];
            if (yPoints[i] > ymax) ymax = yPoints[i];
            if (yPoints[i] < ymin) ymin = yPoints[i];
        }
        return (xmax - xmin) * (ymax - ymin);
    }

    public Rectangle surroundRectangle() {
        int xmin = xPoints[0], ymin = yPoints[0], xmax = xPoints[0], ymax = yPoints[0];

        for (int i = 0; i < xPoints.length; i++) {
            if (xPoints[i] > xmax) xmax = xPoints[i];
            if (xPoints[i] < xmin) xmin = xPoints[i];
            if (yPoints[i] > ymax) ymax = yPoints[i];
            if (yPoints[i] < ymin) ymin = yPoints[i];
        }
        Rectangle rectangle = new Rectangle(xmin*20+300, -ymax*20+200, (xmax-xmin)*20, (ymax-ymin)*20);

        return rectangle;
    }

    public pPolygon(int[] xPoints, int[] yPoints) {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        if (xPoints.length != yPoints.length) {
            System.out.println("warnibng: size of x != size of y, will exit"); //amount of xpoints should obviously equal the amount of ypoints, duh
            System.exit(2);
        }
        if (xPoints.length < 3) {
            System.out.println("warnibng: not polygon"); //if <3 then its a line or a point
            System.exit(2);
        }

        /*for (int i = 0; i < xPoints.length; i++) {
            System.out.print(xPoints[i] + " ");

        }
        for (int i = 0; i < xPoints.length; i++) {
            System.out.print(yPoints[i] + " ");

        }*/

    }


    public int[] getXpoints() {
        return xPoints;
    }

    public int[] getYpoints() {
        return yPoints;
    }

    //------
    public void setSolexPoint(int index, int value) {
        this.xPoints[index] = value;
    }

    public void setSoleYPoint(int index, int value) {
        this.yPoints[index] = value;
    }

    public int getSoleXpoint(int index) {
        return xPoints[index];
    }

    public int getSoleYpoint(int index) {
        return yPoints[index];
    }

    public int getNpoints() {
        return this.npoints;
    }
//NOTE: transforming coordinates is currently done on object itself, not on reference, so calling to perimeter and area functions would yield results in PIXELS, not in actual "coordinate" numbers
    //NOTE: always detransform coordinates before calling any of the functions in this class
    //note: transform methods do not need reference to polygon to be passed to them, they are just copied from formmain and thus retain it


    public void coordTransform(pPolygon pPPolygon) {
        for (int i = 0; i < pPPolygon.getXpoints().length; i++) {
            pPPolygon.setSolexPoint(i, pPPolygon.getSoleXpoint(i) * 20 + 300);
            //pPPolygon.setSoleYPoint(i, pPPolygon.getSoleYpoint(i) * 20 - 2 * pPPolygon.getSoleYpoint(i) * 20 + 200);
            pPPolygon.setSoleYPoint(i, -pPPolygon.getSoleYpoint(i) * 20 + 200);
        }

    }

    public void coordDeTransform(pPolygon pPPolygon) {
        for (int i = 0; i < pPPolygon.getXpoints().length; i++) {
            pPPolygon.setSolexPoint(i, (pPPolygon.getSoleXpoint(i) - 300) / 20);
            pPPolygon.setSoleYPoint(i, -(pPPolygon.getSoleYpoint(i) - 200) / 20);
        }


    }
}

import java.awt.*;

public class Oval extends Shape{
    Oval(int x1, int y1, int x2, int y2, Color c) {
        super(x1, y1, x2, y2, c);
        this.type=2;
    }
    boolean isAtThatPos(int x, int y) {
        double a=(double) endX /2;
        double b=(double) endY /2;
        double h=startX+a;
        double k=startY+b;
        return (Math.pow(x-h,2)/Math.pow(a,2))+(Math.pow(y-k,2)/Math.pow(b,2))<=1;
    }
}

import java.awt.*;

public class Rectangle extends Shape{
    Rectangle(int x1, int y1, int x2, int y2, Color c) {
        super(x1, y1, x2, y2, c);
        this.type=1;
    }
}

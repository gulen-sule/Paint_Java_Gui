import java.awt.*;

public class Line extends Shape {

    Line(int x1, int y1, int x2, int y2, Color c) {
        super(x1, y1, x2, y2, c);
        this.type=0;
    }

    @Override
    boolean isAtThatPos(int x, int y) {
        return false;
    }
}

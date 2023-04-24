import java.awt.*;

public class Shape {
    Color color;
    int startX;
    int startY;
    int endX;
    int endY;
    int type = -1;

    Shape(int x1, int y1, int x2, int y2, Color c) {
        startX = x1;
        startY = y1;
        endX = x2;
        endY = y2;
        color = c;
    }

    boolean isAtThatPos(int x, int y) {
        return (x <= endX+startX && x >= startX && y <= endY+startY && y >= startY);

    }
}

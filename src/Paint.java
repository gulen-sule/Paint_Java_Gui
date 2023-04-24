import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

class Paint extends JFrame {
    private int whichBtnClicked = -1;
    Color currentColor = Color.BLACK;

    public Paint() {
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel fircaPaneli = new JPanel();
        fircaPaneli.setLayout(new GridLayout(2, 1));

        JPanel renkPaneli = new JPanel();
        renkPaneli.setBorder(BorderFactory.createEmptyBorder(10, 6, 0, 6));

        JPanel blue_panel = new JPanel();
        JPanel red_panel = new JPanel();
        JPanel orange_panel = new JPanel();
        JPanel purple_panel = new JPanel();
        JPanel black_panel = new JPanel();
        JPanel yellow_panel = new JPanel();
        JPanel green_panel = new JPanel();

        blue_panel.setBackground(Color.blue);
        red_panel.setBackground(Color.red);
        green_panel.setBackground(Color.green);
        yellow_panel.setBackground(Color.yellow);
        orange_panel.setBackground(Color.orange);
        purple_panel.setBackground(Color.MAGENTA);
        black_panel.setBackground(Color.black);

        blue_panel.addMouseListener(new ColorPanelListener());
        red_panel.addMouseListener(new ColorPanelListener());
        green_panel.addMouseListener(new ColorPanelListener());
        yellow_panel.addMouseListener(new ColorPanelListener());
        orange_panel.addMouseListener(new ColorPanelListener());
        purple_panel.addMouseListener(new ColorPanelListener());
        black_panel.addMouseListener(new ColorPanelListener());

        blue_panel.setPreferredSize(new Dimension(80, 40));
        red_panel.setPreferredSize(new Dimension(80, 40));
        green_panel.setPreferredSize(new Dimension(80, 40));
        yellow_panel.setPreferredSize(new Dimension(80, 40));
        orange_panel.setPreferredSize(new Dimension(80, 40));
        purple_panel.setPreferredSize(new Dimension(80, 40));
        black_panel.setPreferredSize(new Dimension(80, 40));

        renkPaneli.add(blue_panel);
        renkPaneli.add(red_panel);
        renkPaneli.add(green_panel);
        renkPaneli.add(yellow_panel);
        renkPaneli.add(orange_panel);
        renkPaneli.add(purple_panel);
        renkPaneli.add(black_panel);

        JPanel tusPaneli = new JPanel();
        JButton rect = new JButton("Dikdörtgen Çiz");
        rect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                whichBtnClicked = 0;
            }
        });
        JButton circle = new JButton("Oval Çiz");
        circle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                whichBtnClicked = 1;
            }
        });
        JButton line = new JButton("Kalemle Çiz");
        line.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                whichBtnClicked = 2;
            }
        });
        JButton move = new JButton("Taşı");
        move.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                whichBtnClicked = 3;
            }
        });
        tusPaneli.setLayout(new FlowLayout());
        tusPaneli.add(rect);
        tusPaneli.add(circle);
        tusPaneli.add(line);
        tusPaneli.add(move);
        setVisible(true);

        fircaPaneli.add(renkPaneli);
        fircaPaneli.add(tusPaneli);
        fircaPaneli.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.blue));

        add(fircaPaneli, BorderLayout.NORTH);

        Plane p = new Plane();
        add(p);
        setVisible(true);
    }

    class ColorPanelListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            JPanel colorPanel = (JPanel) e.getSource();
            currentColor = colorPanel.getBackground();
        }

        @Override
        public void mousePressed(MouseEvent e) {
            JPanel colorPanel = (JPanel) e.getSource();
            currentColor = colorPanel.getBackground();
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    class Plane extends JPanel implements MouseInputListener {
        ArrayList<Shape> drawings = new ArrayList<>();
        int startX = 0;
        int startY = 0;
        Shape currShape = null;
        int xDiff = 0;
        int yDiff = 0;

        Plane() {
            setVisible(true);
            addMouseListener(this);
            addMouseMotionListener(this);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            for (int i = 0; i < drawings.size(); i++) {
                Shape tmp = drawings.get(i);
                g.setColor(tmp.color);
                if (tmp.type == 0) {
                    g.drawLine(tmp.startX, tmp.startY, tmp.endX, tmp.endY);
                } else if (tmp.type == 1) {
                    g.fillRect(tmp.startX, tmp.startY, tmp.endX, tmp.endY);
                } else if (tmp.type == 2) {
                    g.fillOval(tmp.startX, tmp.startY, tmp.endX, tmp.endY);
                }
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            startX = e.getX();
            startY = e.getY();
            if (whichBtnClicked == 3) {
                for (int i = drawings.size() - 1; i >= 0; i--) {
                    if (drawings.get(i).isAtThatPos(startX, startY)) {
                        currShape = drawings.get(i);
                        xDiff = startX - currShape.startX;
                        yDiff = startY - currShape.startY;
                        drawings.remove(i);
                        break;
                    }
                }
            }

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (whichBtnClicked == 0) {
                drawings.add(new Rectangle(startX + 10, startY + 10, e.getX() - startX, e.getY() - startY, currentColor));
                repaint();
            } else if (whichBtnClicked == 1) {
                drawings.add(new Oval(startX + 10, startY + 10, e.getX() - startX, e.getY() - startY, currentColor));
                repaint();
            } else if (whichBtnClicked == 3 && currShape != null) {
                currShape.startX = e.getX() - xDiff;
                currShape.startY = e.getY() - yDiff;
                drawings.add(currShape);
                repaint();
                currShape = null;
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

        @Override
        public void mouseDragged(MouseEvent e) {
            Graphics g = getGraphics();
            g.setColor(currentColor);
            if (whichBtnClicked == 0) {
                g.fillRect(startX + 10, startY + 10, e.getX() - startX, e.getY() - startY);
            } else if (whichBtnClicked == 1) {
                g.fillOval(startX + 10, startY + 10, e.getX() - startX, e.getY() - startY);
            } else if (whichBtnClicked == 2) {
                g.drawLine(startX, startY, e.getX(), e.getY());
                drawings.add(new Line(startX, startY, e.getX(), e.getY(), currentColor));
                startX = e.getX();
                startY = e.getY();
            } else if (whichBtnClicked == 3 && currShape != null) {
                drawings.remove(currShape);
                currShape.startX = e.getX() - xDiff;
                currShape.startY = e.getY() - yDiff;
                drawings.add(currShape);
                repaint();
            }
        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

}

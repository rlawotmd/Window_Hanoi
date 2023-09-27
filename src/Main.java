import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import javax.swing.*;

class MyPanel extends JPanel {



    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(15, BasicStroke.CAP_SQUARE, 0));
        g2.draw(new Line2D.Double(10, 10, 100, 100));
    }
}

class MyFrame extends JFrame {
    public MyFrame() {
        setTitle("Hanoi");
        setSize(1650, 870);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

        Container contentPane = getContentPane();
        contentPane.add(new MyPanel());

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                System.out.println(x + ", " + y);
            }

            @Override
            public void mousePressed(MouseEvent e) {

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
        });
    }

}


public class Main {
    public static void main(String[] args) {
        JFrame Fr = new MyFrame();
        Fr.show();
    }
}
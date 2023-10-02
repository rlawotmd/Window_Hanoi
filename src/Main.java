import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import javax.swing.*;

class MyPanel extends JPanel {

    public Font f;
    public Font fi;
    public FontMetrics fm;
    public FontMetrics fim;

    public void setFonts(Graphics g) {
        if (f != null) return;
        f = new Font("SansSerif", Font.BOLD, 14);
        fi = new Font("SansSerif", Font.ITALIC, 14);
        fm = g.getFontMetrics(f);
        fim = g.getFontMetrics(fi);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 탑
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(5, BasicStroke.CAP_SQUARE, 0));
        g2.draw(new Line2D.Double(290, 150, 290, 750));
        g2.draw(new Line2D.Double(140, 750, 440, 750));
        g2.draw(new Line2D.Double(640, 150, 640, 750));
        g2.draw(new Line2D.Double(490, 750, 790, 750));
        g2.draw(new Line2D.Double(990, 150, 990, 750));
        g2.draw(new Line2D.Double(840, 750, 1140, 750));

        setFonts(g);
        String how = "층 수 : ";
        int width_how = fm.stringWidth(how);

        Dimension d = getSize();
        int x_text = (d.width - width_how) / 2;

        g.setFont(f);
        g.drawString(how, x_text, 20);


    }
}

class MyFrame extends JFrame {
    public MyFrame() {
        setTitle("Hanoi");
        setSize(1280, 850);

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
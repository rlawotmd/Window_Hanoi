import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import javax.swing.*;
import java.util.*;

/*
class Stack extends java.util.Stack {
    @Override
    public Object push(Object item) {
        return super.push(item);
    }

    @Override
    public synchronized Object pop() {
        return super.pop();
    }
}*/

class MoveHanoi {
    int n;
    public Stack<Integer> a = new Stack<>();
    public Stack<Integer> b = new Stack<>();
    public Stack<Integer> c = new Stack<>();

    public void StartMove(int n, Stack a, Stack b, Stack c) {
        if (n == 1) {
            c.push(a.pop());
            System.out.println("move");
        }
        else {
            StartMove(n - 1, a, c, b);
            c.push(a.pop());
            System.out.println("move");
            StartMove(n - 1, b, a, c);
        }
    }
}

class LinkedList {
    LinkedList Prev = null;
    LinkedList next = null;
    Stack<Integer> data_a;
    Stack<Integer> data_b;
    Stack<Integer> data_c;


    public void Work(Stack<Integer> a, Stack<Integer> b, Stack<Integer> c) {
        if(Prev == null) Prev = this;
        if(next == null) {
            next = this;
            next.data_a = a;
            next.data_b = b;
            next.data_c = c;
        } else {
            next = new LinkedList();
            next.Prev = this;
            next.data_a = a;
            next.data_b = b;
            next.data_c = c;
        }
    }

}
class MyPanel extends JPanel {

    private Font f;
    private Font fi;
    private FontMetrics fm;
    private FontMetrics fim;
    private JTextField floorTextField;
    private JButton prevButton;
    private JButton startButton;
    private JButton nextButton;
    private JButton resetButton;
    private MoveHanoi moveHanoi;
    private LinkedList goWork;

    public MyPanel() {
        setLayout(new FlowLayout());

        floorTextField = new JTextField("0", 5);
        floorTextField.setForeground(Color.GRAY);

        floorTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                if(floorTextField.getText().equals("0")) {
                    floorTextField.setText("");
                    floorTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (floorTextField.getText().isEmpty()) {
                    floorTextField.setText("0");
                    floorTextField.setForeground(Color.GRAY);
                }
            }
        });

        add(new JLabel("층 수:"));
        add(floorTextField);

        prevButton = new JButton("Prev");
        add(prevButton);

        startButton = new JButton("Start");
        add(startButton);

        nextButton = new JButton("Next");
        add(nextButton);

        resetButton = new JButton("Reset");
        add(resetButton);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int floor_numder = Integer.parseInt(floorTextField.getText());
                if (floor_numder == 0) return;
                moveHanoi = new MoveHanoi();
                for(int i = floor_numder; i > 0; i--) {
                    moveHanoi.a.push(i);
                }
                LinkedList goWork = new LinkedList();
                goWork.Work(moveHanoi.a, moveHanoi.b, moveHanoi.c);
                moveHanoi.StartMove(floor_numder, moveHanoi.a, moveHanoi.b, moveHanoi.c);
                repaint();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                floorTextField.setText("0");


            }
        });
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }


    // 각 스택에 있는 원반을 그래픽으로 그리는 메서드
    private void drawTower(Graphics g, Stack<Integer> tower, int x) {
        int baseY = 750;
        int widthIncrement = 20;
        int heightIncrement = 15;

        for (int i = 0; i < tower.size(); i++) {
            int diskWidth = tower.get(i) * widthIncrement;
            int diskHeight = 10;
            int diskX = x - diskWidth / 2;
            int diskY = baseY - i * heightIncrement;
            g.fillRect(diskX, diskY, diskWidth, diskHeight);
        }
    }

    public void setFonts(Graphics g) {
        if (f != null) return;
        f = new Font("SansSerif", Font.BOLD, 10);
        fi = new Font("SansSerif", Font.ITALIC, 10);
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

        if (moveHanoi != null) {
            // 각 스택에 있는 원반을 그래픽으로 그리기
            drawTower(g, moveHanoi.a, 290);
            drawTower(g, moveHanoi.b, 640);
            drawTower(g, moveHanoi.c, 990);
        }
/*
        setFonts(g);
        String how = "층 수 : ";
        int width_how = fm.stringWidth(how);

        Dimension d = getSize();
        int x_text = (d.width - width_how) / 2;

        g.setFont(f);
        g.drawString(how, x_text, 20);
*/
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
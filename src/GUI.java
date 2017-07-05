import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.security.PublicKey;
import java.util.*;
import java.awt.*;

public class GUI extends JFrame {

    public int mx = -100;
    public int my = -100;
    int spacing = 5;
    Random rand = new Random();

    int[][] mines = new int[16][9];
    int[][] neighbours = new int[16][9];
    boolean[][] revealed = new boolean[16][9];
    boolean[][] flagged = new boolean[16][9];


    public GUI() {
        this.setTitle("Minesweeper");
        this.setSize(1286, 829);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                if (rand.nextInt(100) < 20) {
                    mines[i][j] = 1;
                } else {
                    mines[i][j] = 0;
                }

            }
        }

        Board board = new Board();
        this.setContentPane(board);

        Move move = new Move();
        this.addMouseMotionListener(move);

        Click click = new Click();
        this.addMouseListener(click);
    }

    public class Board extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(Color.darkGray);
            g.fillRect(0, 0, 1280, 800);

            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 9; j++) {
                    g.setColor(Color.gray);
                    /*
                    if (mines[i][j] == 1) {
                        g.setColor(Color.yellow);
                    }
                    */
                    if (mx >= spacing + i * 80 + 2 && mx < i * 80 + 80 - spacing + 2 && my >= spacing + j * 80 + 106 && my < j * 80 + 186 - spacing) {
                        g.setColor(Color.red);
                    }
                    g.fillRect(spacing + i * 80, spacing + j * 80 + 80, 80 - 2 * spacing, 80 - 2 * spacing);
                }
            }
        }
    }

    public class Move implements MouseMotionListener {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mx = e.getX();
            my = e.getY();
            /**
             System.out.println("The mouse was moved");
             System.out.println("x= " + mx + ", " + "y= " + my);
             */
        }
    }

    public class Click implements MouseListener {

        /**
         * Invoked when the mouse button has been clicked (pressed
         * and released) on a component.
         *
         * @param e
         */
        @Override
        public void mouseClicked(MouseEvent e) {

            if (inBoxX() != -1 && inBoxY() != -1) {
                System.out.println("The mouse is in the [" + inBoxX() + "," + inBoxY() + "]");
            } else {
                System.out.println("The Pointer is not inside any box");
            }
        }

        /**
         * Invoked when a mouse button has been pressed on a component.
         *
         * @param e
         */
        @Override
        public void mousePressed(MouseEvent e) {

        }

        /**
         * Invoked when a mouse button has been released on a component.
         *
         * @param e
         */
        @Override
        public void mouseReleased(MouseEvent e) {

        }

        /**
         * Invoked when the mouse enters a component.
         *
         * @param e
         */
        @Override
        public void mouseEntered(MouseEvent e) {

        }

        /**
         * Invoked when the mouse exits a component.
         *
         * @param e
         */
        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public int inBoxX() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                if (mx >= spacing + i * 80 + 2 && mx < i * 80 + 80 - spacing + 2 && my >= spacing + j * 80 + 106 && my < j * 80 + 186 - spacing) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int inBoxY() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                if (mx >= spacing + i * 80 + 2 && mx < i * 80 + 80 - spacing + 2 && my >= spacing + j * 80 + 106 && my < j * 80 + 186 - spacing) {
                    return j;
                }
            }
        }
        return -1;
    }


}

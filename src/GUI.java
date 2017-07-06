import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.awt.*;

public class GUI extends JFrame {

    public boolean resseter = false;

    public boolean flagger = false;

    Date startDate = new Date();
    Date endDate;

    String vicMes = "Nothing!";

    public int mx = -100;
    public int my = -100;

    public int smileyX = 605;
    public int smileyY = 5;

    public int smileyCenterX = smileyX + 35 + 3;
    public int smileyCenterY = smileyY + 35 + 26;

    public int flaggerX = 445;
    public int flaggerY = 5;


    public int timeX = 1130;
    public int timeY = 5;

    public int vicMesX = 100;
    public int vicMesY = -50;

    public int sec = 0;

    public boolean happiness = true;

    public boolean victory = false;
    public boolean defeat = false;

    int spacing = 5;

    Random rand = new Random();

    int neighs = 0;

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

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);



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

            mx = e.getX();
            my = e.getY();
/**
            if (inBoxX() != -1 && inBoxY() != -1) {
                revealed[inBoxX()][inBoxY()] = true;
            }
 */

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

    public void checkVictoryStatus() {
        if (defeat == false) {
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 9; j++) {
                    if (revealed[i][j] == true && mines[i][j] == 1) {
                        defeat = true;
                        happiness = false;
                        endDate = new Date();
                    }
                }
            }
        }
        if (totalBoxesReveled() >= 144 - totalMines() && victory == false) {
            victory = true;
            endDate = new Date();
        }
    }

    public int totalMines() {
        int total = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                if (mines[i][j] == 1) {
                    total++;
                }
            }
        }
        return total;
    }

    public int totalBoxesReveled() {
        int total = 0;
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                if (revealed[i][j] == true) {
                    total++;
                }
            }
        }
        return total;
    }

    public void resetAll() {

        resseter = true;

        startDate = new Date();
        happiness = true;
        victory = false;
        defeat = false;


        resseter = false;
    }


}

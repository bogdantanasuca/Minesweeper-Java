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

    public int smileyX = 605;
    public int smileyY = 5;

    public boolean happiness = true;

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

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                if (rand.nextInt(100) < 20) {
                    mines[i][j] = 1;
                } else {
                    mines[i][j] = 0;
                }
                revealed[i][j] = false;
            }
        }

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                neighs = 0;
                for (int m = 0; m < 16; m++) {
                    for (int n = 0; n < 9; n++) {
                        if (!(m == i && n == j))
                            if (isN(i, j, m, n) == true)
                                neighs++;
                    }
                }
                neighbours[i][j] = neighs;
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

                    if (mines[i][j] == 1) {
                        g.setColor(Color.yellow);
                    }

                    if (revealed[i][j] == true) {
                        g.setColor(Color.white);
                        if (mines[i][j] == 1) {
                            g.setColor(Color.red);
                        }
                    }

                    if (mx >= spacing + i * 80 + spacing / 2 && mx < i * 80 + 80 - spacing + spacing / 2 && my >= spacing + j * 80 + 106 && my < j * 80 + 186 - spacing) {
                        g.setColor(Color.lightGray);
                    }
                    g.fillRect(spacing + i * 80, spacing + j * 80 + 80, 80 - 2 * spacing, 80 - 2 * spacing);

                    if (revealed[i][j] == true) {
                        g.setColor(Color.black);
                        if (mines[i][j] == 0 && neighbours[i][j] != 0) {
                            if (neighbours[i][j] == 1) {
                                g.setColor(Color.blue);
                            } else if (neighbours[i][j] == 2) {
                                g.setColor(Color.green);
                            } else if (neighbours[i][j] == 3) {
                                g.setColor(Color.red);
                            } else if (neighbours[i][j] == 4) {
                                g.setColor(new Color(0, 0, 128));
                            } else if (neighbours[i][j] == 5) {
                                g.setColor(new Color(178, 34, 34));
                            } else if (neighbours[i][j] == 6) {
                                g.setColor(new Color(72, 209, 204));
                            } else if (neighbours[i][j] == 8) {
                                g.setColor(Color.darkGray);
                            }
                            g.setFont(new Font("Tahome", Font.BOLD, 40));
                            g.drawString(Integer.toString(neighbours[i][j]), i * 80 + 27, j * 80 + 80 + 55);
                        } else if (mines[i][j] == 1) {
                            g.fillRect(+i * 80 + 10 + 20, j * 80 + 80 + 20, 20, 40);
                            g.fillRect(+i * 80 + 20, j * 80 + 80 + 10 + 20, 40, 20);
                            g.fillRect(i * 80 + 5 + 20, j * 80 + 80 + 5 + 20, 30, 30);
                            g.fillRect(i * 80 + 38, j * 80 + 80 + 15, 4, 50);
                            g.fillRect(i * 80 + 15, j * 80 + 80 + 38, 50, 4);
                        }
                    }
                }
            }

            g.setColor(Color.yellow);
            g.fillOval(smileyX, smileyY, 70, 70);
            g.setColor(Color.black);
            g.fillOval(smileyX + 15, smileyY + 20, 10, 10);
            g.fillOval(smileyX + 45, smileyY + 20, 10, 10);
            if (happiness == true) {
                g.fillRect(smileyX + 20, smileyY + 50, 30, 5);
                g.fillRect(smileyX + 17, smileyY + 45, 5, 5);
                g.fillRect(smileyX + 48, smileyY + 45, 5, 5);
            } else {
                g.fillRect(smileyX + 20, smileyY + 45, 30, 5);
                g.fillRect(smileyX + 17, smileyY + 50, 5, 5);
                g.fillRect(smileyX + 48, smileyY + 50, 5, 5);
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
                revealed[inBoxX()][inBoxY()] = true;
            }

            if (inBoxX() != -1 && inBoxY() != -1) {
                System.out.println("The mouse is in the [" + inBoxX() + "," + inBoxY() + "],Number of mine neigh:" + neighbours[inBoxX()][inBoxY()]);
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
                if (mx >= spacing + i * 80 + spacing / 2 && mx < i * 80 + 80 - spacing + spacing / 2 && my >= spacing + j * 80 + 106 && my < j * 80 + 186 - spacing) {
                    return i;
                }
            }
        }
        return -1;
    }

    public int inBoxY() {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                if (mx >= spacing + i * 80 + spacing / 2 && mx < i * 80 + 80 - spacing + spacing / 2 && my >= spacing + j * 80 + 106 && my < j * 80 + 186 - spacing) {
                    return j;
                }
            }
        }
        return -1;
    }

    public boolean isN(int mX, int mY, int cX, int cY) {
        if (mX - cX < 2 && mX - cX > -2 && mY - cY < 2 && mY - cY > -2 && mines[cX][cY] == 1) {
            return true;
        }
        return false;
    }
}

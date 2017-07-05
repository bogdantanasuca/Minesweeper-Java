import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.*;
import java.awt.*;

public class GUI extends JFrame{

    int spacing=5;
    public int mx=-100;
    public int my=-100;

    public GUI(){
        this.setTitle("Minesweeper");
        this.setSize(1286,829);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);

        Board board = new Board();
        this.setContentPane(board);

        Move move= new Move();
        this.addMouseMotionListener(move);

        Click click= new Click();
        this.addMouseListener(click);
    }

    public class Board extends JPanel{
        public void paintComponent(Graphics g){
            g.setColor(Color.darkGray);
            g.fillRect(0,0,1280,800);

            for (int i=0;i<16;i++){
                for (int j=0;j<9;j++){
                    g.setColor(Color.gray);
                    if (mx >=spacing+i*80 && mx <spacing+i*80+80-2*spacing && my >= spacing+j*80+80+26 && my < spacing+j*80+80+80-2*spacing+26){
                        g.setColor(Color.red);
                    }
                    g.fillRect(spacing+i*80,spacing+j*80+80,80-2*spacing,80-2*spacing);
                }
            }
        }
    }
        public class Move implements MouseMotionListener{

            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                System.out.println("The mouse was moved");
                mx = e.getX();
                my = e.getY();
                System.out.println("x= "+mx+", "+"y= "+my);
            }
        }
        public  class Click implements MouseListener{

            /**
             * Invoked when the mouse button has been clicked (pressed
             * and released) on a component.
             *
             * @param e
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("The mouse was clicked");
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
}

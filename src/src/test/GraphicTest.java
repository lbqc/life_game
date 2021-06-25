package test;

import javax.swing.*;
import java.awt.*;

public class GraphicTest extends JFrame {

    private class MyPanel extends JPanel{
        @Override
        public void paint(Graphics g){
            super.paint(g);
            g.setColor(Color.BLACK);
            g.fillRect(10,10,20,20);
            g.drawRect(10,10,20,20);
        }
    }

    private MyPanel myPanel = new MyPanel();

    GraphicTest(){
        this.setSize(400,400);
        this.setLocation(100,100);
        this.setLayout(null);
        myPanel.setSize(200,200);
        this.add(myPanel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new GraphicTest();
    }
}

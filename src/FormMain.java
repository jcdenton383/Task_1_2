import java.awt.*;
import java.awt.Toolkit;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.JPanel;


public class FormMain extends JFrame {

    private Toolkit toolkit;


    public FormMain() {

        //setSize(320, 240);
        this.setTitle("FormMain");
        this.setPreferredSize(new Dimension(640, 480));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        toolkit = getToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);


        //jpanel
        JPanel panel = new GPanel();
        getContentPane().add(panel);
        panel.setLayout(null);


    }

    private class GPanel extends JPanel {
        @Override
        //important: always override, do not mess with graphics itself
        protected void paintComponent(Graphics g) {  //drawing itself is super low priority so will only draw/redraw when opened/resized/mAXimized
            super.paintComponent(g);           //since we NEED to redraw each time let the "parent" get called and do its thing

           /* int[] xpoints = {1, 3, 5, 1};
            int[] ypoints = {1, 3, 1, 1};*/  //triangle

            /*int[] xpoints = {1, 2, 1, 5, 4, 5, 1};
            int[] ypoints = {1, 4, 7, 7, 4, 1, 1}; */ //like, an hourglass or smth


            int[] xpoints = {2, 1, 4, 7, 6, 2};
            int[] ypoints = {1, 4, 6, 4, 1, 1};


            pPolygon newPPolygon = new pPolygon(xpoints, ypoints);
            System.out.println("perimeter " + newPPolygon.perimeter());  //demonstrating perimeter solving method
            System.out.println("area " + newPPolygon.area());  //demonstrating solving area of polygon
            System.out.println("surrounding rectangle area " + newPPolygon.areaSurroundRectangle());  //demonstrating solving area of surrounding rectangle
            newPPolygon.coordTransform(newPPolygon);
            drawAxis(g);
            g.drawPolygon(newPPolygon.getXpoints(), newPPolygon.getYpoints(), newPPolygon.getXpoints().length);
            newPPolygon.coordDeTransform(newPPolygon);
            Rectangle rectangle = new Rectangle(newPPolygon.surroundRectangle());
            System.out.println();
            g.setColor(Color.RED);
            g.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);  //demonstrating ability to draw surrounding rectangle
            newPPolygon.move(1,1);  //demonstrating ability to move figure and then draw it in a new place, note that you write an OFFSET BY which it is going to be moved
            g.setColor(Color.BLUE);
            newPPolygon.coordTransform(newPPolygon);//transform is needed EVERY time we change from java to descartes coordinates
            //lame, i know, but i would need some feedback on better practices
            g.drawPolygon(newPPolygon.getXpoints(), newPPolygon.getYpoints(), newPPolygon.getXpoints().length);


        }
    }


    private void drawAxis(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
//drawing axis, center is in (200 200)
        g2d.drawLine(100, 200, 500, 200);  //horiz
        g2d.drawLine(300, 0, 300, 500);//vert
        //drawing "steps", each step = 20px
        for (int i = 100; i < 500; i += 20) {
            g2d.drawLine(i, 195, i, 205);
            g2d.drawLine(295, i, 305, i);
        }


    }

}

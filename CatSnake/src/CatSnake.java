package week6;

import java.awt.geom.*; //Needed for Graphics 2D
 
import javax.swing.JFrame;

/*Hint:  Good guides:  ex1511 on gradient paint,
ex152b on general path,
ex142 on paint animation.*/

import java.awt.*;

public class CatSnake extends JFrame {

   private static final long serialVersionUID = 1L; //Needed to avoid Eclipse warning
   boolean firstTime = true; //Flag to indicate if static object need to be painted
   int catHeadLeft = 30; //Starting x position of vertical cat head lines
   int catHeadRight = 80;
   int catEarLeft = 45; //Starting x position of diagonal cat ear lines
   int catEarRight = 65;
   int catEyeLeftX = 37; //Starting x position of cat eyes
   int catEyeRightX = 63;
   int height= 500; //Applet width and height
   int width= 600;
   Container c;
   FlowLayout flow = new FlowLayout();
     
 
   
   public CatSnake() {
       this.setSize(600, 400);  
       c = getContentPane();
       c.setLayout(flow);
 
 
 
   }
 
   //Method to override paint, does not clear the screen to reduce flicker
   public void update(Graphics g) {
       paint(g);
   }
 
   //Method to paint the applet, calls repaint to move cat head after sleep and increment
   public void paint(Graphics g) {
     super.paint(g);
       Graphics2D g2 = (Graphics2D) g;  
     
       if (firstTime) {  
       
           Rectangle2D.Float grassRectangle = new Rectangle2D.Float(0, height / 2, width,
                   height / 2);  
           g2.setColor(Color.green);  
           g2.fill(grassRectangle);  

           System.out.println("nancy made it here");
         
         
           GradientPaint gradientPainter = new GradientPaint(0, 0, Color.cyan, width - 100, height - 100,
                   Color.white, false);  
       
           Rectangle2D.Float skyRectangle = new Rectangle2D.Float(0, 0, width, height / 2);
           g2.setPaint(gradientPainter);  
           g2.fill(skyRectangle);  

           
         
           BasicStroke trunkStroke = new BasicStroke(5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND); //Defines stroke
     
           Point2D.Float trunkTop = new Point2D.Float(width / 2 + 50, height / 2);
           Point2D.Float trunkBottom = new Point2D.Float(width / 2 + 50, (height / 2) - 60);
           Line2D.Float trunkLine = new Line2D.Float(trunkTop, trunkBottom);  
           g2.setColor(Color.darkGray); //Sets color
           g2.setStroke(trunkStroke); //Sets stroke
           g2.draw(trunkLine); //Paints trunk
 
           Arc2D.Float treeArc = new Arc2D.Float(width / 2 + 10, height / 2 - 80, 80, 50, 10f, 160f,
                   Arc2D.CHORD); //Creates new Arc2D centered on top of trunk
           g2.setColor(Color.green); //Sets color
           g2.fill(treeArc); //Paints tree top

         
           //Paints solid yellow ellipse sun
         
           //Creates new Ellipse2D circle in top left corner of applet
           Ellipse2D.Float sunEllipse = new Ellipse2D.Float(width / 10, height / 10, 60f, 60f);
           g2.setColor(Color.yellow); //Sets color
           g2.fill(sunEllipse); //Paints sun
 
           firstTime = false; //Changes flag so static shapes will not be repainted to reduce flicker
       }
     
     
       try{ //Try-catch block to handle animation and sleep
     
           // Sets cat head stroke
           BasicStroke catStroke = new BasicStroke(3f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
         
           //Paints green rectangle over cat head
           Rectangle2D catCover = new Rectangle2D.Float(catHeadLeft - 5, 225, 65, 70);
           g2.setColor(Color.GREEN);
           g2.fill(catCover);
         
           //Increments x position of cat head and eyes, to move to the right
           catHeadLeft += 20;
           catHeadRight += 20;
           catEarLeft += 20;
           catEarRight += 20;
           catEyeLeftX += 20;
           catEyeRightX += 20;
         
           //Paints GeneralPath cat head in red, which will be visible
           GeneralPath catHead = new GeneralPath();
           catHead.moveTo(catHeadLeft, 230f);
           catHead.lineTo(catHeadLeft, 290f);
           catHead.lineTo(catHeadRight, 290f);
           catHead.lineTo(catHeadRight, 230f);
           catHead.lineTo(catEarRight, 245f);
           catHead.lineTo(catEarLeft, 245f);
           catHead.closePath();
           g2.setStroke(catStroke);
           g2.setColor(Color.red);
           g2.draw(catHead);

           //Paints Ellipse2D cat eyes in blue, which will be visible
           Ellipse2D.Float catEyeLeft = new Ellipse2D.Float(catEyeLeftX, 250, 10f, 10f);
           Ellipse2D.Float catEyeRight = new Ellipse2D.Float(catEyeRightX, 250, 10f, 10f);
           g2.setColor(Color.blue);
           g2.fill(catEyeLeft);
           g2.fill(catEyeRight);
         
           //Sleeps for quarter second
           Thread.sleep(1000);
         
       } catch (InterruptedException e) { //Empty catch to handle interrupted exception
      repaint();
       }
       if (catHeadRight < width - 20) { //Tests if right edge of cat head is on the applet.
           repaint(); //Calls repaint if head is on the applet. Static shapes are not redrawn,
                       //cat head is covered in green, incremented, and repainted
       }
   }
   public static void main(String[] args)
   {
   CatSnake f = new CatSnake();
   //  f.repaint();
   }
 }

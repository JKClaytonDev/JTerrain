import javax.swing.JFrame; import java. awt. Graphics;   import javax.swing.JPanel;
import java.awt.Color;  import java.lang.Object;   import java.awt.Graphics2D;
import java.awt.Graphics;  import javax.swing.JOptionPane;  import java.util.*;
import java.util.Scanner;  import java.lang.Object;   import java.awt.MouseInfo;
import java.awt.PointerInfo;  import java.awt.geom.Point2D; import java.awt.Point;
import java.awt.event.KeyEvent;
public class RayCast extends JPanel{
   MakeWorld world = new MakeWorld(10);
   private int FrameCount = 0;
   private boolean w, a, s, d;
   public static void main (String[] args)
   {
      try
      {
         RayCast obj = new RayCast ();
         obj.run (args);
      }
      catch (Exception e)
      {
         e.printStackTrace ();
      }
   }
   
   public void run (String[] args){
      OpenJFrame("Test Run");
   }
   JFrame frame = new JFrame(); 
   public void OpenJFrame(String Events){
      frame.getContentPane().add(new DrawCraft());
      frame.setLocationRelativeTo(null);
      frame.setVisible(true); 
      PointerInfo a = MouseInfo.getPointerInfo();
      Point b = a.getLocation();
      while (1!=0){
         a = MouseInfo.getPointerInfo();
         if (a.getLocation() != b){
            b = a.getLocation();
            frame.setSize(700+(int)(Math.random()*3),700);
         }
      }
      
   }
   public void paint(Graphics g)
   {
      frame.addKeyListener(new KeyListener() {
      keyPressed(frame);
   }
   public void keyPressed(KeyEvent e){
      int keyCode = e.getKeyCode();
  System.out.println("You Pressed " + keyCode);
   }

}
   
   
    

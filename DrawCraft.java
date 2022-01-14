import javax.swing.JFrame; import java. awt. Graphics;   import javax.swing.JPanel;
import java.awt.Color;  import java.lang.Object;   import java.awt.Graphics2D;
import java.awt.Graphics;  import javax.swing.JOptionPane;  import java.util.*;
import java.util.Scanner;  import java.lang.Object;   import java.awt.MouseInfo;
import java.awt.PointerInfo;  import java.awt.geom.Point2D; import java.awt.Point;
import java.awt.event.KeyEvent;  import java.awt.Polygon;   import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;import java.awt.event.KeyListener;  import java.awt.geom.Dimension2D;
import java.awt.Dimension; import java.awt.Toolkit;
public class DrawCraft extends JPanel {
   double waterLevel = -55;
   MakeWorld world = new MakeWorld(3000);
   private int FrameCount = 0;
   private double lowestDistance;
   private int x;
   Color c;
   double timeFrames;
   double newZ;
   private int y;
   private double CamXX;
   private double CamXY;
   private double CamYX;
   private double CamYY;
   private double CamZXX;
   private double CamZXY;
   private double CamZYX;
   private double CamZYY;
   boolean water;
   private double PX;
   private double PY;
   private double PZ;
   private double dX;
   private double dY;
   private ViewModel v;
   boolean[] keys = new boolean[255];
   public static void main (String[] args)
   {
      try
      {
         DrawCraft obj = new DrawCraft ();
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
   
      a = MouseInfo.getPointerInfo();
      if (a.getLocation() != b){
         b = a.getLocation();
         frame.setSize(700+(int)(Math.random()*3),700);
      }
   }
   void updatePlayers(){
      movePlayer(1, 1);
   }
   void movePlayer(int x, int y){
      PX+= Math.cos(dX+90) * x / 25;
      PY+= Math.sin(dX+90) * y / 25;
   }
   public void paint(Graphics g)
   {
      System.out.println("Time : " + timeFrames/60);
      timeFrames++;
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
      Point b = MouseInfo.getPointerInfo().getLocation();
      dX = 90+(b.getX()/200);
      dY = 90+(b.getY()/200);
      updatePlayers();
      g.clearRect(0, 0, (int)frame.getSize().getWidth(), (int)frame.getSize().getHeight());
      frame.setSize((int)screenSize.getWidth()+(int)(Math.random()*3),(int)screenSize.getHeight());
      FrameCount++;
      List<GameObject> Polys = new ArrayList();
      List<Double> Distances = new ArrayList();
      lowestDistance = 999;
      for (int i = 0; i<world.getSize()-5; i+=4){
         Polys.add(renderBlock(i));
         Distances.add(Polys.get(Polys.size()-1).getDistance());
      }
      double tempPx = PX;
      double tempPy = PY;
      double tempPd = dX;
      PX = 0;
      PY = 0;
      PX = tempPx;
      PY = tempPy;
      dX = tempPd;
      Polys = Cull(Polys);
      PZ += (newZ-PZ)/15;
      for (GameObject pol : Polys){
         //int distance= (int)pol.getDistance() * 5;
         int distance = (int)(pol.getY()+1)*-5;
         if (distance > 255)
            distance = 255;
         if (distance <= 0)
            distance = 1;
         if ((pol.getY()) < waterLevel){
         c = new Color((int)(100/distance), distance, (int)(100/distance));
         }
         else{
         c = new Color((int)(100/distance), distance, (int)(100/distance));
         }
         g.setColor(c);
         g.fillPolygon(pol.getPoly());
      }
      
      removeAll();
      revalidate();
      repaint();
   }
   public GameObject renderBlock(int i){
         CamXX = Math.cos(dX);
         CamXY = Math.sin(dX);
         CamYX = -CamXY;
         CamYY = CamXX;
         CamZXX = Math.cos(dY);
         CamZXY = Math.sin(dY);
         CamZYX = -CamXY;
         CamZYY = CamXX;
         int oldI = i;
         double distance = 0;
        
         water = world.getBlockZ(i) < waterLevel;
         /*if ((Math.abs(PX - world.getBlockX(i)) + Math.abs(PY - world.getBlockY(i)))/2 < lowestDistance){
            lowestDistance = (Math.abs(PX - world.getBlockX(i)) + Math.abs(PY - world.getBlockY(i)))/2;
            newZ = world.getBlockZ(i) - 15;
         }
         */
         newZ = world.getNoiseZ(PX, PY) - 10;
         Polygon p = new Polygon();
         for (i = oldI; i<oldI+4; i++){
            distance += (Math.abs(PX - world.getBlockX(i)) + Math.abs(PY - world.getBlockY(i)))/2;
            double Bx = CalculateX(world.getBlockX(i),world.getBlockY(i),PX,PY,dX, dY);
            if (Bx != -9999){
               if (Bx < -getWidth())
                  Bx = -getWidth();
               if (Bx > getWidth())
                  Bx = getWidth();
               double By = CalculateY(world.getBlockX(i),world.getBlockY(i), world.getBlockZ(i, timeFrames, waterLevel),PX,PY,PZ,dX, dY);
               p.addPoint(getWidth()/2 + (int)Bx, getHeight()/2 + (int)By*5);
            }
         }
         distance/=50;
         GameObject k = new GameObject(p, distance, world.getBlockZ(i), world.getBlockZ(i));
         return k;
   }
   public double CalculateX(double worldX, double worldY, double X, double Y, double DX, double DY){
      double perspective = 100/((CamYX*(worldX-X))+(CamYY*(worldY-Y)));
      double v = (((CamXX*(worldX-X))+(CamXY*(worldY-Y))) * perspective * 1.04) + perspective; 
      if (0 < 4*perspective)
         return v*4;
      return -9999;
   }
   public double CalculateY(double worldX, double worldY, double worldZ, double X, double Y, double Z, double DX, double DY){
      double perspective = 100/((CamZYX*(worldX-X))+(CamZYY*(worldY-Y)));
      return (perspective + ((worldZ-Z) * perspective))*2;
   }
   public static List<GameObject> Cull(List<GameObject> hk){
      GameObject[] TempGO = new GameObject[hk.size()];
      for (int i = 0; i<hk.size(); i++)
         TempGO[i] = hk.get(i);
      GameObject[] b = mergeSort_srt(TempGO, 0, TempGO.length-1);
      List<GameObject> c = new ArrayList();
      for (GameObject g : b)
      c.add(0, g);
      return c;
   }
   public static GameObject[] mergeSort_srt(GameObject[] array,double lo, double n){
      int j, index, key;
      for (j = 1; j < array.length; j++){
         key = (int)array[j].getDistance();
         for (index = j - 1; (index >= 0) && ((int)array[index].getDistance() > key); index--){
            GameObject temp = array[index];
            array[index] = array[index + 1];
            array[index + 1] = temp;
         }
      }
      return array;
   }
}
   
   
    

import javax.swing.Timer;
import javax.swing.JOptionPane;
import java.util.*;
import javax.swing.JFrame;
import java. awt. Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.util.Scanner;
import java.lang.Object;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.Graphics2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.Rectangle2D;


public class EnvApp extends JPanel{
private int questions = 1;

   public static void main (String[] args)
   {
   
      try
      {
         EnvApp obj = new EnvApp ();
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
   
      frame.getContentPane().add(new EnvApp());
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(500,500);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true); 
      
   }
   
   public void paint(Graphics g)
   {
g.setColor(Color.BLUE);
g.fillRect(0, 0, getWidth(), getHeight());  
g.setColor(Color.BLUE);
drawText(g, "Save the Planet!", 100, 25, "TimesRoman", 30, 0);

      
      Object selectedValue = "";
      int arrayLength = 0;
      String[] whatIdid = new String[100];
      Object[] possibleValues = { "I walked to work/school", "I used reusable bags", "I shopped online", "I composted my leftovers","I turned off the lights when I left a room","please select one"};
      
      
      JOptionPane.showMessageDialog(null, "Thanks for helping save the planet!", "Welcome!", JOptionPane.PLAIN_MESSAGE);
      for (arrayLength =0; selectedValue.toString() != "that's all!" && questions == 1;arrayLength++){      
      
         selectedValue = (JOptionPane.showInputDialog(null,"How did you help the Enviornment Today?", "Input",JOptionPane.INFORMATION_MESSAGE, null,possibleValues, possibleValues[0]));
         
         if (selectedValue.toString() != "that's all!" && selectedValue.toString() != "please select one"){
            whatIdid[arrayLength] = selectedValue.toString()+"\n";
            possibleValues[5] = "that's all!";
         }
         
          if (selectedValue.toString() == "please select one")
            arrayLength = -1;
         
         
      }
      
      questions++;
      whatIdid = Arrays.copyOfRange(whatIdid, 0, arrayLength-1);
      
      super.paint(g);
      g.setColor(Color.BLUE);
      g.fillRect(0, 0, getWidth(), getHeight());
      g.setColor(Color.RED);
      
      drawText(g, "How I helped Save the World Today:", getWidth()/2, (int)(getHeight()*0.2), "TimesRoman", 30,0);
      
      for (int i = 0; i<=whatIdid.length-1; i++){
      drawText(g, whatIdid[i], getWidth()/2, getHeight()/2+(3*(whatIdid.length-1)*i), "TimesRoman", getWidth()/(whatIdid.length*5),10);
      }
      
      }
      
      public void drawText(Graphics g, String textInput, int TextX, int TextY, String fontType, int fontSize, int heightOffset){
      double textSize = fontSize;
      Font myfont = new Font(fontType, Font.PLAIN, (int)textSize);
      g.setFont(myfont); 
      Graphics2D g2d = (Graphics2D) g;
      FontRenderContext frc = g2d.getFontRenderContext();
      TextLayout layout = new TextLayout(textInput, myfont, frc);
      Rectangle2D bounds = layout.getBounds();
      int width = (int) Math.round(bounds.getWidth());
      
      g.drawString(textInput, (getWidth() - width) / 2, TextY+(heightOffset));
      }
   }
   
   
    

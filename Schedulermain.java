import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java. awt. Graphics;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.*;

public class Schedulermain extends JPanel {
   private int count;
   private String eventNames;

   Schedulerdata data = new Schedulerdata();
   public static void main (String[] args)
   {
      try
      {
         Schedulermain obj = new Schedulermain ();
         obj.run (args);
      }
      catch (Exception e)
      {
         e.printStackTrace ();
      }
   }

   public void run (String[] args){
Questions();
   }
   
   public void Questions(){
      count=0;
      JOptionPane.showMessageDialog(null, "Welcome to the Alpha build of my Task Scheduler!","Message", JOptionPane.QUESTION_MESSAGE);
   
      String inputValue;
      String event;
      Object selectedValue;
      for (int i = 0; (JOptionPane.showConfirmDialog(null,"Continue?", "Continue?", JOptionPane.YES_NO_OPTION) == 0); i++){
      
         data.AddtoEvents(JOptionPane.showInputDialog("Enter an event:"),i);
      
         Object[] possibleValues = { "Easy", "Difficult", "Hard" };
      
         System.out.println (selectedValue = JOptionPane.showInputDialog(null,"How Difficult will this task be?", "Input",JOptionPane.INFORMATION_MESSAGE, null,possibleValues, possibleValues[0]));
      
         System.out.println (inputValue = JOptionPane.showInputDialog("How many minutes will it take to complete this task?:"));
      
      }
      OpenJFrame("hi");
   }
   JFrame frame = new JFrame();
   
   public void OpenJFrame(String Events){
      eventNames = "jeff";
      frame.getContentPane().add(new Schedulermain());
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setSize(500,500);
      frame.setLocationRelativeTo(null);
      frame.setVisible(true); 
   }

   public String gettempEvent(int eventNum){
      return "asdf";
   }

   public void addEvent(String event){
      eventNames = eventNames + event;
   }


   public void paint(Graphics g)
   {

   }
   
   public void drawString(String input, Graphics g){
      g.drawString(data.getEventsArray(), 25, 25);
   }


}



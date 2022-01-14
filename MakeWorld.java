import java.util.Random;
public class MakeWorld{
   private int worldSize;
   double[]z;
   double[] x;
   double[] y;
   int[] xD;
   int[] yD;
   int[] zD;
   int floorX;
   int floorY;
   int Spikes = 10;
   public MakeWorld(int worldSizeIn){
      xD = new int[Spikes];
      yD = new int[Spikes];
      zD = new int [Spikes];
      for (int i = 0; i<Spikes; i++){
      Random rand = new Random();
      xD[i] = rand.nextInt(480);
      yD[i] = rand.nextInt(1440);
      zD[i] = 1+rand.nextInt(3);
      }
      worldSize = worldSizeIn+13;
      while (worldSize%13 != 0)
         worldSize++;
            x = new double[worldSize];
      y = new double[worldSize];
      z = new double[worldSize];
      int i = 0;
      floorX = 0;
      floorY = 0;
      for (int w = 0; w < Math.sqrt(worldSize-5); w++){//{while(i<worldSize-13){
      floorX = 0;
      floorY+=45;
      for (int miniW = 0; miniW < Math.sqrt(worldSize-5); miniW++){
         floorX+=15;
            for (int f = i; f<i+5; f++){
               PlaceBlock(i, Math.random()*360);
               if (f<x.length){
                  double tempx = y[f];
                  y[f] = y[i] + x[f]-x[i];
                  z[f] = z[i] + y[f]-y[i];
                  x[f] = x[i] + tempx-z[i];
               }
            }
         i+=4;
      }
      }

      {
      for (int f = 0; f<x.length; f++){
      
      double brightness = 1;
      double Distance = 0;
      for (int index = 0; index <yD.length; index++){
      Distance = zD[index]*(Math.sqrt(((y[f]-yD[index])*(y[f]-yD[index]))+((x[f]-xD[index])*(x[f]-xD[index]))));
      if (Distance != 0 && Distance/5 < 50)
         brightness+=(50/(Distance/5));
      }
      brightness/=yD.length;
      z[f] = brightness*-25;
      /*
         int d = findClosestDistance(x[f], y[f], x, y, z);
         if (d != -1 && z[f] == -999)
            z[f] = z[d];
         else if (z[f] == -999)
            z[f] = z[0];
            */
      }
   }
   }
   public double getNoiseZ(double Wx, double Wy){
      double brightness = 1;
      double Distance = 0;
      for (int index = 0; index <yD.length; index++){
      Distance = zD[index]*(Math.sqrt(((Wy-yD[index])*(Wy-yD[index]))+((Wx-xD[index])*(Wx-xD[index]))));
      if (Distance != 0 && Distance/5 < 50)
         brightness+=(50/(Distance/5));
      }
      brightness/=yD.length;
      return(brightness*-25);
   }
   public int findClosestDistance(double f, double t, double[] xl, double[] yl, double z1[]){
      double minDistance = 100;
      int index = -1;
      for (int i = 0; i<x.length; i++){
         double x = xl[i];
         double y = yl[i];
         double distance = Math.sqrt(((x-f)*(x-f)) + ((y-f)*(y-f)));
         if (distance < minDistance && distance != 0 && z1[i] != -999){
            index = i;
            distance = minDistance;
            }
      }
      return index;
   }
   public void PlaceBlock(int i, double rot){
      if (!(i >= x.length)){
         y[i] = floorY;
         x[i] = floorX;
         z[i] = -20 + (Math.random()*10);
      }
      //System.out.println("x " + x[i] + " y " + y[i]);
      if (!(i+1 >= x.length)){
         z[i+1] = z[i];
         x[i+1] = x[i];
         y[i+1] = y[i]+ 45;
      }
      if (!(i+2 >= x.length)){
         z[i+2] = z[i];
         x[i+2] = x[i] + 15;
         y[i+2] = y[i] + 45;
      }
      if (!(i+3 >= x.length)){
         z[i+3] = z[i];
         x[i+3] = x[i] + 15;
         y[i+3] = y[i];
      }
      if (!(i+4 >= x.length)){
         y[i+4] = y[i];
         x[i+4] = x[i];
         z[i+4] = z[i];
      }
}
   public double getBlockZ(int blockNum){
      return z[blockNum];
   }
   
   public double getBlockZ(int blockNum, double frames, double level){
      if (z[blockNum] < level)
      return z[blockNum] + Math.sin((frames/60 + y[blockNum])) + Math.cos((frames/60 + x[blockNum]));
      else
      return z[blockNum];
   }
   
   public double getBlockY(int blockNum){
      return y[blockNum];
   }
   public double getBlockX(int blockNum){
      return x[blockNum];
   }
   public int getSize(){
      return x.length;
   }
   /*
if (rot > 90){
double tempx;
      if (!(i >= x.length)){
      tempx = y[i];
      y[i] = y[i] + x[i]-x[i];
      z[i] = z[i] + y[i]-y[i];
      x[i] = x[i] + tempx-y[i];
      }
      if (!(i+1 >= x.length)){
      tempx = y[i+1];
      y[i+1] = y[i] + x[i+1]-x[i];
      z[i+1] = z[i] + y[i+1]-y[i];
      x[i+1] = x[i] + tempx-y[i];
      }
      if (!(i+2 >= x.length)){
      tempx = y[i+2];
      y[i+2] = y[i] + x[i+2]-x[i];
      z[i+2] = z[i] + y[i+2]-y[i];
      x[i+2] = x[i] + tempx-y[i];
      }
      if (!(i+3 >= x.length)){
      tempx = y[i+3];
      y[i+3] = y[i] + x[i+3]-x[i];
      z[i+3] = z[i] + y[i+3]-y[i];
      x[i+3] = x[i] + tempx-y[i];
      }
      if (!(i+4 >= x.length)){
      tempx = y[i+4];
      y[i+4] = y[i] + x[i+4]-x[i];
      z[i+4] = z[i] + y[i+4]-y[i];
      x[i+4] = x[i] + tempx-y[i];
      }
   }
   */
}
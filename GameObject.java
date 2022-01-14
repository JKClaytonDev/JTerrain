import java.awt.Polygon;
public class GameObject{
   private Polygon p;
   private double d;
   private double z;
   public double Y;
   public GameObject(Polygon pIn, double Distance, double ZIN, double Yin){
      Y = Yin;
      p = pIn;
      d = Distance;
      z = ZIN;
   }
   public double getY(){
      return Y;
   }
   public double getDistance(){
      return d;
   }
   public Polygon getPoly(){
      return p;
   }
   public static void mergeSort(int[] a){
      mergeSort_srt(a, 0, a.length-1);
   }
   public double getZ(){
      return z;
   }
   public static void mergeSort_srt(int array[],int lo, int n){
      int low = lo;
      int high = n;
      if (low >= high) {
         return;
      }
      int middle = (low + high) / 2;
      mergeSort_srt(array, low, middle);
      mergeSort_srt(array, middle + 1, high);
      int end_low = middle;
      int start_high = middle + 1;
      while ((lo <= end_low) && (start_high <= high)) {
         if (array[low] < array[start_high]) {
            low++;
         } 
         else {
            int Temp = array[start_high];
            for (int k = start_high- 1; k >= low; k--) {
               array[k+1] = array[k];
            }
            array[low] = Temp;
            low++;
            end_low++;
            start_high++;
         }
      }
   }
}
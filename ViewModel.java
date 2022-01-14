public class ViewModel{
private int worldSize;
double[]z;
double[] x;
double[] y;
public ViewModel(){
x = new double[12];
y = new double[12];
z = new double[12];
int i = 0;
y[i] = 0;
x[i] = 0;
z[i] = 0;

z[i+1] = z[i] + 15;
x[i+1] = x[i];
y[i+1] = y[i];

z[i+2] = z[i] + 7;
x[i+2] = x[i] + 3;
y[i+2] = y[i] + 7;

z[i+3] = z[i] + 15;
x[i+3] = x[i];
y[i+3] = y[i] + 15;

z[i+4] = z[i];
x[i+4] = x[i];
y[i+4] = y[i] + 15;

z[i+5] = z[i] + 15;
x[i+5] = x[i];
y[i+5] = y[i];

i = 6;
y[i] = 0;
x[i] = 0;
z[i] = 0;

z[i+1] = z[i] + 15;
x[i+1] = x[i];
y[i+1] = y[i];

z[i+2] = z[i] + 7;
x[i+2] = x[i] + 3;
y[i+2] = y[i] + 7;

z[i+3] = z[i] + 15;
x[i+3] = x[i];
y[i+3] = y[i] + 15;

z[i+4] = z[i];
x[i+4] = x[i];
y[i+4] = y[i] + 15;

z[i+5] = z[i] + 15;
x[i+5] = x[i];
y[i+5] = y[i];
}
public double getBlockZ(int blockNum){
return z[blockNum];
}
public double getBlockY(int blockNum){
return y[blockNum];
}
public double getBlockX(int blockNum){
return x[blockNum];
}
public int getSize(){
return worldSize;
}
}
public class AnimationTimer{
private double length;
private boolean done;
public AnimationTimer(double lengthIn, boolean doneIn){
length = lengthIn;
done = false;
}
public AnimationTimer(){
length = 1.0;
done = false;
}
public Boolean done(){
return done;
}
}
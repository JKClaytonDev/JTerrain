import java.util.*;
public class Schedulerdata   {
private String [] Events = new String[20];

public String getEventsArray(){
System.out.println(Arrays.toString(Events));
return Arrays.toString(Events);
}
public void AddtoEvents(String answerChoice, int i){
System.out.println(answerChoice);
Events[i] = answerChoice;
System.out.println(Arrays.toString(Events));
}

}
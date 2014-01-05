import java.util.*;

public class RequestItem extends EventObject{
	
	int pid; 	//Unique Id for person.
	int from;	//Floor coming from or waiting on.
	int to;		//Destination floor.	
	int time;	//request time.
	int weight; //weight of person (1 = normal, 2 = trolley)

	public RequestItem(Object source){
      	super(source);
   	}

   	public int getID(){
      	return pid;
   	}
	
	public int getTo(){
		return to;
	}
	
	public int getFrom(){
		return from;
	}

	public int getTime(){
		return time;
	}

	public int getWeight(){
		return weight;
	}

	public void setID(int i){
    	pid = i;
   	}

	public void setTo(int i){
		to = i;
	}

	public void setFrom(int i){
		from = i;
	}

	public void setTime(int i){
		time = i;
	}

	public void setWeight(int i){
		weight = i;
	}
}

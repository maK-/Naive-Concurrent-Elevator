import java.io.*;
import java.util.*;
import java.lang.Thread;

/*
This class adds persons to the floor queues
with each person having random credentials
*/

class Person extends Thread{
	private int id = 0;
	private int from;
	private int to;
	private int t;
	private int weight;
	private String data;
	RequestItem request;
	StoreRequests[] queue;
	Random random = new Random();

	public Person(StoreRequests[] q){
      	queue = q;
	}

	public void run(){
    	while(true){
         	/*
			Generate random beginning floor and destination floor 
			making sure they are not the same.
			*/
         	from = random.nextInt(9);
         	to = random.nextInt(9);
			while(from == to){
				to = random.nextInt(9); 
			}
			weight = random.nextInt(2) + 1;
			t = random.nextInt(300)+50; //random time between .05-.3 seconds 
         	

			/*
			Creating our request object.
			*/
			request = new RequestItem(this);
			request.setID(id);
         	request.setTo(to);
         	request.setFrom(from);
			request.setWeight(weight);
         	id++;
								
			queue[request.getFrom()].add(request);
			try{
				AirportElevator.accessFile.acquire(); 
				data = "Person "+request.getID()+" made request on floor "+request.getFrom()+" going to floor "+request.getTo();
				System.out.println(data);
				AirportElevator.toFile(data+"\n"); 
			}
			catch (InterruptedException ex) {}
			finally{
				AirportElevator.accessFile.release();
			}
			try{
            	sleep(t); //Sleep for a random amount of time before creating more persons	
         	}
         	catch (InterruptedException e) {}
      	}
	}
}

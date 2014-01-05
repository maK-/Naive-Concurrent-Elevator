import java.lang.Thread;
import java.util.concurrent.Semaphore;
import java.util.*;
import java.io.*;

public class AirportElevator{
	public static final int floors = 10;

	public static Semaphore accessFile = new Semaphore(1);

	public static void main(String[] args){
      	StoreRequests[] elevator, queue;
      	Person p;
      	Elevator e;
      	elevator = new StoreRequests[floors];
      	queue = new StoreRequests[floors];
		
      	for(int i=0; i<floors; i++){
			elevator[i] = new StoreRequests();
        	queue[i] = new StoreRequests();
      	}
		p = new Person(queue);
      	e = new Elevator(elevator, queue);
      	// start the threads
      	p.start();
      	e.start();
  	}

	//write string data to a file
    public static void toFile(String data){
        File file = new File("output.dat");
        try{
            FileWriter dataf = new FileWriter(file, true);
            dataf.write(data);
            dataf.close();
        }
        catch(FileNotFoundException e){
            System.out.println("\nFile not found!");
        }
        catch(IOException e){
            System.out.println("\nIO Error!");
        }
    }
}

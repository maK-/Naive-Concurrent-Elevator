import java.util.*;
import java.lang.Thread;

/*
This elevator simply moves up and down, from floor 0 - 9
it takes requests from each floor and adds them to the 
elevators own request list

This is a naive solution without the elevator waiting 
on a floor until a request is made before moving.
*/

public class Elevator extends Thread{
	RequestItem request;
	StoreRequests[] elevator, queue;
	int currentFloor;
	boolean direction;
	int room = 10; //Max capacity of elevator
	int capacity; //Current capacity of elevator
	String data = "";

	public Elevator(StoreRequests[] e, StoreRequests[] q){
		currentFloor = 0; //start at bottom floor.
		elevator = e;
		queue = q;
		direction = true; //true is the up direction, false is down.
		capacity = 0; //starts off empty.
	}

	public boolean nobodyWaiting(StoreRequests[] q){
		/*
		If count is equal to 0, noone is waiting, elevator stays on same floor.
		*/
		int count = 0;
		for(int i=0; i<10;i++){
			if(!q[i].isEmpty()){
				count++;
			}
		}
		if(count == 0){
			return true;
		}
		else{
			return false;
		}
	}

	public void run(){
		while(true){
			if(!nobodyWaiting(queue)){
				/*
				Remove all persons from the elevator if they are at their floor
				*/
				while(elevator[currentFloor].isEmpty() == false){
					request = elevator[currentFloor].remove();
					try{
						AirportElevator.accessFile.acquire();	
						data = "Person "+request.getID()+" has left the elevator at their destination on floor "+currentFloor;
						System.out.println(data);
						AirportElevator.toFile(data+"\n");
					}
					catch (InterruptedException ex) {}
					finally{
						AirportElevator.accessFile.release();
					}	
					capacity = capacity - request.getWeight();
				}	
		
				/*
				While the q on this floor is not empty and there is room on the elevator
				add requests from this floor to the list of requests for destination floors on the elevator.
				*/	
				while(queue[currentFloor].isEmpty() == false){
					if(capacity < room){
						request = queue[currentFloor].remove();
						try{
							AirportElevator.accessFile.acquire();	
							data = "Person "+request.getID()+" has boarded the elevator on floor "+currentFloor+" going to floor "+request.getTo();
							System.out.println(data);
							AirportElevator.toFile(data+"\n");
						}
						catch (InterruptedException ex) {}
						finally{
							AirportElevator.accessFile.release();
						}
	
						elevator[request.getTo()].add(request);
						capacity = capacity + request.getWeight();
					}
					else{
						//met capacity
						System.out.println("Elevator is full");
						break; //Break from loop
					}
				}
	
				/*
				Changing the direction of the elevator when hits bottom or top floor.
				*/
				if(currentFloor == 0){
					direction = true;
				}
				if(currentFloor == 9){
					direction = false;
				}
	
				if(direction){
					//System.out.println(currentFloor);
					currentFloor++;
				}
				else{
					//System.out.println(currentFloor);
					currentFloor--;
				}		
		
				try{
					sleep(100); //sleep for 100 ticks between each floor.
				}
				catch(InterruptedException e) {}
			}
			else{
				//Testing if being idle works...
				//System.out.println("Elevator idle at "+currentFloor);
			}
		}
	}
}

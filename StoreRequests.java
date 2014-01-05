import java.util.concurrent.ConcurrentLinkedQueue;
/*
This class implements a ConcurrentLinkedQueue.
It simply wraps this element with the functions I need.
*/


public class StoreRequests{
   ConcurrentLinkedQueue<RequestItem> q;

   public StoreRequests()
   {
      q = new ConcurrentLinkedQueue<RequestItem>();
   }

   public synchronized void add(RequestItem event)
   {
      q.add(event);
   }

   public synchronized RequestItem remove()
   {
      return (RequestItem) q.poll();
   }

   public boolean isEmpty()
   {
      if(q.isEmpty())
         return true;
      else
         return false;
   }
}

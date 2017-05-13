import java.io.IOException;
import java.net.*;

public class Router1 extends Routing {

	public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {

		r1.connect(r0, 1);
		r1.connect(r2, 1);
		r1.connect(r3, Integer.MAX_VALUE);
		
		for(int i = 1; i < numberOfNodes; i++) {
			
			System.out.println("RUNNING ITERATION " + i);
			
			DatagramSocket socket = new DatagramSocket(port);
			DatagramSocket sender = new DatagramSocket();
			
			display(r1, "Client Router 1 Displaying it's data");
	
			serving(r1, socket);
			
			Thread.sleep(500);
			
			processing(1, r1, sender);
	
			passControl("afsaccess3.njit.edu", sender);
	
			serving(r1, socket);
			
			socket.close();
			sender.close();
			tables.clear();
		}
		System.out.println();
		display(r1, "Final Display of Router 1");
	}
}

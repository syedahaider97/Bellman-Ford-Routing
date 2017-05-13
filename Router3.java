import java.io.IOException;
import java.net.*;

public class Router3 extends Routing {

	public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {

		r3.connect(r0, 7);
		r3.connect(r1, Integer.MAX_VALUE);
		r3.connect(r2, 2);

		for(int i = 1; i < numberOfNodes; i++) {
			
			System.out.println("RUNNING ITERATION " + i);
			
			DatagramSocket socket = new DatagramSocket(port);
			DatagramSocket sender = new DatagramSocket();
			
			display(r3, "Client Router 3 Displaying it's data");
	
			serving(r3, socket);
			
			Thread.sleep(500);
			
			processing(3, r3, sender);
	
			for (int j = 1; j <= 3; j++) {
				passControl("afsaccess" + Integer.toString(j) + ".njit.edu", sender);
			}
			socket.close();
			sender.close();
			tables.clear();
		}
		System.out.println();
		display(r3, "Final Display of Router 2");
	}
}
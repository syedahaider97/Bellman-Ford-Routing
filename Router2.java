import java.io.IOException;
import java.net.*;

public class Router2 extends Routing {

	public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {

		r2.connect(r0, 3);
		r2.connect(r1, 1);
		r2.connect(r3, 2);

		for(int i = 1; i < numberOfNodes; i++) {
			
			System.out.println("RUNNING ITERATION " + i);
			
			DatagramSocket socket = new DatagramSocket(port);
			DatagramSocket sender = new DatagramSocket();
			
			display(r2, "Client Router 2 Displaying it's data");
	
			serving(r2, socket);
			
			Thread.sleep(500);
			
			processing(2, r2, sender);
	
			passControl("afsaccess4.njit.edu", sender);
	
			serving(r2, socket);
			
			socket.close();
			sender.close();
			tables.clear();
		}
		System.out.println();
		display(r2, "Final Display of Router 2");
	}
}
import java.io.IOException;
import java.net.*;

public class Router0 extends Routing {

	public static void main(String args[]) throws IOException, ClassNotFoundException, InterruptedException {

		r0.connect(r1, 1);
		r0.connect(r2, 3);
		r0.connect(r3, 7);		

		for(int i = 1; i < numberOfNodes; i++) {
			
			System.out.println("RUNNING ITERATION " + i);
			
			DatagramSocket socket = new DatagramSocket();
			DatagramSocket receiver = new DatagramSocket(port);
			
			display(r0, "Client Router 0 Displaying it's data");
			Thread.sleep(500);
			processing(0, r0, socket);
	
			passControl("afsaccess2.njit.edu", socket);
	
			serving(r0, receiver);
			
			socket.close();
			receiver.close();
			tables.clear();
		}
		System.out.println();
		display(r0, "Final Display of Router 0");
		
	}
}

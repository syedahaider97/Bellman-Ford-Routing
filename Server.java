import java.io.IOException;
import java.net.*;

public class Server {

	public static void main(String args[]) throws IOException, ClassNotFoundException {
		
		Router r0 = new Router("r0");
		Router r1 = new Router("r1");
		Router r2 = new Router("r2");
		Router r3 = new Router("r3");
		
		r0.connect(r1, 1);
		r0.connect(r2, 3);
		r0.connect(r3, 7);
		r1.connect(r0, 1);
		r1.connect(r2, 1);
		r1.connect(r3, Integer.MAX_VALUE);
		r2.connect(r0, 3);
		r2.connect(r1, 1);
		r2.connect(r3, 2);
		r3.connect(r0, 7);
		r3.connect(r1, Integer.MAX_VALUE);
		r3.connect(r2, 2);
		
		
		
		
		System.out.println("Server Router Displaying it's Data");
		Router.display(r2.getTable());
		DatagramSocket socket = new DatagramSocket(1332);
		InetAddress ip = InetAddress.getByName("afsaccess1.njit.edu");
		
		while(true) {
			
			byte[] b1 = new byte[1024];
			DatagramPacket packet = new DatagramPacket(b1,b1.length);
			socket.receive(packet);
			
			Message received = (Message) Message.deserialize(packet.getData());

			if(received.getHeader().get("MessageType").equals("request")) {
				Message response = new Message(received.getHeader().get("VersionNumber"),"response",received.getHeader().get("Identifier"));
				response.setBody(r2.getTable());
				byte[]b2 = Message.serialize(response);
				DatagramPacket packet2 = new DatagramPacket(b2,b2.length,ip,packet.getPort());
				socket.send(packet2);
			}
			else if(received.getHeader().get("MessageType").equals("update")) {
				System.out.println("Server Displaying Asynchronous Push From Client {" + packet.getAddress() + " " + packet.getPort() + "}");
				Router.display(received.getBody());
			}
		}
	}
}

import java.net.*;
import java.io.*;

public class Client {

	public static void main(String args[]) throws UnknownHostException, IOException, ClassNotFoundException {
		
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
		
		
		
		System.out.println("Client Router Displaying it's data");
		Router.display(r1.getTable());
		System.out.println();
		
		
		DatagramSocket socket = new DatagramSocket();
		InetAddress ip = InetAddress.getByName("afsaccess2.njit.edu");
		Message message = new Message("1","request","1");
		message.setBody(r1.getTable());
		byte[] b1 = Message.serialize(message);
		DatagramPacket packet = new DatagramPacket(b1, b1.length, ip, 1332);
		
		socket.send(packet);
			
		byte[] b2 = new byte[1024];
		DatagramPacket packet2 = new DatagramPacket(b2,b2.length);
		socket.receive(packet2);
		
		Message received = (Message) Message.deserialize(packet2.getData());
		System.out.println("Client Router Displaying Server Router's Data {" + packet2.getAddress() + " " + packet2.getPort() + "}");
		Router.display(received.getBody());
		
		Message advertise = new Message("1","update","2");
		advertise.setBody(r1.getTable());
		byte[]b3 = Message.serialize(advertise);
		DatagramPacket packet3 = new DatagramPacket(b3,b3.length,ip,1332);
		
		socket.send(packet3);		
	}
}

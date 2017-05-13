import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Routing {

	static ArrayList<HashMap<String, Integer>> tables = new ArrayList<HashMap<String, Integer>>();
	
	static int port = 1332;
	static int numberOfNodes = 4;
	
	static Router r0 = new Router("r0");
	static Router r1 = new Router("r1");
	static Router r2 = new Router("r2");
	static Router r3 = new Router("r3");

	public static void display(Router router, String message) {
		System.out.println(message);
		Router.display(router.getTable());
		System.out.println();
		System.out.println();
	}

	public static void processing(int routerNumber, Router router, DatagramSocket socket)throws ClassNotFoundException, IOException {

		for (int i = 1; i <= 4; i++) {
			if (i == routerNumber + 1)
				continue;
			InetAddress ip = InetAddress.getByName("afsaccess" + Integer.toString(i) + ".njit.edu");
			Message request = new Message("1", "request", "1");
			byte[] b1 = Message.serialize(request);
			DatagramPacket packet = new DatagramPacket(b1, b1.length, ip, port);

			socket.send(packet);
		
			byte[] b2 = new byte[1024];
			DatagramPacket packet2 = new DatagramPacket(b2, b2.length);
			socket.receive(packet2);

			Message received = (Message) Message.deserialize(packet2.getData());
			System.out.println("Client Router Displaying  " + (i - 1) + " Server Router's Data {" + packet2.getAddress() + " "
					+ packet2.getPort() + "}");
			Router.display(received.getBody());
			System.out.println();
			tables.add((HashMap<String, Integer>) received.getBody());
		}
		for (HashMap<String, Integer> entries : tables) {
			String currentRouter = "r" + ((tables.indexOf(entries) >= routerNumber)
					? Integer.toString(tables.indexOf(entries) + 1) : Integer.toString(tables.indexOf(entries)));
			for (String entry : entries.keySet()) {
				if ((router.getTable().get(currentRouter) + entries.get(entry) < router.getTable().get(entry))
						&& router.getTable().get(currentRouter) + entries.get(entry) > 0) {
					router.getTable().put(entry, router.getTable().get(currentRouter) + entries.get(entry));
				}
			}
		}
	}

	public static void serving(Router router, DatagramSocket socket) throws IOException, ClassNotFoundException {

		while (true) {

			byte[] b1 = new byte[1024];
			DatagramPacket packet = new DatagramPacket(b1, b1.length);
			socket.receive(packet);
			Message received = (Message) Message.deserialize(packet.getData());

			if (received.getHeader().get("MessageType").equals("request")) {
				Message response = new Message(received.getHeader().get("VersionNumber"), "response",
						received.getHeader().get("Identifier"));
				response.setBody(router.getTable());
				byte[] b2 = Message.serialize(response);
				DatagramPacket packet2 = new DatagramPacket(b2, b2.length, packet.getAddress(), packet.getPort());
				socket.send(packet2);
			} else if (received.getHeader().get("MessageType").equals("complete")) {
				break;
			}
		}
	}

	public static void passControl(String address, DatagramSocket socket) throws IOException {
		Message complete = new Message("1", "complete", "2");
		byte[] b1 = Message.serialize(complete);
		InetAddress ip = InetAddress.getByName(address);
		DatagramPacket packet = new DatagramPacket(b1, b1.length, ip, port);

		socket.send(packet);
	}

}

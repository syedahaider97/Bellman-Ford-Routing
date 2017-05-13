import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class Message implements Serializable {

	String versionNumber;
	String messageType;
	String identifier;

	Map<String, Integer> body = new HashMap<String, Integer>();

	public Message(String version, String message, String id) {
		versionNumber = version;
		messageType = message;
		identifier = id;
	}

	public void setBody(Map<String, Integer> table) {
		body = table;
	}

	public Map<String, Integer> getBody() {
		return body;
	}

	public HashMap<String, String> getHeader() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("VersionNumber", versionNumber);
		map.put("MessageType", messageType);
		map.put("Identifier", identifier);
		return map;

	}

	public static byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(obj);
		return out.toByteArray();
	}

	public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream is = new ObjectInputStream(in);
		return is.readObject();
	}

}

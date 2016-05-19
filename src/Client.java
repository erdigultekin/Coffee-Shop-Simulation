
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client{
	public static Shop connect(Shop shop) throws IOException{
		// localhost ip
		String ip = ClientInterface.IPAddress;
		int port = 4444;
		Socket socket = null;

		try {
			//connect
			socket = new Socket(ip, port);

			//initialize streams
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

			System.out.println(shop.name+"'s shop's day before sending is : "+shop.day);
			System.out.println("Trying to send "+shop.name+"'s shop to the Server.");
			out.writeObject(shop);
			System.out.println("Sent "+shop.name+"'s shop to the Server.");
			shop = (Shop) in.readObject();
			System.out.println("Read "+shop.name+"'s shop from the Server.");

			System.out.println(shop.name+"'s shop's day after receiving is : "+shop.day);

			in.close();
			out.close();

		}
		catch (IOException e){
			e.printStackTrace();
			if (!socket.isClosed()){socket.close();}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return shop;
	}
}
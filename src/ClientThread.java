import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ClientThread extends Thread{

	private Socket socket = null;
	private ObjectInputStream in = null;
	private ObjectOutputStream out = null;

	//Constructor
	public ClientThread(Socket socket){
		this.socket = socket;
	}

	public void run(){
		try {
			// Streams :)
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());

			Shop shop = new Shop("Trial Shop");

			System.out.println("Trying to read the Shop Object");

			shop = (Shop) in.readObject();

			System.out.println("Shop Object is read. Its name is : "+shop.name);

			System.out.println("Shop object before engine start running: ");
			Engine.printShopDetails(shop);

			/*
			 * Engine part followed from here. Each thread here follows the logic:
			 * 0-) Check Day consistency between Server and Client. If not consistent send it back properly.
			 * 1-) Update the Teacher's player list. 
			 * 2-) Wait for Teacher's approval and Engine's work to be done.
			 * 3-) Get the proper shop that is updated by the Engine.
			 * 4-) Increment shop's day.
			 * 5-) Send new shop to the client
			 */

			System.out.println("Engine part is beginning");

			if(!Engine.checkDayConsistency(shop)){
				//Mistaken shops drop here. Send them properly.
				out.writeObject(shop);
				for(int i=0; i<Server.clientsConnected.length;i++){
					if(Server.clientsConnected[i]!=null){
						if(Server.clientsConnected[i].equals(this)){
							Server.clientsConnected[i] = null;
						}
					}
				}
				return;
			}
			
			shop.dailySales = 0;

			Engine.updatePlayerList(shop);

			System.out.println("Updated player list in teacher gui");

			Engine.controlTeachersApproval();

			System.out.println("Teacher approved");

			shop = Engine.getProperShop(shop);

			shop.day++;

			System.out.println("Returning new Shop Object to client");

			out.writeObject(shop);

			for(int i=0; i<Server.clientsConnected.length;i++){
				if(Server.clientsConnected[i]!=null){
					if(Server.clientsConnected[i].equals(this)){
						Server.clientsConnected[i] = null;
					}
				}
			}

			System.out.println("Shop object after engine finished: ");
			Engine.printShopDetails(shop);


		} catch (IOException e) {
			System.out.println("Client disconnected!");
			for(int i=0; i<Server.clientsConnected.length;i++){
				if(Server.clientsConnected[i]!=null){
					if(Server.clientsConnected[i].equals(this)){
						Server.clientsConnected[i] = null;
					}
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
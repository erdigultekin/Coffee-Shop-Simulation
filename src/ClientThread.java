
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ClientThread extends Thread{

    private ClientThread[] clientsConnected;
    private Socket socket = null;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;
    private String clientName = null;
    private Teacher teacher;

    //Constructor
     public ClientThread(Socket socket, ClientThread[] clientsConnected){
        this.socket = socket;
        this.clientsConnected = clientsConnected;
    }

    public void run(){
        try {
            // Streams :)
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());

            String message = null;

            Shop shop = new Shop("Trial Shop");
            
            System.out.println("Trying to read the Shop Object");
            
            shop = (Shop) in.readObject();
            
            System.out.println("Shop Object is read. Its name is : "+shop.name);
            
            /*
             * Engine part followed from here. Each thread here follows the logic:
             * 0-) Check Day consistency between Server and Client. If not consistent send it back properly.
             * 1-) Update the Teacher's player list. 
             * 2-) Wait for Teacher's approval and Engine's work to be done(Model is running).
             * 3-) Get the proper shop that is updated by the Engine.
             * 4-) Increment shop's day.
             * 5-) Send new shop to the client
             */
            
            System.out.println("Engine part is beginning");
            
            if(!Engine.checkDayConsistency(shop)){
            	//Mistaken shops drop here. Send them properly.
            	out.writeObject(shop);
            }
            
            Engine.updatePlayerList(shop);
            
            System.out.println("Updated player list in teacher gui");
            
            Engine.controlTeachersApproval();
            
            System.out.println("Teacher approved");
            
            shop = Engine.getProperShop(shop);
            
            shop.day++;
            
            System.out.println("Returning new Shop Object to client");
            
            out.writeObject(shop);
            
        } catch (IOException e) {
            System.out.println("Client disconnected!");
            this.clientsConnected = null;
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
    // Every instance of this class ( the client ) will have this method.
    private void sendMessage(String mess, String name){
        try {
            out.writeUTF(name + " says: " + mess);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
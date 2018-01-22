import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    private final static String    HOST    = "10.0.0.170";
    private final static int       PORT    = 4242;
	public  final static String    NAME    = "EpicPlayer1337";
	private final static Player    PLAYER  = null;

    public static void main(String[] args) {

        Store store = new Store();
        Communicator communicator = null;
        try {
            communicator = new Communicator(store, PORT, InetAddress.getByName(HOST),
                    NAME);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't create Socket...");
            e.printStackTrace();
            System.exit(1);
        } 

        communicator.start();

        while (true) {
        	PLAYER.play(store.getGameState());
        }

    }
}

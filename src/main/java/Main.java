import java.io.IOException;
import java.lang.System;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

	private final static String     HOST    = "localhost";
	private final static int        PORT    = 4242;
	private final static String     NAME    = "EpicPlayer1337";
	private final static Player     PLAYER  = new Bot();

	public static Boolean   IN_GAME = false; // TODO: this variable doesn't synchronize across threads...

	public static void main(String[] args) {

		Store store = new Store();
		Communicator communicator = null;
		try {
			communicator = new Communicator(store, PORT, InetAddress.getLocalHost(), NAME); // Debug
			//communicator = new Communicator(store, PORT, InetAddress.getByName(HOST), NAME);
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
			if(IN_GAME) {
				store.setDesiredPaddleState(PLAYER.play(store.getGameState()));
			}
		}

	}
}

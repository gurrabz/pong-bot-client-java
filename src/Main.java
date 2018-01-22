public class Main {

	private final static String  HOST     = "localhost:8456";
	public  final static String  NAME     = "EpicPlayer1337";
	private final static Player  player   = null;

    public static void main(String[] args) {

    	Store store = new Store();
        Communicator communicator = new Communicator(store, HOST);
        communicator.start();

        while (true) {
        	player.play(store.getGameState());
        }

    }
}

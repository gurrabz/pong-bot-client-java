import pong.GameState;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Communicator {
	private final Store store;
	private final Thread collectorThread, pusherThread;

	public Communicator(Store store, String host){
		this.store = store;
		this.collectorThread = new Thread(new Collector());
		this.pusherThread = new Thread(new Pusher());
		this.collectorThread.setDaemon(true);
		this.pusherThread.setDaemon(true);
	}

	public void start(){
		collectorThread.start();
		pusherThread.start();
	}

	private class Collector implements Runnable {

		@Override
		public void run() {
			GameState newGameState;
			while (true){
				// todo get som string from tcp
				store.setGameState(parseGameState("test"));
			}

		}

		private GameState parseGameState(String input) {
			// todo
			throw new NotImplementedException();
		}
	}

	private class Pusher implements Runnable {

		@Override
		public void run() {
			// todo send
			store.getDesiredPaddleState();
		}
	}
}

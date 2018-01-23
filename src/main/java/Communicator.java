import java.io.*;
import java.net.Socket;

import java.net.InetAddress;

import Communication.ClientId;
import Communication.ClientName;
import com.google.gson.Gson;
import pong.GameState;

public class Communicator extends Thread {
	
	private final Gson gson = new Gson();

	private final Store store;
	private Thread collectorThread, pusherThread;
	private Socket socket;
	
	private final String NAME;
	private int clientId;

	public Communicator(Store store, int port, InetAddress inetAddress, String name) throws IOException {
		this.store = store;
		this.socket = new Socket(inetAddress, port);
		this.NAME = name;
		setup(); // Should (theoretically) handle initial communication with server
		this.collectorThread = new Thread(new Collector(socket.getInputStream()));
		this.pusherThread = new Thread(new Pusher(socket.getOutputStream(), this.clientId));
	}

	public void start(){
		collectorThread.start();
		pusherThread.start();
	}
	
	private void setup() {
		try {
			BufferedReader bis = new BufferedReader(new InputStreamReader(socket
					.getInputStream()));
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			while(true) {
				if(bis.ready()){
					// Receive client id
					StringBuilder sb = new StringBuilder();
					bis.lines().forEach(sb::append);
					ClientId clientId = gson.fromJson(sb.toString(), ClientId.class);
					this.clientId = clientId.id;
					
					// Respond with client name
					dos.writeChars(gson.toJson(new ClientName(NAME)));
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private class Collector implements Runnable {

		private final BufferedReader INPUT_STREAM;

		public Collector(InputStream stream) {
			this.INPUT_STREAM = new BufferedReader(new InputStreamReader(stream));
		}

		@Override
		public void run() {
			GameState newGameState;
			while (true) {
				String input = null;
				try {
					if(INPUT_STREAM.ready()) {
						input = read();
						// TODO: Should we check validity of input???
						newGameState = gson.fromJson(input, GameState.class);
						store.setGameState(newGameState);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		
		private String read() {
			StringBuilder sb = new StringBuilder();
			INPUT_STREAM.lines().forEach(sb::append);
			return sb.toString();
		}
		
	}

	private class Pusher implements Runnable {

		private final DataOutputStream OUTPUT_STREAM;
		private final int ID;

		public Pusher(OutputStream stream, int id) {
			this.OUTPUT_STREAM = new DataOutputStream(stream);
			this.ID = id;
		}

		@Override
		public void run() {
			// todo send
			String data;
			while(true) {
				data = gson.toJson(store.getDesiredPaddleState());
				send(data);
			}
			
		}
		
		/**
		 * Sends data to the server.
		 * @param data
		 * @return true if the data was sent correctly, else false.
		 */
		public boolean send(String data) {
			try {
				OUTPUT_STREAM.writeChars(data);
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
	}
}

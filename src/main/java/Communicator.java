import java.io.*;
import java.net.Socket;

import java.net.InetAddress;

import java.lang.System;

import com.google.gson.*;
import communication.*;

import pong.GameState;

/**
 * The Communicator thread is responsible for all the communication with the server.
 */
public class Communicator extends Thread {

	/**
	 * Converter to and from json.
	 */
	private final Gson gson = new Gson();

	private final Store store;
	private final Thread collectorThread, pusherThread;
	private final Socket socket;

	private final String NAME;
	private int clientId;
	private volatile boolean inGame = false;

	public Communicator(Store store, int port, InetAddress inetAddress, String name) throws IOException {
		this.store = store;
		this.socket = new Socket(inetAddress, port);
		this.NAME = name;
		setup();
		this.collectorThread = new Thread(new Collector(socket.getInputStream()));
		this.pusherThread = new Thread(new Pusher(socket.getOutputStream()));
	}

	public void start() {
		collectorThread.start();
		pusherThread.start();
	}

	/**
	 * Performs the initial setup with the server, receiving an ID and sending a Display Name in return.
	 */
	private void setup() {
		try {
			System.out.println("[INFO]: Setup started!");

			// Can throw an I/O-Exception
			BufferedReader bis = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			// Can throw an I/O-Exception
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

			// Receive client id
			StringBuilder sb = new StringBuilder();
			sb.append(bis.readLine()); // Can throw an I/O-Exception
			System.out.println("[IN]: " + sb.toString());
			ClientId clientId = gson.fromJson(sb.toString(), ClientId.class);
			this.clientId = clientId.id;

			// Respond with client name
			dos.writeBytes(gson.toJson(new ClientName(NAME)) + "\n"); // Can throw I/O-Exception
			System.out.println("[OUT]: " + gson.toJson(new ClientName(NAME)));

			System.out.println("[INFO]: Setup done!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The Collector class is responsible for receiving and parsing data from server.
	 */
	private class Collector implements Runnable {

		/**
		 * Input stream from the server.
		 */
		private final BufferedReader INPUT_STREAM;

		private Collector(InputStream stream) {
			this.INPUT_STREAM = new BufferedReader(new InputStreamReader(stream));
		}

		@Override
		public void run() {
			String input;
			while (true) { // Continuously reads the input from the serer
				try {
					if (INPUT_STREAM.ready()) { // can throw an I/O-Exception
						input = read();
						handleInput(input);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		/**
		 * Parses the input received from the server.
		 *
		 * @param input the input received from the server.
		 */
		private void handleInput(final String input) {
			JsonObject eventObject = ServerEventParser.parse(input);
			ServerEventType event = ServerEventParser.getServerEventType(eventObject);
			switch (event) {
				case START_GAME:
					handleStartGameEvent(eventObject);
					break;
				case END_GAME:
					handleEndGameEvent(eventObject);
					break;
				case GAME_STATE:
					handleGameStateEvent(eventObject);
					break;
				default:
					break;
			}
		}

		private void handleGameStateEvent(JsonObject eventObject) {
			GameState gameState = tryParseGameState(eventObject.getAsJsonObject("game_state"));
			if (gameState == null) {
				System.out.println("[WARN]: Couldn't parse eventObject into GameState!");
				return;
			}
			store.setGameState(gameState);
		}

		private void handleEndGameEvent(JsonObject eventObject) {
			if (inGame) {
				inGame = false;
				System.out.println("[INFO]: Game Ended");
			}
		}

		private void handleStartGameEvent(JsonObject eventObject) {
			if (!inGame) {
				if (eventObject.getAsJsonArray(ServerEventParser.AFFECTED_PLAYERS_MEMBER_NAME)
						.contains(new JsonPrimitive(clientId))) {
					inGame = true;
					System.out.println("[INFO]: Game started");
				}
			}
		}

		/**
		 * Tries to parse jsonObject into a {@link GameState} if possible.
		 *
		 * @param jsonObject the Json formatted String.
		 * @return GameState represented by jsonObject if possible, else null.
		 */
		private GameState tryParseGameState(JsonObject jsonObject) {
			try {
				return gson.fromJson(jsonObject, GameState.class);
			} catch (JsonSyntaxException e) {
				return null;
			}
		}

		/**
		 * Reads data from the server.
		 *
		 * @return a String of read data.
		 */
		private String read() throws IOException {
			String input = INPUT_STREAM.readLine();
			System.out.println(String.format("[IN]: %s", input)); // Debug
			return input;
		}
	}

	/**
	 * The Pusher class is responsible for sending data to the server.
	 */
	private class Pusher implements Runnable {

		/**
		 * The output stream to the server.
		 */
		private final DataOutputStream OUTPUT_STREAM;

		private Pusher(OutputStream stream) {
			this.OUTPUT_STREAM = new DataOutputStream(stream);
		}

		@Override
		public void run() {
			String data;
			while (true) {
				if (inGame) {
					data = store.getDesiredPaddleState().toString();
					send(data + "\n");
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						break;
					}
				}
			}
		}

		/**
		 * Sends data to the server.
		 *
		 * @param data
		 * @return true if the data was sent correctly, else false.
		 */
		private boolean send(String data) {
			try {
				OUTPUT_STREAM.writeBytes(data);
				System.out.println(String.format("[OUT]: %s", data)); // Debug
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
	}
}

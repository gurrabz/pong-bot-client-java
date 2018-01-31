package communication;

import pong.Ball;
import pong.GameState;
import pong.Paddle;

public class GameStateEvent {

	private Ball ball;
	private Paddle[] paddles;

	public GameStateEvent(){

	}

	public GameState toGameState() {
		return new GameState(ball, paddles);
	}

}

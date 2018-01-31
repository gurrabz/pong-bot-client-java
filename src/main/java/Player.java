import pong.GameState;
import pong.Paddle;

public interface Player {
	/**
	 * Should return a {@link Paddle.State} in response to a {@link GameState} received by the server.
	 * @param gameState the GameState received from the server.
	 * @return a Paddle.State
	 */
	Paddle.State play(GameState gameState);
}

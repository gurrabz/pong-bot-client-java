import pong.GameState;
import pong.Paddle;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Bot implements Player {

	/**
	 * Should return a {@link Paddle.State} in response to a {@link GameState} received by the server.
	 * @param gameState the GameState received from the server.
	 * @return a Paddle.State
	 */
    @Override
    public Paddle.State play(GameState state) {
        // Enter your bot logic here...

        // Example...
        return Paddle.State.MOVE_CLOCKWISE;
    }

    public Bot() {

    }

}
import pong.GameState;
import pong.Paddle;

public interface Player {
	Paddle.State play(GameState gameState);
}

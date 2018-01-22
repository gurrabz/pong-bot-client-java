public interface Player {
	String name();
	String host();
	Paddle.State play(GameState gameState);
}

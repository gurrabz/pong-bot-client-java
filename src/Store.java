public class Store {
	private GameState gameState = null;
	private Paddle.State desiredPaddleState = null;

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	public Paddle.State getDesiredPaddleState() {
		return desiredPaddleState;
	}

	public void setDesiredPaddleState(Paddle.State paddleState) {
		this.desiredPaddleState = paddleState;
	}
}

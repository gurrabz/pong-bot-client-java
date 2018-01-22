import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class GameState {
	private final Collection<Paddle> paddles;
	private final Ball ball;

	public GameState(Ball ball, Collection<Paddle> paddles){
		this.ball = ball;
		this.paddles = new ArrayList<>(paddles);
	}

	public GameState(Ball ball, Paddle... paddles){
		this.ball = ball;
		this.paddles = new ArrayList<>(Arrays.asList(paddles));
	}

	public Ball getBall(){
		return this.ball;
	}

	public Collection<Paddle> getPaddles(){
		return new ArrayList<>(paddles);
	}

	public Paddle getMyPaddle(String playerName){

		Paddle paddle = null;
		for (Iterator<Paddle> iter = paddles.iterator(); iter.hasNext(); paddle = iter.next()) {
			if (paddle.getPlayer().equals(Main.NAME)) {
				return paddle;
			}
		}
		throw new IllegalStateException("You are not in the game");
	}
}

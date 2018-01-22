package pong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

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
}

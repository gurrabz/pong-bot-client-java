package pong;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(this.getBall(), this.getPaddles(), "pong.GameState");
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof GameState)) return false;
		return this.getBall().equals(((GameState) o).getBall()) &&
			this.getPaddles().equals(((GameState) o).getPaddles());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("{ball: %s, paddles: {", this.getBall().toString()));
		this.getPaddles().forEach((paddle) -> sb.append(String.format("%n%s", paddle.toString())));
		sb.append(String.format("%n}}"));
		return sb.toString();
	}
}

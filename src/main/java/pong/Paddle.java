package pong;

import java.util.Objects;

public class Paddle {
	private final double angle;
	private final State state;
	private final String player;

	public Paddle(double angle, State state, String player) {
		this.angle = angle;
		this.state = state;
		this.player = player;
	}

	public Paddle(Paddle p){
		this.angle = p.getAngle();
		this.state = p.getState();
		this.player = p.getPlayer();
	}

	public Paddle withState(State s){
		return new Paddle(this.getAngle(), s, this.getPlayer());
	}

	public Paddle withPosition(double a){
		return new Paddle(a, this.getState(), this.getPlayer());
	}

	public Paddle withStateAndPosition(State s, double a){
		return new Paddle(a, s, this.getPlayer());
	}

	public double getAngle() {
		return angle;
	}

	public State getState() {
		return state;
	}

	public String getPlayer() {
		return player;
	}

	public enum State {
		MOVE_CLOCKWISE,
		MOVE_COUNTERCLOCKWISE,
		STOP
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getAngle(), this.getPlayer(), this.getState(), "pong.Paddle");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if(!(o instanceof Paddle)) return false;
		return this.getPlayer().equals(((Paddle) o).getPlayer()) &&
			this.getState() == ((Paddle) o).getState() &&
			this.getAngle() == ((Paddle) o).getAngle();
	}

	@Override
	protected Object clone() {
		return new Paddle(this);
	}

	@Override
	public String toString() {
		return String.format("{player: %s, state: %s, angle: %.2f}", this.getPlayer(), this.getState(), this.getAngle());
	}
}

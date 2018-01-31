package pong;

import java.util.Objects;

public class Ball {
	private final Position position;
	private final Vector velocity;

	public Ball(Position p, Vector v) {
		this.velocity = v;
		this.position = p;
	}

	public Ball(Ball b){
		this.velocity = b.getVelocity();
		this.position = b.getPosition();
	}

	public Ball withVector(Vector v){
		return new Ball(this.position, v);
	}

	public Ball withPosition(Position p){
		return new Ball(p, this.velocity);
	}

	public Position getPosition() {
		return position;
	}

	public Vector getVelocity() {
		return velocity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.position, this.velocity, "pong.Ball");
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Ball) {
			if (this.getPosition().equals(((Ball) o).getPosition()) &&
			    this.getVelocity().equals(((Ball) o).getVelocity()))
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return super.toString(); //Todo
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Ball(this);
	}
}

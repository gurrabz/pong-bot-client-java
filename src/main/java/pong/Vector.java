package pong;

import java.util.Objects;

public class Vector extends Position {

	public Vector(double x, double y) {
		super(x, y);
	}

	public Vector(Vector v) {
		super(v);
	}

	public double magnitude() {
		return Math.sqrt(this.dot(this));
	}

	public Vector product(double d) {
		return new Vector(this.getX() * d, this.getY() * d);
	}

	public double dot(Vector vector) {
		return this.getX() * vector.getX() + this.getY() * vector.getY();
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof Vector)) return false;
		return this.getX() == ((Vector) o).getX() &&
				this.getY() == ((Vector) o).getY();
	}

	@Override
	protected Object clone() {
		return new Vector(this);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, "pong.Vector");
	}

	@Override
	public String toString() {
		return super.toString();
	}
}

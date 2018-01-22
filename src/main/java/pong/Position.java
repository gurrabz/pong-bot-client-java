package pong;

import java.util.Objects;

public class Position implements Cloneable {
	protected final double x, y;

	public Position(double x, double y){
		this.x = x;
		this.y = y;
	}

	public Position(Position p) {
		this.x = p.getX();
		this.y = p.getY();
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public Position add(Position p) {
		return new Position(this.getX() + p.getX(), this.getY() + p.getY());
	}

	public Position negative(Position p) {
		return new Position(-this.getX(), -this.getY());
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(!(o instanceof Position)) return false;
		return this.getX() == ((Position) o).getX() &&
				this.getY() == ((Position) o).getY();
	}

	@Override
	protected Object clone() {
		return new Position(this);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, "pong.Position");
	}

	@Override
	public String toString() {
		return String.format("{x: %.2f, y: %.2f}", this.getX(), this.getY());
	}
}

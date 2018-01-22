package pong;

import java.util.Objects;

public class Position implements Cloneable{
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
		if (o instanceof Position){
			if (this.getX() == ((Position) o).getX() &&
			    this.getY() == ((Position) o).getY())
			{
				return true;
			}
		}
		return false;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Position(this);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, "pong.Position");
	}

	// todo tostring
}

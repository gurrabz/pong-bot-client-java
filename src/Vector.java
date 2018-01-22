import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.Objects;

public class Vector extends Position {

	public Vector(double x, double y) {
		super(x, y);
	}

	public Vector(Vector v) {
		super(v);
	}

	public double length() {
		throw new NotImplementedException();
	}

	public Vector product(double d){
		throw new NotImplementedException();
	}

	public Vector dotProduct(Vector vector){
		throw new NotImplementedException();
	}

	public Vector crossProduct(Vector vector){
		throw new NotImplementedException();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Vector){
			if (this.getX() == ((Vector) o).getX() &&
					this.getY() == ((Vector) o).getY())
			{
				return true;
			}
		}
		return false;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Vector(this);
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y, "Vector");
	}

	// todo tostring
}

import java.util.Objects;

public class Ball {
	private final Position position;
	private final Vector vector;

	public Ball(Position p, Vector v) {
		this.vector = v;
		this.position = p;
	}

	public Ball(Ball b){
		this.vector = b.getVector();
		this.position = b.getPosition();
	}

	public Ball withVector(Vector v){
		return new Ball(this.position, v);
	}

	public Ball withPosition(Position p){
		return new Ball(p, this.vector);
	}

	public Position getPosition() {
		return position;
	}

	public Vector getVector() {
		return vector;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.position, this.vector, "Ball");
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Ball) {
			if (this.getPosition().equals(((Ball) o).getPosition()) &&
			    this.getVector().equals(((Ball) o).getVector()))
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

package fr.projet.java.towerdefense;

/**
 * @author Romain Une position sur la carte.
 */
public class Position {

	private final int x;

	private final int y;

	/**
	 * Une position avec des coordonnees.
	 * 
	 * @param x
	 *            La coordonne X
	 * @param y
	 *            La coordonne Y
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * @return La coordonnees X
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * @return La coordonnees Y
	 */
	public int getY() {
		return this.y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Position))
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
}

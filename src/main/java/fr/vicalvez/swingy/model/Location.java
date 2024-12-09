package fr.vicalvez.swingy.model;

public class Location {

	private int x;
	private int y;

	public Location(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void set(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void addX(int x)
	{
		this.x += x;
	}

	public void addY(int y)
	{
		this.y += y;
	}

	public void removeX(int x)
	{
		this.x -= x;
	}

	public void removeY(int y)
	{
		this.y -= y;
	}

	public boolean equals(int x, int y)
	{
		return this.x == x && this.y == y;
	}

	public boolean equals(Location loc)
	{
		return this.x == loc.getX() && this.y == loc.getY();
	}

}

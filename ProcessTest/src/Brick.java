
class Brick {
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private int value;
	private boolean active;

	public Brick() {
		x1 = 0;
		y1 = 0;
		x2 = 0;
		y2 = 0;
		value = 0;
		active = false;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public void setBrickParm(int x1, int y1, int x2, int y2, int value, boolean active) {
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.value = value;
		this.active = active;
	}

	public Brick getBrickParm(int i) {
		return  Brick.this;
	}

	int getValue() {
		return value;
	}

	boolean getActive() {
		return active;
	}

}

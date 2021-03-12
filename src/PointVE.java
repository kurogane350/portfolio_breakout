

import java.awt.Point;

public class PointVE extends Point {

	private int up = 0;
	private int down = 0;
	private int right = 0;
	private int left = 0;

	private boolean isContain = false;

	public PointVE() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public PointVE(Point p) {
		super(p);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public PointVE(int x, int y) {
		super(x, y);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public void setUp(int up) {
		this.up = up;
	}
	public void setDown(int down) {
		this.down = down;
	}
	public void setRight(int right) {
		this.right = right;
	}
	public void setLeft(int left) {
		this.left = left;
	}

	public int getUp() {
		return up;
	}
	public int getDown() {
		return down;
	}
	public int getRight() {
		return right;
	}
	public int getLeft() {
		return left;
	}

	public void contain() {
		isContain = true;
	}

	public boolean isContain() {
		return isContain;
	}
}


import java.awt.Color;
import java.awt.Graphics;

public class Ball {
	//サイズ
	private static final int SIZE = 12;

	//位置（ボールを囲む矩形の左上隅）
	private int x, y;
	//半径
	private int r = SIZE / 2;
	//速度
	private int vx, vy;
	//ベクトル
	public static final int DR = 0;
	public static final int DL = 1;
	public static final int UR = 2;
	public static final int UL = 3;


	//当たり判定用の座標１２個












	public Ball() {

		//位置を初期化
		x = 60;
		y = 250;

		//速度を初期化
		vx = 3;
		vy = 4;
	}

	/**
	 * ボールの描画
	 *
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillOval(x, y, SIZE, SIZE);
	}


	/**
	 * ボールの移動
	 */
	public void move() {
		x += vx;
		y += vy;

		//左右の壁にぶつかった場合にバウンド
		if (x < GamePanel.LEFTWALL || x > GamePanel.RIGHTWALL - SIZE) {
			boundX();
		}
		//上下の壁にぶつかった場合にバウンド
		if (y < GamePanel.UPWALL ) {
			boundY();
		}
	}

	/**
	 * X方向のバウンド
	 */
	public void boundX() {
		vx = -vx;
		System.out.println("X");
	}

	/**
	 * Y方向のバウンド
	 */
	public void boundY() {
		vy = -vy;
		System.out.println("Y");
	}

	/**
	 * 斜めバウンド
	 */
	public void boundXY() {

		vx = -vx;
		vy = -vy;
		System.out.println("XY");
	}

	public void setSpeed(int vx, int vy) {
		this.vx = vx;
		this.vy = vy;
	}

	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSize() {
		return SIZE;
	}

	public PointVE getCenter() {
		PointVE centerPos = new PointVE(x + SIZE / 2, y + SIZE / 2);
		return centerPos;
	}


	public PointVE getPoint0() {
		PointVE point0 = new PointVE(x + r, y + r / 6);
		point0.setUp(3);
		return point0;
	}
	public PointVE getPoint1() {
		PointVE point1 = new PointVE(x + r + r / 2, y + r / 6);
		point1.setUp(2);
		point1.setRight(1);
		return point1;
	}
	public PointVE getPoint2() {
		PointVE point2 = new PointVE(x + SIZE - r / 6, y + r / 2);
		point2.setUp(1);
		point2.setRight(2);
		return point2;
	}
	public PointVE getPoint3() {
		PointVE point3 = new PointVE(x + SIZE, y + r);
		point3.setRight(3);
		return point3;
	}
	public PointVE getPoint4() {
		PointVE point4 = new PointVE(x + SIZE - r / 6, y + r + r / 2);
		point4.setRight(2);
		point4.setDown(1);
		return point4;
	}
	public PointVE getPoint5() {
		PointVE point5 = new PointVE(x + r + r / 2, y + SIZE - r / 6);
		point5.setRight(1);
		point5.setDown(2);
		return point5;
	}
	public PointVE getPoint6() {
		PointVE point6 = new PointVE(x + r, y + SIZE);
		point6.setDown(3);
		return point6;
	}
	public PointVE getPoint7() {
		PointVE point7 = new PointVE(x + r / 2, y + SIZE - r / 6);
		point7.setDown(2);
		point7.setLeft(1);
		return point7;
	}
	public PointVE getPoint8() {
		PointVE point8 = new PointVE(x + r / 6, y + r + r / 2);
		point8.setDown(1);
		point8.setLeft(2);
		return point8;
	}
	public PointVE getPoint9() {
		PointVE point9 = new PointVE(x, y + r);
		point9.setLeft(3);
		return point9;
	}
	public PointVE getPoint10() {
		PointVE point10 = new PointVE(x + r / 6, y + r / 2);
		point10.setLeft(2);
		point10.setUp(1);
		return point10;
	}
	public PointVE getPoint11() {
		PointVE point11 = new PointVE(x + r / 2, y + r / 6);
		point11.setLeft(1);
		point11.setUp(2);
		return point11;
	}





	public PointVE[] getPointList(){
		PointVE[] pointList = { getPoint0(), getPoint1(), getPoint2(),
				getPoint3(),getPoint4(), getPoint5(), getPoint6(), getPoint7(),
				getPoint8(), getPoint9(), getPoint10(), getPoint11() };
		return pointList;
	}

	public int getVector() {
		if (vx > 0 && vy > 0) {
			return DR;
		} else if (vx < 0 && vy > 0) {
			return DL;
		} else if (vx > 0 && vy < 0) {
			return UR;
		} else {
			return UL;
		}
	}
}


















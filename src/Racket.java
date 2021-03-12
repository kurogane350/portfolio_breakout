
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
/**
 * ボールを打つラケットクラス
 * @author Owner
 *
 */
public class Racket {
	//ラケットのサイズ
	public int width = 84;
	public int height = 10;

    // ボールの当たり位置
    public static final int NO_COLLISION = 0;  // 未衝突
    public static final int LEFT3 = 1;
    public static final int LEFT2 = 2;
    public static final int LEFT1 = 3;
    public static final int RIGHT1 = 4;
    public static final int RIGHT2 = 5;
    public static final int RIGHT3 = 6;

	//ラケットの中心位置
	private int centerPos;



	public Racket () {
		// ラケットの位置を画面の真ん中で初期化
		centerPos = GamePanel.LEFTWALL + ( GamePanel.RIGHTWALL - GamePanel.LEFTWALL )/ 2;
	}

	/**
	 * ラケットの描画
	 *
	 * @param g
	 */
	public void draw(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(centerPos - width / 4, GamePanel.HEIGHT - height * 4, width / 4, height);
		g.fillRect(centerPos, GamePanel.HEIGHT - height * 4, width / 4, height);
		g.setColor(Color.RED);
		g.fillRect(centerPos - (width / 12) * 5, GamePanel.HEIGHT - height * 4, width / 6, height);
		g.fillRect(centerPos + width / 4, GamePanel.HEIGHT - height * 4, width / 6, height);
		g.setColor(Color.CYAN);
		g.fillRect(centerPos - width / 2, GamePanel.HEIGHT - height * 4, width / 12, height);
		g.fillRect(centerPos + (width / 12) * 5, GamePanel.HEIGHT - height * 4, width / 12, height);
	}

	/**
	 * ラケットの移動
	 */
	public void move(int pos) {
        switch (pos) {
        case -1 :
            centerPos -= width / 4;
            break;
        case 1 :
            centerPos += width / 4;
            break;
    }


		//ラケットが画面の端から飛び出ないようにする
		if (centerPos < GamePanel.LEFTWALL + width / 2) {
			centerPos = GamePanel.LEFTWALL + width / 2;
		}else if (centerPos > GamePanel.RIGHTWALL - width / 2) {
			centerPos = GamePanel.RIGHTWALL - width / 2;
		}
	}

	/**
	 * ボールが当たった位置を返す
	 *
	 * @param ball ボール
	 * @return ボールに当たったらtrue
	 *
	 */
	public int collideWith(Ball ball) {
		// ラケットの矩形
		Rectangle racketRectLeft3 = new Rectangle(
				centerPos - width / 2, GamePanel.HEIGHT - height * 4, width / 12, height);
		Rectangle racketRectLeft2 = new Rectangle(
				centerPos - (width / 12) * 5, GamePanel.HEIGHT - height * 4, width / 6, height);
		Rectangle racketRectLeft1 = new Rectangle(
				centerPos - width / 4, GamePanel.HEIGHT - height * 4, width / 4, height);
		Rectangle racketRectRight1 = new Rectangle(
				centerPos, GamePanel.HEIGHT - height * 4, width / 4, height);
		Rectangle racketRectRight2 = new Rectangle(
				centerPos + width / 4, GamePanel.HEIGHT - height * 4, width / 6, height);
		Rectangle racketRectRight3 = new Rectangle(
				centerPos + (width / 12) * 5, GamePanel.HEIGHT - height * 4, width / 12, height);

		// ボールの矩形
		Rectangle ballRect = new Rectangle(
				ball.getX(), ball.getY(),
				ball.getSize(), ball.getSize());

    	int ballX = ball.getX();
    	int ballY = ball.getY();
    	int ballSize = ball.getSize();

		// ラケットとボールの矩形領域が重なっていたら当たっている
    	if (racketRectLeft2.intersects(ballRect)) {
            return LEFT2;
        } else if (racketRectRight2.intersects(ballRect)) {
            return RIGHT2;
        } else if (racketRectLeft1.intersects(ballRect)) {
        	if (racketRectRight1.contains(ballX + ballSize / 2, ballY + ballSize)) {
        		return RIGHT1;
        } else {
        	return LEFT1;
        }
        } else if (racketRectRight1.intersects(ballRect)) {
        	if (racketRectLeft1.contains(ballX + ballSize / 2, ballY + ballSize)) {
        		return LEFT1;
        } else {
        	return RIGHT1;
        }
        }
        else if (racketRectLeft3.intersects(ballRect)) {
            return LEFT3;
        } else if (racketRectRight3.intersects(ballRect)) {
            return RIGHT3;
        }

		return NO_COLLISION;
	}


}

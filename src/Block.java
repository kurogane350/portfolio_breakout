
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Block {
	public static final int WIDTH = 39;
	public static final int HEIGHT = 15;

	//ボールの当たり位置
	public static final int NO_COLLISION = 0;	// 未衝突
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;
    public static final int UP = 4;
    public static final int DOWN_LEFT = 5;
    public static final int DOWN_RIGHT = 6;
    public static final int UP_LEFT = 7;
    public static final int UP_RIGHT = 8;

    // 位置（左上隅の座標）
    protected int x, y;

    Rectangle blockRect = new Rectangle();

    Point centerPos = new Point();



    protected double collideDist;

    // ボールが当たって消されたか
    protected boolean isDeleted;

    // 自身の外側に隣接するブロックがある場合に角当たり判定（斜め反射）を無効にする
//    protected boolean cornerUR;	//右上
//    protected boolean cornerDR;	//右下
//    protected boolean cornerDL;	//左下


    // ブロックの耐久度
	protected int durability;

	// ブロックの得点
	protected int point;

    public Block(int x, int y) {
    	this.x = x;
    	this.y = y;
    	isDeleted = false;
//    	cornerUR = false;
//    	cornerDR = false;
//    	cornerDL = false;
        blockRect = new Rectangle(x, y, WIDTH, HEIGHT);
        centerPos = new Point( x + WIDTH / 2, y + HEIGHT / 2);
    }

    /**
     * ブロックを描画
     *
     * @param g
     */
    public void draw(Graphics g) {
    	g.setColor(Color.CYAN);
    	g.fillRect(x, y, WIDTH, HEIGHT);

    	// 枠線を描画
    	g.setColor(Color.BLACK);
    	g.drawRect(x, y, WIDTH, HEIGHT);
    }

    /**
     * ボールと衝突したか
     *
     * @param ball ボール
     * @return 衝突位置
     */
    public boolean collideWhith(Ball ball) {


//
//    	int ballX = ball.getX();
//    	int ballY = ball.getY();
//    	int ballSize = ball.getSize();
//
//
//    	if (blockRect.contains(ballX, ballY)
//    			&& blockRect.contains(ballX + ballSize, ballY)) {
//    		// ブロックの下から衝突＝ボールの左上・右下の点がブロック内
//    		return DOWN;
//    	} else if (blockRect.contains(ballX + ballSize, ballY)
//    			&& blockRect.contains(ballX + ballSize, ballY + ballSize)) {
//    		// ブロックの左から衝突＝ボールの右上・右下の点がブロック内
//    		return LEFT;
//    	} else if (blockRect.contains(ballX, ballY)
//    			&& blockRect.contains(ballX, ballY + ballSize)) {
//    		// ブロックの右から衝突＝ボールの左上・左下の点がブロック内
//    		return RIGHT ;
//    	} else if (blockRect.contains(ballX, ballY + ballSize)
//    			&& blockRect.contains(ballX + ballSize, ballY + ballSize)) {
//    		// ブロックの上から衝突＝ボールの左下・右下の点がブロック内
//    		return UP;
//    	} else if (blockRect.contains(ballX + ballSize, ballY + ballSize)) {
//    		//ブロックの左下から衝突＝ボールの右上の点がブロック内
//    		return DOWN_LEFT;
//    	} else if (blockRect.contains(ballX, ballY)) {
//    		//ブロックの右下から衝突＝ボールの左上の点がブロック内
//    		return DOWN_RIGHT;
//    	} else if (blockRect.contains(ballX + ballSize, ballY + ballSize)) {
//    		//ブロックの左上から衝突＝ボールの右下の点がブロック内
//    		return UP_LEFT;
//    	} else if (blockRect.contains(ballX, ballY + ballSize)) {
//    		//ブロックの右上から衝突＝ボールの左下の点がブロック内
//    		return UP_RIGHT;
//    	}
//
//    	return NO_COLLISION;


    	PointVE[] pointList = ball.getPointList();
    	for (int i = 0; i < pointList.length; i++) {
    		if (blockRect.contains(pointList[i])) {
    			return true;
    		}
    	}
		return false;











//    	if (blockRect.contains(p12)) {
//    		return DOWN;
//    	} else if (blockRect.contains(p6)) {
//    		return UP;
//    	} else if (blockRect.contains(p9)) {
//    		return RIGHT;
//    	} else if (blockRect.contains(p3)) {
//    		return LEFT;
//    	} else if (blockRect.contains(p1)) {
//    		if (blockRect.contains(p2)) {
//    			return DOWN_LEFT;
//    		} else {
//    			return DOWN;
//    		}
//    	} else if (blockRect.contains(p2)) {
//    		return LEFT;
//    	} else if (blockRect.contains(p4)) {
//    		if (blockRect.contains(p5)) {
//    			return UP_LEFT;
//    		} else {
//    			return LEFT;
//    		}
//    	} else if (blockRect.contains(p5)) {
//    		return UP;
//    	} else if (blockRect.contains(p7)) {
//    		if (blockRect.contains(p8)) {
//    			return UP_RIGHT;
//    		} else {
//    			return UP;
//    		}
//    	} else if (blockRect.contains(p8)) {
//    		return RIGHT;
//    	} else if (blockRect.contains(p10)) {
//    		if (blockRect.contains(p11)) {
//    			return DOWN_RIGHT;
//    		} else {
//    			return RIGHT;
//    		}
//    	} else if (blockRect.contains(p11)) {
//    		return DOWN;
//    	}
//    	return NO_COLLISION;

//    	if (blockRect.contains(p11) &&
//    			blockRect.contains(p12) && blockRect.contains(p1)) {
//    		return DOWN;
//    	}else if (blockRect.contains(p5) &&
//    			blockRect.contains(p6) && blockRect.contains(p7)) {
//    		return UP;
//    	}else if (blockRect.contains(p2) &&
//    			blockRect.contains(p3) && blockRect.contains(p4)) {
//    		return LEFT;
//    	}else if (blockRect.contains(p8) &&
//    			blockRect.contains(p9) && blockRect.contains(p10)) {
//    		return RIGHT;
//    	}else if (blockRect.contains(p12) &&
//    			blockRect.contains(p1) && blockRect.contains(p2)) {
//    		return DOWN;
//    	}else if (blockRect.contains(p1) &&
//    			blockRect.contains(p2) && blockRect.contains(p3)) {
//    		return LEFT;
//    	}else if (blockRect.contains(p3) &&
//    			blockRect.contains(p4) && blockRect.contains(p5)) {
//    		return LEFT;
//    	}else if (blockRect.contains(p4) &&
//    			blockRect.contains(p5) && blockRect.contains(p6)) {
//    		return UP;
//    	}else if (blockRect.contains(p6) &&
//    			blockRect.contains(p7) && blockRect.contains(p8)) {
//    		return UP;
//    	}else if (blockRect.contains(p7) &&
//    			blockRect.contains(p8) && blockRect.contains(p9)) {
//    		return RIGHT;
//    	}else if (blockRect.contains(p9) &&
//    			blockRect.contains(p10) && blockRect.contains(p11)) {
//    		return RIGHT;
//    	}else if (blockRect.contains(p10) &&
//    			blockRect.contains(p11) && blockRect.contains(p12)) {
//    		return DOWN;
//    	}else if (blockRect.contains(p1) && blockRect.contains(p2)) {
//    		return DOWN_LEFT;
//    	}else if (blockRect.contains(p4) && blockRect.contains(p5)) {
//    		return UP_LEFT;
//    	}else if (blockRect.contains(p7) && blockRect.contains(p8)) {
//    		return UP_RIGHT;
//    	}else if (blockRect.contains(p10) && blockRect.contains(p11)) {
//    		return DOWN_RIGHT;
//    	}
//    	return NO_COLLISION;

    }

    public PointVE[] getCollidePL(Ball ball) {
    	PointVE[] collidePL = ball.getPointList();
    	for (int i = 0; i < collidePL.length; i++) {
    		if (blockRect.contains(collidePL[i])) {
    			collidePL[i].contain();
    		}
    	}
    	return collidePL;
    }

    /**
     * ブロックにダメージ
     */
    public void damage(){
    	durability --;
    }

    /**
     * ブロックを消去
     */
    public void delete() {

    	isDeleted = true;
    }

    /**
     * ブロックの角当たり判定を消す
//     */
//    public void ignoreCornerR() {
//    	cornerUR = true;
//    	cornerDR = true;
//    }
//
//    public void ignoreCornerD() {
//    	cornerDR = true;
//    	cornerDL = true;
//    }


    public int getX() {
    	return x;
    }

    public int getY() {
    	return y;
    }
    public Rectangle getRect() {
    	return blockRect;
    }

    public boolean isDeleted() {
    	return isDeleted;
    }

    public void setDistance(Ball ball) {
    	Point ballPos = ball.getCenter();
    	collideDist = centerPos.distance(ballPos);
    }









}


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

/**
 * ブロック崩しのゲーム画面パネル
 * @author Owner
 *
 */
public abstract class GamePanel extends JPanel implements Runnable, MouseWheelListener, KeyListener {
	//パネルサイズ
	public static final int WIDTH = 720;
	public static final int HEIGHT = 540;

	//枠の位置
	public static final int LEFTWALL= 40;
	public static final int RIGHTWALL = 480;
	public static final int UPWALL = 60;
	public static final int WALLSIZE = 16;

    // ブロックの行数
    private static final int NUM_BLOCK_ROW = 24;
    // ブロックの列数
    private static final int NUM_BLOCK_COL = 11;

    //ブロックの色
    public static final int WH = 1;
    public static final int OR = 2;
    public static final int CY = 3;
    public static final int GR = 4;
    public static final int RE = 5;
    public static final int BL = 6;
    public static final int PI = 7;
    public static final int YE = 8;
    public static final int SI = 9;
    public static final int GO = 10;


	protected Racket racket;	// ラケット
	protected Ball ball;	// ボール
	protected int[][] blockmap; //ブロックマップ
	protected Block[][] block; // ブロック
	protected int NumBlocks; // ブロック数
	protected int brokenCount; //壊したブロック数
    protected boolean clearflag = false;
    protected boolean run = true;

    // スコアパネルへの参照
    protected MainPanel mainPanel;


    // ダブルバッファリング（db）用
    protected Graphics dbg;
    protected Image dbImage = null;

	protected Thread gameLoop; // ゲームループ

	public GamePanel(MainPanel panel) {
		// パネルの推奨サイズを設定、pack()するときに必要
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		mainPanel = panel;
		racket = new Racket();
		ball = new Ball();
		blockmap = new int[NUM_BLOCK_ROW][NUM_BLOCK_COL];
		block = new Block[NUM_BLOCK_ROW][NUM_BLOCK_COL];



		//マウスホイールの有効化
		addMouseWheelListener(this);

		// パネルがキーボードを受け付けるようにする
        this.setFocusable(true);
        addKeyListener(this);




        gameLoop = new Thread(this);
        gameLoop.start();
	}

	/**
	 * ブロックマップをもとにブロックを配置するメソッド
	 */
	public void makeBlock(int blockmap[][]) {
        // ブロックを並べる
        for (int i = 0; i < NUM_BLOCK_ROW; i++) {
            for (int j = 0; j < NUM_BLOCK_COL; j++) {
            	if (blockmap[i][j] != 0) {
            		int x = ( j * Block.WIDTH ) + GamePanel.LEFTWALL + (j * 1);
                    int y = (i * Block.HEIGHT ) + GamePanel.UPWALL + (i * 1);
                    switch (blockmap[i][j]) {
                    	case WH :
                    		block[i][j] = new BlockWhite(x, y);
                    		break;
                    	case OR :
                    		block[i][j] = new BlockOrange(x, y);
                    		break;
                    	case CY :
                    		block[i][j] = new BlockCyan(x, y);
                    		break;
                    	case GR :
                    		block[i][j] = new BlockGreen(x, y);
                    		break;
                    	case RE :
                    		block[i][j] = new BlockRed(x, y);
                    		break;
                    	case BL :
                    		block[i][j] = new BlockBlue(x, y);
                    		break;
                    	case PI :
                    		block[i][j] = new BlockPink(x, y);
                    		break;
                    	case YE :
                    		block[i][j] = new BlockYellow(x, y);
                    		break;
                    	case SI :
                    		block[i][j] = new BlockSilver(x, y);
                    		break;
                    	case GO :
                    		block[i][j] = new BlockGold(x, y);
                    		break;
                    }
            	}
            }
        }
	}

	/**
	 * 壊せるブロックの数を求めるメソッド
	 */
	public int getNumBlocks() {
		ArrayList<Block> NumBlocks= new ArrayList<Block>();
		for (int i = 0; i < NUM_BLOCK_ROW; i++) {
            for (int j = 0; j < NUM_BLOCK_COL; j++) {
            	if (block[i][j] != null && block[i][j].durability >= 0 ) {
            		NumBlocks.add(block[i][j]);
            	}
            }
		}
		return NumBlocks.size();
	}

	/**
	 * ゲームループ
	 */
	@Override
	public void run() {
		 while (run) {
			 gameUpdate(); // ゲーム状態を更新
	         gameRender(); // バッファにレンダリング（ダブルバッファリング）
	         paintScreen(); // バッファを画面に描画

			 if (clearflag == true) {

				System.out.println("changeggg");
			 	change();
			 }

			 try {
				 Thread.sleep(16);
			 } catch (InterruptedException e) {
				 e.printStackTrace();
			 }
		 }
	}

	/**
     * ゲーム状態を更新
     */
    protected void gameUpdate() {
    	ball.move();

		//ラケットとボールの衝突処理
		int collidePos = racket.collideWith(ball);
		if (collidePos != Racket.NO_COLLISION) {	//ラケットに当たっていたら
			//	ボールの当たった位置に応じてボールの速度を変える
			switch (collidePos) {
			 	case Racket.LEFT3 :
			 		ball.setSpeed(-5, -2);
			 		break;
			 	case Racket.LEFT2 :
			 		ball.setSpeed(-3, -4);
			 		break;
			 	case Racket.LEFT1 :
			 		ball.setSpeed(-2, -5);
			 		break;
			 	case Racket.RIGHT1 :
			 		ball.setSpeed(2, -5);
			 		break;
			 	case Racket.RIGHT2 :
			 		ball.setSpeed(3, -4);
			 		break;
			 	case Racket.RIGHT3 :
			 		ball.setSpeed(5, -2);
			 		break;
			 }

		 }



		//衝突しているブロックを格納する配列
		ArrayList<Block> collideBlock = new ArrayList<Block>();

		// ブロックとボールの衝突判定
		for (int i = 0; i < NUM_BLOCK_ROW; i++) {
			for (int j = 0; j < NUM_BLOCK_COL; j++) {
				// すでに消えているブロックは無視
				if (block[i][j] == null || block[i][j].isDeleted())
					continue;
				// ブロックがボールに当たっていたらcollideBlockに追加
				if (block[i][j].collideWhith(ball)) {
					block[i][j].setDistance(ball);	//ブロックとボールの距離を設定
					collideBlock.add(block[i][j]);

				}
			}
		}

		if (!collideBlock.isEmpty()) {
			 // ボールとの距離が近い順にソート
			 Collections.sort(
					 collideBlock, new Comparator<Block>() {	//内部クラスでcollideDist順にソートの定義
						 public int compare(Block b1, Block b2) {
							 return b1.collideDist < b2.collideDist ? - 1 : 1;
						 }
					 });


			 // ボールの当たり判定の座標を取得
			 PointVE[] collidePL = ball.getPointList();
			 // 当たり判定の属性値 上下左右
			 int up = 0;
			 int down = 0;
			 int right = 0;
			 int left = 0;

			 // ブロックに含まれる当たり判定座標の属性値を集計
			 for (int i = 0; i < collideBlock.size(); i++) {
				 for (int j = 0; j < collidePL.length; j++)
				 if (collideBlock.get(i).getRect().contains(collidePL[j])) {
					 up += collidePL[j].getUp();
					 down += collidePL[j].getDown();
					 right += collidePL[j].getRight();
					 left += collidePL[j].getLeft();
				 }
			 }
			 int[] vector = new int[4];
			 vector[0] = up;
			 vector[1] = down;
			 vector[2] = right;
			 vector[3] = left;

			 Arrays.sort(vector);
			 if (vector[0] == vector[1]) {
				 ball.boundXY();
			 } else {
				 switch (ball.getVector()) {
				 	case Ball.DR :
				 		if (Arrays.stream(vector).max().getAsInt() == up)
				 			ball.boundX();
				 		else if (Arrays.stream(vector).max().getAsInt() == down)
				 			ball.boundY();
				 		else if (Arrays.stream(vector).max().getAsInt() == right)
				 			ball.boundX();
				 		else if (Arrays.stream(vector).max().getAsInt() == left)
				 			ball.boundY();
			 			break;

				 	case Ball.DL :
				 		if (Arrays.stream(vector).max().getAsInt() == up)
				 			ball.boundX();
				 		else if (Arrays.stream(vector).max().getAsInt() == down)
				 			ball.boundY();
				 		else if (Arrays.stream(vector).max().getAsInt() == right)
				 			ball.boundY();
				 		else if (Arrays.stream(vector).max().getAsInt() == left)
				 			ball.boundX();
			 			break;

				 	case Ball.UR :
				 		if (Arrays.stream(vector).max().getAsInt() == up)
				 			ball.boundY();
				 		else if (Arrays.stream(vector).max().getAsInt() == down)
				 			ball.boundX();
				 		else if (Arrays.stream(vector).max().getAsInt() == right)
				 			ball.boundY();
				 		else if (Arrays.stream(vector).max().getAsInt() == left)
				 			ball.boundX();
			 			break;

				 	case Ball.UL :
				 		if (Arrays.stream(vector).max().getAsInt() == up)
				 			ball.boundY();
				 		else if (Arrays.stream(vector).max().getAsInt() == down)
				 			ball.boundX();
				 		else if (Arrays.stream(vector).max().getAsInt() == right)
				 			ball.boundY();
				 		else if (Arrays.stream(vector).max().getAsInt() == left)
				 			ball.boundX();
			 			break;
				 }

			 }
			 collideBlock.get(0).damage();
			 if (collideBlock.get(0).durability == 0) {
				 collideBlock.get(0).delete();
				 upScore(collideBlock.get(0).point);
				 brokenCount ++;
				 if (brokenCount == NumBlocks) {
					 clearflag = true;
				 }
		}



		 }
    }

    /**
     * バッファにレンダリング（ダブルバッファリング）
     */
    protected void gameRender() {
        // 初回の呼び出し時にダブルバッファリング用オブジェクトを作成
        if (dbImage == null) {
            // バッファイメージ
            dbImage = createImage(WIDTH, HEIGHT);
            if (dbImage == null) {
                System.out.println("dbImage is null");
                return;
            } else {
                // バッファイメージの描画オブジェクト
                dbg = dbImage.getGraphics();
            }
        }

        // バッファをクリアする
		// 背景
		dbg.setColor(Color.BLACK);
		dbg.fillRect(0, 0, WIDTH, HEIGHT);

		//枠の描画
		dbg.setColor(Color.WHITE);
		dbg.fillRect(LEFTWALL - WALLSIZE, UPWALL - WALLSIZE, WALLSIZE, HEIGHT - UPWALL + WALLSIZE);
		dbg.fillRect(RIGHTWALL, UPWALL - WALLSIZE, WALLSIZE, HEIGHT - UPWALL + WALLSIZE);
		dbg.fillRect(LEFTWALL, UPWALL - WALLSIZE, RIGHTWALL - LEFTWALL, WALLSIZE);




        // バッファへ描画する
		racket.draw(dbg);	// ラケット
		ball.draw(dbg);	// ボール

		// ブロック
		for (int i = 0; i < NUM_BLOCK_ROW; i++) {
			 for (int j = 0; j < NUM_BLOCK_COL; j++) {
				if (block[i][j] != null && !block[i][j].isDeleted()) {
					block[i][j].draw(dbg);
				}
			 }
		}

        // スコアを描画
        dbg.setColor(Color.YELLOW);
        DecimalFormat formatter = new DecimalFormat("000000");
        // フォントを作成
        Font font = new Font("Ariel", Font.BOLD, 24);
        dbg.setFont(font);

        dbg.drawString(formatter.format(mainPanel.score), 520, 100);
    }

    /**
     * バッファを画面に描画
     */
    protected void paintScreen() {
        try {
            Graphics g = getGraphics(); // グラフィックオブジェクトを取得
            if ((g != null) && (dbImage != null)) {
                g.drawImage(dbImage, 0, 0, null); // バッファイメージを画面に描画
            }
            Toolkit.getDefaultToolkit().sync();
            if (g != null) {
                g.dispose(); // グラフィックオブジェクトを破棄
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    /**
     * スコアをプラスする
     *
     * @param d プラス分
     */
    public void upScore(int d) {
        mainPanel.score += d;
    }


	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		racket.move(e.getWheelRotation());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// 使わない
	}

	@Override
	public void keyPressed(KeyEvent e) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// 使わない
	}

	//画面切り替え用メソッド
	public void change() {
		try {
 			Thread.sleep(2000);
 		} catch (InterruptedException e) {
 			e.printStackTrace();
 		}
		mainPanel.round ++;
		mainPanel.state = 100;
		run = false;
	}



}














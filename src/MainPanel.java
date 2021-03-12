

import javax.swing.JPanel;

public class MainPanel extends JPanel implements Runnable{

	public int scene; // ゲームの状態
	public boolean in_game = true;   // ゲーム実行中はtrue
	public int state = 0;   // ゲーム状態（0:表紙，1:ゲーム，2:クリア，3:オーバー，4:終了）
	public int old_state = 0;   // 直前のゲーム状態
	public int round = 0;   // ラウンド数
	public int score = 0;
	public StartPanel startPanel;
	public GamePanel gamePanel;
	public InterludePanel interludePanel;
//	public GameClearPanel gameClearPanel;
//	public GameOverPanel gameOverPanel;
	Thread td;

	public MainPanel(){
		startPanel = new StartPanel(this);   // スタート（タイトル）
		add(startPanel);
					// スレッドの生成
		td = new Thread(this);
		td.start();

	}

	@Override
	public void run() {
		while (in_game) {
			try {
				Thread.sleep(16);   // 20 ms 毎の実施
			}
			catch (InterruptedException e) {}
			if (state != old_state) {
							// 前のパネルの削除
				if (old_state == 0)
					remove(startPanel);
				else if (old_state == 1)
					remove(gamePanel);
				else if (old_state == 2)
					remove(gamePanel);
				else if (old_state == 100)
					remove(interludePanel);
//				else
//					remove(gop);
							// 新しいパネルの追加
				if (state == 4)   // ゲーム終了
					in_game = false;
				else {
					if (state == 0) {   // StartPanel
						startPanel = new StartPanel(this);
						add(startPanel);
					}
					else if (state == 1) {   // GamePanel
						gamePanel = new Round1(this);
						add(gamePanel);
					}
					else if (state == 2) {   // GameClearPanel
						gamePanel = new Round2(this);
						System.out.println("round2");
						add(gamePanel);
					}
					else if (state == 100) {   // GameClearPanel
						interludePanel = new InterludePanel(this);
						add(interludePanel);
					}
//					else {   // GameOverPanel
//						gop = new GameOverPanel(ac, cb, size, this);
//						add(gop);
//					}
					validate();
					old_state = state;
				}
			}

		}


	}


}

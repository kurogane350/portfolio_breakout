

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class StartPanel extends JPanel implements KeyListener{
	//パネルサイズ
	public static final int WIDTH = 720;
	public static final int HEIGHT = 540;

	MainPanel mainPanel; //メインパネルの参照


	public StartPanel(MainPanel panel) {
		mainPanel = panel;

		// パネルの推奨サイズを設定、pack()するときに必要
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		// パネルがキーボードを受け付けるようにする
        this.setFocusable(true);
        addKeyListener(this);
	}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 背景
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

		FontMetrics fm;
		Font f;
		String str;
		int w, h;


		g.setColor(Color.CYAN);
		f    = new Font("Serif", Font.BOLD, 40);
		fm   = g.getFontMetrics(f);
		str = "ARKANOPPOID";
		w    = fm.stringWidth(str);
		h    = fm.getHeight() + 60;
		g.setFont(f);
		g.drawString(str, WIDTH / 2 - w / 2, h);



		f    = new Font("Serif", Font.PLAIN, 20);
		fm   = g.getFontMetrics(f);
		str = "ゲーム開始：「 s 」キー";
		w    = fm.stringWidth(str);
		h    = HEIGHT - fm.getHeight() - 30;
		g.setFont(f);
		g.drawString(str, WIDTH / 2 - w / 2, h);

		f    = new Font("Serif", Font.PLAIN, 20);
		fm   = g.getFontMetrics(f);
		str = "ランキング：「 r 」キー";
		w    = fm.stringWidth(str);
		h    = h + fm.getHeight() + 5;
		g.setFont(f);
		g.drawString(str, WIDTH / 2 - w / 2, h);



		// この Component が入力フォーカスを取得することを要求
		requestFocusInWindow();

    }


	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == 83) { //「s」キー
        	mainPanel.state = 100;
        	System.out.println(1);

        } else if (key == 82) {  //「r」キー

        }

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.text.DecimalFormat;

import javax.swing.JPanel;

public class InterludePanel extends JPanel implements Runnable{
	//パネルサイズ
	public static final int WIDTH = 720;
	public static final int HEIGHT = 540;

	MainPanel mainPanel; //メインパネルの参照
	private Thread gameLoop; // ゲームループ

	int count = 0;
	private boolean run = true;


	public InterludePanel(MainPanel panel) {
		mainPanel = panel;

		// パネルの推奨サイズを設定、pack()するときに必要
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

        gameLoop = new Thread(this);
        gameLoop.start();

	}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 背景
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

		FontMetrics fm;
		Font f;
		String str;
		int w, h, r;


		g.setColor(Color.RED);
		f    = new Font("DialogInput", Font.BOLD, 25);
		fm   = g.getFontMetrics(f);
		str = "SCORE";
		w    = fm.stringWidth(str);
		h    = fm.getHeight() + 20;
		g.setFont(f);
		g.drawString(str, WIDTH / 2 - w / 2, h);

		g.setColor(Color.WHITE);
        DecimalFormat formatter = new DecimalFormat("000000");
		f    = new Font("DialogInput", Font.BOLD, 25);
		fm   = g.getFontMetrics(f);
		w    = fm.stringWidth(str);
		h    = h + fm.getHeight() + 5;
		g.setFont(f);
		g.drawString(formatter.format(mainPanel.score), WIDTH / 2 - w / 2, h);

		f    = new Font("DialogInput", Font.BOLD, 30);
		fm   = g.getFontMetrics(f);
		str  = "ROUND ";
		r    = mainPanel.round + 1;
		w    = fm.stringWidth(str);
		h    = h + fm.getHeight() + 60;
		g.setFont(f);
		g.drawString(str + r, WIDTH / 2 - w / 2, h);

    }

	@Override
	public void run() {

        while (run) {
    		try {
    			Thread.sleep(3000);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}

        	mainPanel.state = mainPanel.round + 1;
        	run = false;

        }
	}
}

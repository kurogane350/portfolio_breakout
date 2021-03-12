
import javax.swing.JFrame;

public class Breakout extends JFrame{
	public Breakout() {
		setTitle("ARKANOPPOID");
		setResizable(false);

        //メインパネル
		MainPanel mainPanel = new MainPanel();
		getContentPane().add(mainPanel);

		pack();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

    public static void main(String[] args) {
        new Breakout();
    }

}

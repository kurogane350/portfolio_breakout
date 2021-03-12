
import java.awt.Color;
import java.awt.Graphics;

public class BlockCyan extends Block {

	public BlockCyan(int x, int y) {
		super(x, y);
		durability = 1;
		point = 70;
	}

	@Override
	public void draw(Graphics g) {
    	g.setColor(Color.CYAN);
    	g.fillRect(x, y, WIDTH, HEIGHT);

    	// 枠線を描画
    	g.setColor(Color.BLACK);
    	g.drawRect(x, y, WIDTH, HEIGHT);
	}

}


import java.awt.Color;
import java.awt.Graphics;

public class BlockRed extends Block {

	public BlockRed(int x, int y) {
		super(x, y);
		durability = 1;

	}

	@Override
	public void draw(Graphics g) {
    	g.setColor(Color.RED);
    	g.fillRect(x, y, WIDTH, HEIGHT);

    	// 枠線を描画
    	g.setColor(Color.BLACK);
    	g.drawRect(x, y, WIDTH, HEIGHT);
	}

}

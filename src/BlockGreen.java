
import java.awt.Color;
import java.awt.Graphics;

public class BlockGreen extends Block {

	public BlockGreen(int x, int y) {
		super(x, y);
		durability = 1;
		point = 80;
	}

	@Override
	public void draw(Graphics g) {
    	g.setColor(Color.GREEN);
    	g.fillRect(x, y, WIDTH, HEIGHT);

    	// 枠線を描画
    	g.setColor(Color.BLACK);
    	g.drawRect(x, y, WIDTH, HEIGHT);
	}

}

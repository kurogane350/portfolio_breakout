
import java.awt.Color;
import java.awt.Graphics;

public class BlockPink extends Block {

	public BlockPink(int x, int y) {
		super(x, y);
		durability = 1;
		point = 110;
	}

	@Override
	public void draw(Graphics g) {
    	g.setColor(Color.PINK);
    	g.fillRect(x, y, WIDTH, HEIGHT);

    	// 枠線を描画
    	g.setColor(Color.BLACK);
    	g.drawRect(x, y, WIDTH, HEIGHT);
	}

}

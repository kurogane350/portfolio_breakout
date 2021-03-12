
import java.awt.Color;
import java.awt.Graphics;

public class BlockGold extends Block {

	public BlockGold(int x, int y) {
		super(x, y);
		durability = -1;
	}

	@Override
	public void draw(Graphics g) {
    	g.setColor(new Color(204,153,51));
    	g.fillRect(x, y, WIDTH, HEIGHT);

    	// 枠線を描画
    	g.setColor(Color.BLACK);
    	g.drawRect(x, y, WIDTH, HEIGHT);
	}

}

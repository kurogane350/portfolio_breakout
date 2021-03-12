
import java.awt.Color;
import java.awt.Graphics;

public class BlockOrange extends Block {

	public BlockOrange(int x, int y) {
		super(x, y);
		durability = 1;
		point = 60;
	}

	@Override
	public void draw(Graphics g) {
    	g.setColor(new Color(255,102,51));
    	g.fillRect(x, y, WIDTH, HEIGHT);

    	// 枠線を描画
    	g.setColor(Color.BLACK);
    	g.drawRect(x, y, WIDTH, HEIGHT);
	}

}

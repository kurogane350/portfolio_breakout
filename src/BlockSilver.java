
import java.awt.Color;
import java.awt.Graphics;

public class BlockSilver extends Block {

	public BlockSilver(int x, int y) {
		super(x, y);
		durability = 3;
		point = 500;
	}

	@Override
	public void draw(Graphics g) {
    	g.setColor(Color.GRAY);
    	g.fillRect(x, y, WIDTH, HEIGHT);

    	// 枠線を描画
    	g.setColor(Color.BLACK);
    	g.drawRect(x, y, WIDTH, HEIGHT);
	}

}

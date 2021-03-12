

public class Round2 extends GamePanel {

	public Round2(MainPanel panel) {
		super(panel);

        int[][] blockmap = {
        		{GO,GO,GO,GO,GO,GO,GO,GO,GO,GO,GO},
        		{GO,GO,GO,GO,GO,GO,GO,GO,GO,GO,GO},
        		{GO,0,0,0,0,GO,SI,0,0,0,CY},
        		{0,0,0,0,0,GO,0,0,0,0,0},
        		{0,0,GO,0,0,0,0,0,0,0,0},
        		{0,0,GO,0,0,0,0,0,0,0,0},
        		{0,0,GO,0,0,0,YE,PI,0,0,0},
        		{0,0,GO,0,0,0,GR,BL,0,0,0},
        		{0,0,GO,0,0,0,0,0,0,0,0},
        		{0,0,GO,0,0,0,0,0,0,GO,GO},
        		{0,0,GO,0,0,0,0,0,GO,SI,SI},
        		{0,0,GO,GO,GO,GO,GO,GO,GO,GO,GO},
        		{0,0,0,0,0,0,0,0,0,0,0},
        		{0,0,0,0,0,0,0,0,0,0,0},
        		{0,0,0,0,0,0,0,0,0,0,0},
        		{0,0,0,0,0,0,0,0,0,0,0},
        		{0,0,0,0,0,0,0,0,0,0,0},
        		{0,0,0,0,0,0,0,0,0,0,0},
        		{0,0,0,0,0,0,0,0,0,0,0},
        		{0,0,0,0,0,0,0,0,0,0,0},
        		{0,0,0,0,0,0,0,0,0,0,0},
        		{0,0,0,0,0,0,0,0,0,0,0},
        		{0,0,0,0,0,0,0,0,0,0,0},
        		{0,0,0,0,0,0,0,0,0,0,0}
        };



        brokenCount = 0;
        makeBlock(blockmap);
        NumBlocks = getNumBlocks();
	}

}

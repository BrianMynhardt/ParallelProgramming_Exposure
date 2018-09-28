package ParallelTrees;

import javax.swing.*;
import java.awt.Dimension;

public class TreeGrow {
	static long startTime = 0;
	static int frameX;
	static int frameY;
	static ForestPanel fp;

	// start timer
	private static void tick(){
		startTime = System.currentTimeMillis();
	}
	
	// stop timer, return time elapsed in seconds
	private static float tock(){
		return (System.currentTimeMillis() - startTime) / 1000.0f; 
	}
	
	public static void setupGUI(int frameX,int frameY,Tree [] trees) {
		Dimension fsize = new Dimension(800, 800);
		// Frame init and dimensions
    	JFrame frame = new JFrame("Photosynthesis"); 
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setPreferredSize(fsize);
    	frame.setSize(800, 800);
    	
      	JPanel g = new JPanel();
        g.setLayout(new BoxLayout(g, BoxLayout.PAGE_AXIS)); 
      	g.setPreferredSize(fsize);
 
		fp = new ForestPanel(trees);
		fp.setPreferredSize(new Dimension(frameX,frameY));
		JScrollPane scrollFrame = new JScrollPane(fp);
		fp.setAutoscrolls(true);
		scrollFrame.setPreferredSize(fsize);
	    g.add(scrollFrame);
    	
      	frame.setLocationRelativeTo(null);  // Center window on screen.
      	frame.add(g); //add contents to window
        frame.setContentPane(g);     
        frame.setVisible(true);
        Thread fpt = new Thread(fp);
        fpt.start();
	}
	
		
	public static void main(String[] args) {
		SunData sundata = new SunData();
		Buttons buttons = new Buttons();
		
		// check that number of command line arguments is correct
//		if(args.length != 1)
//		{
//			System.out.println("Incorrect number of command line arguments. Should have form: java treeGrow.java intputfilename");
//			System.exit(0);
//		}
//
		// read in forest and landscape information from file supplied as argument
		sundata.readData("src/ParallelTrees/sample_input.txt");
		System.out.println("Data loaded");
		
		frameX = sundata.sunmap.getDimX();
		frameY = sundata.sunmap.getDimY();
		buttons.createAndShowGUI();

		setupGUI(frameX, frameY, sundata.trees);
		
		// create and start simulation loop here as separate thread
		float minh = 0.0f;
		float maxh = 2.0f;
		for(int i = 0;i<5;i++){

			for(int layer = 0; layer <= 10; layer++) {
				for (Tree tree:sundata.trees) {
					if(tree.getExt() >= minh && tree.getExt() < maxh) {
						tree.sungrow(sundata.sunmap);
						sundata.sunmap.shadow(tree);
					}
				}
			}
			minh = maxh;  // next band of trees
			maxh += 2.0f;

		}
	}
}
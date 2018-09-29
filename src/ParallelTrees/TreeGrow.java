package ParallelTrees;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class TreeGrow implements ActionListener {
	static long startTime = 0;
	static int frameX;
	static int frameY;
	static ForestPanel fp;
	protected static JButton play, pause, reset, end;

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

	    play= new JButton("play");
		play.setVerticalTextPosition(AbstractButton.CENTER);
		play.setHorizontalTextPosition(AbstractButton.LEADING);
		play.setMnemonic(KeyEvent.VK_D);
		play.setActionCommand("disable");
		play.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				if ("disable".equals(e.getActionCommand())) {
					play.setEnabled(false);
				} else {

				}
			}
		} );
		g.add(play);

		pause = new JButton("pause");
		pause.setVerticalTextPosition(AbstractButton.CENTER);
		pause.setHorizontalTextPosition(AbstractButton.LEADING);
		pause.setMnemonic(KeyEvent.VK_D);
		pause.setActionCommand("disable");
		pause.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				if ("disable".equals(e.getActionCommand())) {
					pause.setEnabled(false);
				} else {

				}
			}
		} );
		g.add(pause);

		reset = new JButton("reset");
		reset.setVerticalTextPosition(AbstractButton.CENTER);
		reset.setHorizontalTextPosition(AbstractButton.LEADING);
		reset.setMnemonic(KeyEvent.VK_D);
		reset.setActionCommand("disable");
		reset.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				if ("disable".equals(e.getActionCommand())) {
					reset.setEnabled(false);
				} else {

				}
			}
		} );
		g.add(reset);

		end = new JButton("end");
		end.setVerticalTextPosition(AbstractButton.CENTER);
		end.setHorizontalTextPosition(AbstractButton.LEADING);
		end.setMnemonic(KeyEvent.VK_D);
		end.setActionCommand("disable");
		end.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				if ("disable".equals(e.getActionCommand())) {
					end.setEnabled(false);
				} else {

				}
			}
		} );
		g.add(end);

    	
      	frame.setLocationRelativeTo(null);  // Center window on screen.
      	frame.add(g); //add contents to window
        frame.setContentPane(g);     
        frame.setVisible(true);
        Thread fpt = new Thread(fp);
        fpt.start();
	}
	public void actionPerformed(ActionEvent e) {

	}
	
		
	public static void main(String[] args) {
		SunData sundata = new SunData();
		
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

		setupGUI(frameX, frameY, sundata.trees);
		for(int i=0;i<100;i++) {
			for (Tree tree : sundata.trees) {
				sundata.sunmap.shadow(tree);
				tree.sungrow(sundata.sunmap);
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				};
			}
		}

	}
}
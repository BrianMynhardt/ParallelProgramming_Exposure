package ParallelTrees;

// Trees define a canopy which covers a square area of the landscape
public class Tree{
	
private
	int xpos;	// x-coordinate of center of tree canopy
	int ypos;	// y-coorindate of center of tree canopy
	float ext;// extent of canopy out in vertical and horizontal from center

	static float growfactor = 1000.0f; // divide average sun exposure by this amount to get growth in extent
	
public	
	Tree(int x, int y, float e){
		xpos=x; ypos=y; ext=e;
	}
	
	int getX() {
		return xpos;
	}
	
	int getY() {
		return ypos;
	}
	
	float getExt() {
		return ext;
	}
	
	void setExt(float e) {
		ext = e;
	}

	// return the average sunlight for the cells covered by the tree
	float sunexposure(Land land){
		double extent = getExt();
		float sunlight=0;
		for(int i=(int)(ypos-Math.ceil(extent)); i < (ypos+Math.ceil(extent)); i++){
			for(int j=(int)(xpos-Math.ceil(extent)); j < (xpos+Math.ceil(extent)); j++){
				if(j < land.getDimX()&& j>=0 && i < land.getDimY()&&i>=0){
					sunlight += land.getShade(i,j);
				}
			}
		}
		sunlight = sunlight/(ext*2+1)*(ext*2+1);
		return sunlight;
	}
	
	// is the tree extent within the provided range [minr, maxr)
	boolean inrange(float minr, float maxr) {
		return (ext >= minr && ext < maxr);
	}
	
	// grow a tree according to its sun exposure
	void sungrow(Land land) {
		setExt(sunexposure(land)/growfactor);
	}
}
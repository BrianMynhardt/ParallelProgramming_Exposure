package ParallelTrees;

public class Land{
	
	// to do
	// sun exposure data here
	private int dimX,dimY;
	private float[][] sunMap;

	static float shadefraction = 0.1f; // only this fraction of light is transmitted by a tree

	Land(int dx, int dy) {
		// to do
		this.dimX = dx;
		this.dimY = dy;
		this.sunMap = new float[dy][dx];
	}

	int getDimX() {
		int temp = dimX;
		return temp;
	}
	
	int getDimY() {
		int temp = dimY;
		return temp;
	}
	
	// Reset the shaded landscape to the same as the initial sun exposed landscape
	// Needs to be done after each growth pass of the simulator
	void resetShade() {
		// to do
	}
	
	float getFull(int x, int y) {
		// to do
		return 0.0f; // incorrect value
	}
	
	void setFull(int x, int y, float val) {
		sunMap[y][x]=val;
	}
	
	float getShade(int x, int y) {
		// to do 
		return 0.0f; // incorrect value
	}
	
	void setShade(int x, int y, float val){
		// to do
	}
	
	// reduce the 
	void shadow(Tree tree){
		// to do
	}
}
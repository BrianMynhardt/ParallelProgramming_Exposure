package ParallelTrees;

import java.util.Arrays;

public class Land{
	
	// to do
	// sun exposure data here
	private final int dimX,dimY;
	volatile private float[][] sunMap;
	volatile private float[][] ShadedsunMap;


	static float shadefraction = 0.1f; // only this fraction of light is transmitted by a tree

	Land(int dx, int dy) {
		// to do
		this.dimX = dx;
		this.dimY = dy;
		this.sunMap = new float[dy][dx];
		this.ShadedsunMap = new float[dy][dx];
	}
	// return the number of landscape cells in the x dimension
	int getDimX() {
		return dimX;
	}
	// return the number of landscape cells in the y dimension
	int getDimY() {
		return dimY;
	}
	
	// Reset the shaded landscape to the same as the initial sun exposed landscape
	// Needs to be done after each growth pass of the simulator
	void resetShade() {
		for (int i=0; i<dimY;i++){
			for (int j=0;j<dimX;j++){
				ShadedsunMap[i][j] = getFull(j,i);
			}
		}
	}
	// return the sun exposure of the initial unshaded landscape at position <x,y?
	float getFull(int x, int y) {
		float temp = sunMap[y][x];
		return temp;
	}
	// set the sun exposure of the initial unshaded landscape at position <x,y> to <val>
	void setFull(int x, int y, float val) {
		sunMap[y][x]=val;
	}
	// return the current sun exposure of the shaded landscape at position <x,y>
	float getShade(int x, int y) {
		float temp = ShadedsunMap[y][x];
		return temp;
	}
	// set the sun exposure of the shaded landscape at position <x,y> to <val>
	void setShade(int x, int y, float val){
		ShadedsunMap[y][x]=val;
	}

	// reduce the sun exposure of the shaded landscape to 10% of the original
	// within the extent of <tree>
	void shadow(Tree tree){
		int ypos = tree.getY();
		int xpos = tree.getX();
		float ext = tree.getExt();
		float newVal;
		for(int i=(int)(ypos-Math.ceil(ext)); i < (ypos+Math.ceil(ext)); i++){
			for(int j=(int)(xpos-Math.ceil(ext)); j < (xpos+Math.ceil(ext)); j++){
				if(j < dimX && i < dimY && j>=0 && i>=0){
					newVal = sunMap[i][j];
					newVal = newVal*shadefraction;
					setShade(j,i,newVal);
				}
			}
		}
	}

	void resetExtent(Tree tree){
		tree.resetExtent();
	}
}
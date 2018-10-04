package ParallelTrees;

import java.util.concurrent.RecursiveTask;

public class SumArray extends RecursiveTask<Float> {
    int lo; // arguments
    int hi;
    Tree[] trees;
    static int SEQUENTIAL_CUTOFF = 500;
    Land sunMap;

    // result

    SumArray(Tree[] trees, int l, int h,Land sunmap) {
        this.lo = l;
        this.hi = h;
        this.trees = trees;
        this.sunMap = sunmap;
    }


    protected Float compute() {// return answer - instead of run
        if ((hi - lo) < SEQUENTIAL_CUTOFF) {

            for (int i = lo; i < hi; i++) {
                Tree tree = trees[i];
                float minh = 0.0f;
                float maxh = 2.0f;
                for(int layer = 0; layer <= 10; layer++) {

                if(tree.getExt() >= minh && tree.getExt() < maxh) { // only render trees in current band

                    tree.sungrow(sunMap);
                    sunMap.shadow(tree);
                }

                minh = maxh;
                maxh += 2.0f;
                  // next band of trees

                }
            }
            return 0.0f;
        } else {
            SumArray left = new SumArray(trees, lo, (hi + lo) / 2,sunMap);
            SumArray right = new SumArray(trees, (hi + lo) / 2, hi,sunMap);

            // order of next 4 lines
            // essential – why?
            left.fork();
            float rightAns = right.compute();
            float leftAns = left.join();
            return leftAns + rightAns;
        }
    }
}
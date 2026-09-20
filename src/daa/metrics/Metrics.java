package daa.metrics;

public class Metrics {

    private long comparisons;
    private int maxDepth;

    public void comparison() {
        comparisons++;
    }

    public void updateDepth(int depth) {
        if (depth > maxDepth) {
            maxDepth = depth;
        }
    }

    public long getComparisons() {
        return comparisons;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    public void reset() {
        comparisons = 0;
        maxDepth = 0;
    }
}
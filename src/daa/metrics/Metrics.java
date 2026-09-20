package daa.metrics;

public class Metrics {

    private long comparisons;
    private int maxDepth;

    public Metrics() {
        reset();
    }

    public void incrementComparisons() {
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
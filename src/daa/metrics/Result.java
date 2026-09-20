package daa.metrics;

public class Result {

    private final String algorithm;
    private final String input;
    private final int n;

    private final double timeMs;

    private final long comparisons;
    private final int maxDepth;

    public Result(
            String algorithm,
            String input,
            int n,
            double timeMs,
            long comparisons,
            int maxDepth
    ) {
        this.algorithm = algorithm;
        this.input = input;
        this.n = n;
        this.timeMs = timeMs;
        this.comparisons = comparisons;
        this.maxDepth = maxDepth;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public String getInput() {
        return input;
    }

    public int getN() {
        return n;
    }

    public double getTimeMs() {
        return timeMs;
    }

    public long getComparisons() {
        return comparisons;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    @Override
    public String toString() {
        return algorithm + "," +
                input + "," +
                n + "," +
                timeMs + "," +
                comparisons + "," +
                maxDepth;
    }
}
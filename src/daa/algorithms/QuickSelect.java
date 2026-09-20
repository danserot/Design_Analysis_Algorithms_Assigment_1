package daa.algorithms;

import daa.metrics.Metrics;

public class QuickSelect {

    public int select(int[] a, int k) {
        return select(a, k, new Metrics());
    }

    public int select(int[] a, int k, Metrics metrics) {

        if (a == null || a.length == 0) {
            throw new IllegalArgumentException(
                    "Array must not be null or empty."
            );
        }

        if (k < 0 || k >= a.length) {
            throw new IllegalArgumentException(
                    "k must be between 0 and " + (a.length - 1)
            );
        }

        int left = 0;
        int right = a.length - 1;

        int depth = 1;

        while (left <= right) {

            metrics.updateDepth(depth);

            int[] equalRange =
                    Partition.partition(a, left, right, metrics);

            int equalStart = equalRange[0];
            int equalEnd = equalRange[1];

            if (k < equalStart) {

                right = equalStart - 1;

            } else if (k > equalEnd) {

                left = equalEnd + 1;

            } else {

                return a[k];
            }

            depth++;
        }

        throw new IllegalStateException(
                "QuickSelect failed to find the element."
        );
    }
}
package daa.algorithms;

import daa.metrics.Metrics;

public class QuickSort implements Sorter {

    @Override
    public String name() {
        return "QuickSort";
    }

    @Override
    public void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) {
            return;
        }

        quickSort(
                a,
                0,
                a.length - 1,
                metrics,
                1
        );
    }

    private void quickSort(
            int[] a,
            int left,
            int right,
            Metrics metrics,
            int depth
    ) {

        while (left < right) {

            metrics.updateDepth(depth);

            int[] equalRange =
                    Partition.partition(a, left, right, metrics);

            int equalStart = equalRange[0];
            int equalEnd = equalRange[1];

            int leftSize = equalStart - left;
            int rightSize = right - equalEnd;

            /*
             * Recurse only into the smaller side.
             * Continue with the larger side using the while loop.
             *
             * This prevents deep recursion.
             */
            if (leftSize < rightSize) {

                if (left < equalStart - 1) {
                    quickSort(
                            a,
                            left,
                            equalStart - 1,
                            metrics,
                            depth + 1
                    );
                }

                left = equalEnd + 1;

            } else {

                if (equalEnd + 1 < right) {
                    quickSort(
                            a,
                            equalEnd + 1,
                            right,
                            metrics,
                            depth + 1
                    );
                }

                right = equalStart - 1;
            }
        }
    }
}
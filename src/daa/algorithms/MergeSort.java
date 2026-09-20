package daa.algorithms;

import daa.metrics.Metrics;

public class MergeSort implements Sorter {

    private static final int CUTOFF = 15;

    @Override
    public String name() {
        return "MergeSort";
    }

    @Override
    public void sort(int[] a, Metrics metrics) {
        if (a == null || a.length <= 1) {
            return;
        }

        // One reusable buffer for the whole algorithm
        int[] buffer = new int[a.length];

        mergeSort(
                a,
                buffer,
                0,
                a.length - 1,
                metrics,
                1
        );
    }

    private void mergeSort(
            int[] a,
            int[] buffer,
            int left,
            int right,
            Metrics metrics,
            int depth
    ) {

        metrics.updateDepth(depth);

        int size = right - left + 1;

        // Assignment requirement: cutoff <= 15
        if (size <= CUTOFF) {
            InsertionSort.sort(a, left, right, metrics);
            return;
        }

        int mid = left + (right - left) / 2;

        mergeSort(
                a,
                buffer,
                left,
                mid,
                metrics,
                depth + 1
        );

        mergeSort(
                a,
                buffer,
                mid + 1,
                right,
                metrics,
                depth + 1
        );

        merge(a, buffer, left, mid, right, metrics);
    }

    private void merge(
            int[] a,
            int[] buffer,
            int left,
            int mid,
            int right,
            Metrics metrics
    ) {

        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            metrics.incrementComparisons();

            if (a[i] <= a[j]) {
                buffer[k++] = a[i++];
            } else {
                buffer[k++] = a[j++];
            }
        }

        while (i <= mid) {
            buffer[k++] = a[i++];
        }

        while (j <= right) {
            buffer[k++] = a[j++];
        }

        for (int index = left; index <= right; index++) {
            a[index] = buffer[index];
        }
    }
}
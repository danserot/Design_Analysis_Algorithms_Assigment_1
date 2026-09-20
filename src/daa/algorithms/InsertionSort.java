package daa.algorithms;

import daa.metrics.Metrics;

public final class InsertionSort {

    private InsertionSort() {
    }

    public static void sort(int[] a, int left, int right, Metrics metrics) {
        for (int i = left + 1; i <= right; i++) {
            int value = a[i];
            int j = i - 1;

            while (j >= left) {
                metrics.incrementComparisons();

                if (a[j] <= value) {
                    break;
                }

                a[j + 1] = a[j];
                j--;
            }

            a[j + 1] = value;
        }
    }
}
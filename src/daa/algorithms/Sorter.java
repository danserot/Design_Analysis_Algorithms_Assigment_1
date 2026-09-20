package daa.algorithms;

import daa.metrics.Metrics;

public interface Sorter {

    String name();

    void sort(int[] a, Metrics metrics);
}
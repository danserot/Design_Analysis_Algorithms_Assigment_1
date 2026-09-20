package daa.bench;

import daa.algorithms.MergeSort;
import daa.algorithms.QuickSelect;
import daa.algorithms.QuickSort;
import daa.algorithms.Sorter;

import daa.metrics.Metrics;
import daa.metrics.Result;

import daa.utils.ArrayUtils;
import daa.utils.InputType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Benchmark {

    private static final int[] SIZES = {
            1_000,
            10_000,
            100_000,
            1_000_000
    };

    private static final int RUNS = 5;

    public List<Result> run() {

        List<Result> results = new ArrayList<>();

        Sorter[] sorters = {
                new MergeSort(),
                new QuickSort()
        };

        for (Sorter sorter : sorters) {

            for (InputType inputType : InputType.values()) {

                for (int size : SIZES) {

                    System.out.printf(
                            "%s | %s | n=%d%n",
                            sorter.name(),
                            inputType.getValue(),
                            size
                    );

                    int[] original =
                            ArrayUtils.generate(size, inputType);

                    Result result =
                            benchmarkSorter(
                                    sorter,
                                    original,
                                    inputType
                            );

                    results.add(result);
                }
            }
        }

        benchmarkQuickSelect(results);

        return results;
    }

    private Result benchmarkSorter(
            Sorter sorter,
            int[] original,
            InputType inputType
    ) {

        List<RunResult> runs = new ArrayList<>();

        for (int run = 0; run < RUNS; run++) {

            int[] array =
                    ArrayUtils.copy(original);

            Metrics metrics =
                    new Metrics();

            long start =
                    System.nanoTime();

            sorter.sort(array, metrics);

            long end =
                    System.nanoTime();

            double timeMs =
                    (end - start) / 1_000_000.0;

            runs.add(
                    new RunResult(
                            timeMs,
                            metrics.getComparisons(),
                            metrics.getMaxDepth()
                    )
            );
        }

        runs.sort(
                Comparator.comparingDouble(
                        RunResult::timeMs
                )
        );

        RunResult median =
                runs.get(RUNS / 2);

        return new Result(
                sorter.name(),
                inputType.getValue(),
                original.length,
                median.timeMs(),
                median.comparisons(),
                median.maxDepth()
        );
    }

    private void benchmarkQuickSelect(
            List<Result> results
    ) {

        QuickSelect quickSelect =
                new QuickSelect();

        for (InputType inputType : InputType.values()) {

            for (int size : SIZES) {

                System.out.printf(
                        "QuickSelect | %s | n=%d%n",
                        inputType.getValue(),
                        size
                );

                int[] original =
                        ArrayUtils.generate(
                                size,
                                inputType
                        );

                int k = size / 2;

                List<RunResult> runs =
                        new ArrayList<>();

                for (int run = 0;
                     run < RUNS;
                     run++) {

                    int[] array =
                            ArrayUtils.copy(original);

                    Metrics metrics =
                            new Metrics();

                    long start =
                            System.nanoTime();

                    quickSelect.select(
                            array,
                            k,
                            metrics
                    );

                    long end =
                            System.nanoTime();

                    double timeMs =
                            (end - start)
                                    / 1_000_000.0;

                    runs.add(
                            new RunResult(
                                    timeMs,
                                    metrics.getComparisons(),
                                    metrics.getMaxDepth()
                            )
                    );
                }

                runs.sort(
                        Comparator.comparingDouble(
                                RunResult::timeMs
                        )
                );

                RunResult median =
                        runs.get(RUNS / 2);

                results.add(
                        new Result(
                                "QuickSelect",
                                inputType.getValue(),
                                size,
                                median.timeMs(),
                                median.comparisons(),
                                median.maxDepth()
                        )
                );
            }
        }
    }

    private record RunResult(
            double timeMs,
            long comparisons,
            int maxDepth
    ) {
    }
}
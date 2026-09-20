package daa;

import daa.bench.Benchmark;
import daa.metrics.CsvWritter;
import daa.metrics.CsvWritter;
import daa.metrics.Result;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println(
                "DAA Assignment 1"
        );

        System.out.println(
                "Starting benchmark..."
        );

        Benchmark benchmark =
                new Benchmark();

        List<Result> results =
                benchmark.run();

        try {

            CsvWritter.write(
                    "results.csv",
                    results
            );

            System.out.println();
            System.out.println(
                    "Benchmark completed."
            );

            System.out.println(
                    "Results saved to results.csv"
            );

        } catch (IOException e) {

            System.err.println(
                    "Could not write results.csv"
            );

            e.printStackTrace();
        }
    }
}
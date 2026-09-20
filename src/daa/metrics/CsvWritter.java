package daa.metrics;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

public final class CsvWritter {

    private CsvWritter() {
    }

    public static void write(
            String filename,
            List<Result> results
    ) throws IOException {

        try (PrintWriter writer =
                     new PrintWriter(new FileWriter(filename))) {

            writer.println(
                    "algorithm,input,n,time_ms,comparisons,max_depth"
            );

            for (Result result : results) {

                writer.printf(
                        Locale.US,
                        "%s,%s,%d,%.6f,%d,%d%n",
                        result.getAlgorithm(),
                        result.getInput(),
                        result.getN(),
                        result.getTimeMs(),
                        result.getComparisons(),
                        result.getMaxDepth()
                );
            }
        }
    }
}
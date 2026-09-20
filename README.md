# Design and Analysis of Algorithms — Assignment 1

## Divide and Conquer & Asymptotic Notations

This project contains implementations and experimental analysis of several divide-and-conquer algorithms:

- MergeSort
- QuickSort
- QuickSelect
- InsertionSort as a small-subarray optimization for MergeSort

The project also contains a benchmarking system that measures execution time, number of comparisons, and maximum recursion depth.

---

## Project Structure

```text
Design_Analysis_Algorithms_Assigment_1/
│
├── src/
│   └── daa/
│       ├── Main.java
│       │
│       ├── algorithms/
│       │   ├── Sorter.java
│       │   ├── InsertionSort.java
│       │   ├── MergeSort.java
│       │   ├── Partition.java
│       │   ├── QuickSort.java
│       │   └── QuickSelect.java
│       │
│       ├── bench/
│       │   └── Benchmark.java
│       │
│       ├── metrics/
│       │   ├── Metrics.java
│       │   ├── Result.java
│       │   └── CsvWriter.java
│       │
│       └── utils/
│           ├── ArrayUtils.java
│           └── InputType.java
│
├── test/
│   └── daa/
│       ├── MergeSortTest.java
│       ├── QuickSortTest.java
│       └── QuickSelectTest.java
│
├── plots/
│   ├── time.png
│   ├── depth.png
│   └── ratio.png
│
├── pom.xml
├── results.csv
├── REPORT.md
└── README.md
```

---

## Algorithms

### MergeSort

MergeSort follows the divide-and-conquer approach.

The array is recursively divided into two halves. After both halves are sorted, they are merged in linear time.

The implementation uses one reusable auxiliary buffer allocated at the beginning of sorting. No new temporary arrays are allocated during recursive calls.

For subarrays of size 15 or less, the implementation switches to InsertionSort.

### QuickSort

QuickSort uses a randomly selected pivot and three-way partitioning.

After partitioning, the array is divided into:

```text
< pivot | = pivot | > pivot
```

Three-way partitioning improves behavior when the input contains many duplicate values.

To limit recursion depth, QuickSort recursively processes only the smaller partition. The larger partition is processed iteratively using a while loop.

### QuickSelect

QuickSelect finds the element that would appear at index `k` in a sorted array without sorting the complete array.

`k` is zero-based.

QuickSelect reuses the same three-way partition implementation as QuickSort. After partitioning, it continues only with the partition containing index `k`.

Invalid values of `k` and empty arrays produce an `IllegalArgumentException`.

---

## Metrics

The `Metrics` class records:

- number of element comparisons;
- maximum recursion depth.

Execution time is measured using:

```java
System.nanoTime()
```

Benchmark results are stored in:

```text
results.csv
```

with the following columns:

```text
algorithm,input,n,time_ms,comparisons,max_depth
```

---

## Benchmark

The following input sizes are tested:

```text
1,000
10,000
100,000
1,000,000
```

Three input types are used:

- `random` — random integer values;
- `sorted` — already sorted values;
- `duplicates` — random values from 0 to 9.

Each experiment is executed five times.

The runs are sorted by execution time and the median run is saved. Using the median reduces the influence of occasional JVM, operating-system, and garbage-collection delays.

For QuickSelect, the benchmark searches for the middle element:

```java
k = n / 2;
```

---

## Requirements

- Java 17 or newer
- Maven
- JUnit 5

Check Java:

```bash
java -version
```

Check Maven:

```bash
mvn -version
```

---

## Build

Clone the repository:

```bash
git clone https://github.com/danserot/Design_Analysis_Algorithms_Assigment_1.git
```

Open the project directory:

```bash
cd Design_Analysis_Algorithms_Assigment_1
```

Compile:

```bash
mvn clean compile
```

---

## Run Tests

Run all JUnit tests:

```bash
mvn test
```

The tests compare the sorting algorithms with Java's `Arrays.sort()` on at least 100 randomly generated arrays.

The tests also cover:

- empty arrays;
- one-element arrays;
- arrays with equal elements;
- already sorted arrays;
- QuickSort recursion depth;
- QuickSelect correctness;
- invalid QuickSelect arguments.

---

## Run Benchmark

Run the main program from IntelliJ IDEA or compile and execute the project.

The benchmark generates:

```text
results.csv
```

The CSV file contains the experimental results used in the report.

---

## Complexity Summary

| Algorithm | Best | Average | Worst |
|---|---|---|---|
| MergeSort | Θ(n log n) | Θ(n log n) | Θ(n log n) |
| QuickSort | Θ(n log n) | Θ(n log n) expected | Θ(n²) |
| QuickSelect | Θ(n) | Θ(n) expected | Θ(n²) |
| InsertionSort | Θ(n) | Θ(n²) | Θ(n²) |

More detailed theoretical and experimental analysis is available in `REPORT.md`.

---

## Author

Artem Khloptsev

Astana IT University  
Design and Analysis of Algorithms  
Assignment 1
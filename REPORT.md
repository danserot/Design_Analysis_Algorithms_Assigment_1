# Assignment 1 Report

## Divide and Conquer & Asymptotic Notations

**Student:** Artem Khloptsev  
**Course:** Design and Analysis of Algorithms  
**University:** Astana IT University

---

# 1. Introduction

The purpose of this assignment is to study divide-and-conquer algorithms both theoretically and experimentally.

The project implements three main algorithms:

- MergeSort;
- QuickSort;
- QuickSelect.

InsertionSort is additionally used as an optimization inside MergeSort for small subarrays.

The experimental part measures execution time, number of comparisons, and maximum recursion depth for different input sizes and input distributions.

The benchmark tests arrays containing from 1,000 to 1,000,000 elements using random, sorted, and duplicate-heavy inputs.

Each experiment is executed five times and the median execution time is used.

---

# 2. Asymptotic Analysis

## 2.1 Complexity Table

| Algorithm | Best Case | Average Case | Worst Case |
|---|---:|---:|---:|
| MergeSort | Θ(n log n) | Θ(n log n) | Θ(n log n) |
| QuickSort | Θ(n log n) | Θ(n log n) expected | Θ(n²) |
| QuickSelect | Θ(n) | Θ(n) expected | Θ(n²) |
| InsertionSort | Θ(n) | Θ(n²) | Θ(n²) |

### MergeSort

MergeSort always divides the input into approximately equal halves and performs a linear merge operation at every level of recursion.

Therefore, its running time remains Θ(n log n) in the best, average, and worst cases.

The cutoff to InsertionSort does not change the asymptotic complexity because the cutoff size is a constant.

### QuickSort

QuickSort has Θ(n log n) running time when partitions are reasonably balanced.

With a random pivot, the expected running time is Θ(n log n).

The worst case occurs when partitions repeatedly become extremely unbalanced, producing Θ(n²) work.

The implementation uses three-way partitioning, which is especially useful when many elements are equal to the pivot.

### QuickSelect

QuickSelect performs a partition similarly to QuickSort, but it continues only with the partition containing the required index `k`.

Its expected running time is Θ(n).

The worst case is Θ(n²) when the algorithm repeatedly produces highly unbalanced partitions.

### InsertionSort

InsertionSort requires only Θ(n) time in the best case when the array is already sorted.

In the average and worst cases, elements may need to be shifted through a large part of the array, resulting in Θ(n²).

In this project, InsertionSort is used only for subarrays of size 15 or less.

---

# 3. Recurrence Relations

## 3.1 MergeSort

MergeSort divides a problem of size `n` into two subproblems of approximately size `n/2`.

Merging the two sorted halves requires linear time.

Therefore:

```text
T(n) = 2T(n/2) + Θ(n)
```

For the Master Theorem:

```text
a = 2
b = 2
f(n) = Θ(n)
```

We calculate:

```text
n^(log_b(a))
=
n^(log_2(2))
=
n
```

Therefore:

```text
f(n) = Θ(n^(log_b(a)))
```

This is Master Theorem Case 2.

Thus:

```text
T(n) = Θ(n log n)
```

---

## 3.2 QuickSort

For a balanced partition, QuickSort creates two subproblems of approximately size `n/2`.

Partitioning requires linear time.

Therefore, for the balanced case:

```text
T(n) = 2T(n/2) + Θ(n)
```

Using the Master Theorem:

```text
a = 2
b = 2
f(n) = Θ(n)
```

and:

```text
n^(log_2(2)) = n
```

Therefore:

```text
T(n) = Θ(n log n)
```

However, QuickSort does not guarantee balanced partitions.

If the pivot repeatedly creates partitions of sizes approximately `0` and `n - 1`, the recurrence becomes:

```text
T(n) = T(n - 1) + Θ(n)
```

which gives:

```text
T(n) = Θ(n²)
```

This implementation selects the pivot randomly.

Random pivot selection makes repeatedly poor pivot choices unlikely and gives expected Θ(n log n) running time.

The implementation also uses three-way partitioning:

```text
< pivot | = pivot | > pivot
```

This prevents equal elements from being repeatedly processed in recursive partitions.

---

# 4. QuickSelect Analysis

For a balanced QuickSelect partition, only one half must be processed.

Therefore:

```text
T(n) = T(n/2) + Θ(n)
```

For the Master Theorem:

```text
a = 1
b = 2
f(n) = Θ(n)
```

Then:

```text
n^(log_2(1)) = 1
```

Since:

```text
f(n) = Θ(n)
```

is polynomially larger than:

```text
n^(log_2(1)) = Θ(1)
```

this corresponds to Master Theorem Case 3.

Therefore:

```text
T(n) = Θ(n)
```

This explains why QuickSelect can find the k-th element in expected linear time without sorting the complete array.

---

# 5. Implementation Details

## 5.1 MergeSort Reusable Buffer

A single auxiliary array is allocated when MergeSort begins:

```java
int[] buffer = new int[a.length];
```

The same buffer is passed to every recursive call.

This avoids repeatedly allocating temporary arrays during recursion.

For small subarrays, the implementation uses:

```text
CUTOFF = 15
```

and switches to InsertionSort.

For very small arrays, the overhead of additional recursive calls can be greater than the cost of using a simple quadratic algorithm.

---

## 5.2 Three-Way Partition

QuickSort and QuickSelect share the same partition implementation.

A random pivot is selected and the current range is divided into:

```text
< pivot | = pivot | > pivot
```

The partition operation runs in Θ(n) time for a range containing `n` elements.

This approach is useful for duplicate-heavy inputs because all elements equal to the pivot are handled in a single partition operation.

---

## 5.3 Bounded QuickSort Recursion

A standard recursive QuickSort implementation may recursively process both partitions:

```text
QuickSort(left)
QuickSort(right)
```

This can result in large recursion depth.

The implementation in this project compares the sizes of the two partitions.

Only the smaller partition is processed recursively.

The larger partition is processed by continuing the `while` loop.

Conceptually:

```text
smaller partition -> recursion
larger partition  -> iteration
```

This limits the amount of stack space used by the algorithm.

The JUnit test verifies the required condition for a sorted array of 100,000 elements:

```text
maximum depth <= 2 * log2(n)
```

---

# 6. QuickSelect Implementation

QuickSelect uses the same three-way partition operation as QuickSort.

After partitioning, three cases are possible.

If:

```text
k < equalStart
```

the algorithm continues in the left partition.

If:

```text
k > equalEnd
```

the algorithm continues in the right partition.

Otherwise, `k` belongs to the range containing elements equal to the pivot and the required element has been found.

Unlike QuickSort, QuickSelect never needs to process both sides of the partition.

---

# 7. Experimental Setup

The benchmark uses the following input sizes:

```text
1,000
10,000
100,000
1,000,000
```

Three input distributions are tested.

### Random

The array contains randomly generated Java integer values.

### Sorted

A random array is generated and sorted before the experiment begins.

### Duplicates

Every element is randomly selected from:

```text
0 ... 9
```

This creates an input containing a large number of duplicate values.

Each experiment is executed five times.

Execution time is measured using:

```java
System.nanoTime()
```

The five runs are ordered by execution time and the median run is saved.

The following values are recorded:

```text
algorithm
input
n
time_ms
comparisons
max_depth
```

---

# 8. Experimental Results

The benchmark was executed for input sizes from 1,000 to 1,000,000 elements. Each configuration was executed five times, and the median execution time was recorded.

The results show clear differences between the algorithms and input distributions.

## 8.1 Execution Time

For random input, MergeSort produced the following execution times:

| n | Time (ms) |
|---:|---:|
| 1,000 | 0.1330 |
| 10,000 | 2.4374 |
| 100,000 | 7.8152 |
| 1,000,000 | 91.0873 |

QuickSort on random input produced:

| n | Time (ms) |
|---:|---:|
| 1,000 | 0.1503 |
| 10,000 | 0.7255 |
| 100,000 | 8.7539 |
| 1,000,000 | 100.4574 |

QuickSelect on random input produced:

| n | Time (ms) |
|---:|---:|
| 1,000 | 0.0107 |
| 10,000 | 0.1013 |
| 100,000 | 1.1155 |
| 1,000,000 | 11.3411 |

The results are consistent with the expected difference between sorting and selection. MergeSort and QuickSort must process enough information to completely sort the array, while QuickSelect continues only with the partition that contains the required element.

For one million random elements, MergeSort required approximately 91.09 ms, QuickSort approximately 100.46 ms, while QuickSelect required approximately 11.34 ms.

The exact execution times should not be interpreted as direct measurements of asymptotic complexity because JVM optimization, caching, garbage collection, and random pivot selection also affect the results.

---

## 8.2 Influence of Input Distribution

Input distribution had a significant effect on the measured execution time.

### MergeSort

For `n = 1,000,000`:

| Input | Time (ms) | Comparisons |
|---|---:|---:|
| Random | 91.0873 | 19,883,397 |
| Sorted | 22.7494 | 9,071,040 |
| Duplicates | 43.6915 | 18,927,446 |

MergeSort was considerably faster on already sorted input.

Although MergeSort has Θ(n log n) asymptotic complexity for all three input types, the number of comparisons and practical execution cost can still differ.

For sorted data, merging often exhausts one side earlier, resulting in fewer element comparisons.

The asymptotic complexity therefore remains Θ(n log n), even though the constant factors differ.

### QuickSort

For `n = 1,000,000`:

| Input | Time (ms) | Comparisons | Max Depth |
|---|---:|---:|---:|
| Random | 100.4574 | 37,824,915 | 13 |
| Sorted | 47.2229 | 39,405,045 | 13 |
| Duplicates | 12.2708 | 8,004,290 | 2 |

The duplicate-heavy input was particularly favorable for the implemented QuickSort.

This behavior is explained by three-way partitioning:

```text
< pivot | = pivot | > pivot
```

Since duplicate inputs contain only values from 0 to 9, many elements become part of the `= pivot` region during a single partition operation.

These elements do not need to be processed again.

As a result, the maximum recursion depth for one million duplicate elements was only 2 in the median recorded run, compared with 13 for random and sorted inputs.

---

## 8.3 Maximum Recursion Depth

MergeSort showed logarithmic recursion growth.

For all three input distributions, the recorded depths were:

| n | MergeSort Max Depth |
|---:|---:|
| 1,000 | 8 |
| 10,000 | 11 |
| 100,000 | 14 |
| 1,000,000 | 18 |

This behavior agrees with the divide-by-two structure of MergeSort.

The depth does not depend strongly on the input values because MergeSort always divides the range near its midpoint.

QuickSort also maintained small recursion depth.

For random input:

| n | QuickSort Max Depth |
|---:|---:|
| 1,000 | 6 |
| 10,000 | 9 |
| 100,000 | 10 |
| 1,000,000 | 13 |

The result demonstrates the effect of recursively processing only the smaller partition while processing the larger partition iteratively.

For comparison:

```text
2 * log2(1,000,000) ≈ 39.86
```

The observed random-input depth of 13 is well below this limit.

For duplicate-heavy input, the observed depths were even smaller:

```text
n = 1,000       -> depth 2
n = 10,000      -> depth 2
n = 100,000     -> depth 3
n = 1,000,000   -> depth 2
```

This is another consequence of three-way partitioning.

---

## 8.4 Comparison Growth

The number of comparisons also shows the expected growth behavior.

For random MergeSort:

| n | Comparisons |
|---:|---:|
| 1,000 | 9,637 |
| 10,000 | 127,178 |
| 100,000 | 1,638,836 |
| 1,000,000 | 19,883,397 |

For random QuickSort:

| n | Comparisons |
|---:|---:|
| 1,000 | 19,059 |
| 10,000 | 247,345 |
| 100,000 | 3,280,494 |
| 1,000,000 | 37,824,915 |

Both algorithms show growth broadly consistent with an `n log n` model.

QuickSort records more comparisons partly because the three-way partition implementation may perform both `< pivot` and `> pivot` checks for an element.

Therefore, comparison counts between the two algorithms should not be interpreted as if each algorithm performs exactly the same type of comparison operation.

---

# 9. Empirical Θ Check

To experimentally examine the theoretical bounds, normalized comparison ratios can be used.

For MergeSort and QuickSort:

```text
R(n) = comparisons / (n * log2(n))
```

For QuickSelect:

```text
R(n) = comparisons / n
```

If these ratios remain bounded for sufficiently large `n`, the measurements are consistent with the predicted growth functions.

## MergeSort — Random Input

Approximate normalized ratios are:

| n | comparisons / (n log2 n) |
|---:|---:|
| 1,000 | 0.967 |
| 10,000 | 0.957 |
| 100,000 | 0.987 |
| 1,000,000 | 0.998 |

The ratio becomes very stable as `n` increases.

For the measured random inputs, a reasonable empirical bound is approximately:

```text
0.95 * n log2(n)
<= comparisons <=
1.01 * n log2(n)
```

for:

```text
n >= 1,000
```

Therefore, for this experiment:

```text
c1 ≈ 0.95
c2 ≈ 1.01
n0 = 1,000
```

This experimental result is strongly consistent with the theoretical:

```text
Θ(n log n)
```

comparison growth of MergeSort.

---

## QuickSort — Random Input

Approximate ratios are:

| n | comparisons / (n log2 n) |
|---:|---:|
| 1,000 | 1.912 |
| 10,000 | 1.862 |
| 100,000 | 1.975 |
| 1,000,000 | 1.898 |

The ratio remains within a relatively narrow interval.

A reasonable empirical bound for these measurements is:

```text
1.85 * n log2(n)
<= comparisons <=
2.00 * n log2(n)
```

for:

```text
n >= 1,000
```

Therefore:

```text
c1 ≈ 1.85
c2 ≈ 2.00
n0 = 1,000
```

The experiment is consistent with expected Θ(n log n) comparison growth for randomized QuickSort on random input.

---

## QuickSelect — Random Input

The measured comparison counts were:

| n | Comparisons | comparisons / n |
|---:|---:|---:|
| 1,000 | 3,771 | 3.771 |
| 10,000 | 41,430 | 4.143 |
| 100,000 | 451,365 | 4.514 |
| 1,000,000 | 8,062,301 | 8.062 |

The ratio is less stable than the sorting ratios.

This is expected because QuickSelect uses random pivots, so the amount of work depends strongly on the sequence of partitions generated during a particular run.

The measurements still show much slower growth than a full quadratic process and are compatible with expected linear behavior, but this individual data set is not sufficient to claim a tight empirical constant across all tested sizes.

More repeated experiments would provide a more stable estimate.

---

# 10. Discussion

The experimental results generally agree with the theoretical analysis.

MergeSort showed predictable behavior as the input size increased. Its recursion depth increased from 8 at `n = 1,000` to 18 at `n = 1,000,000`, which is consistent with logarithmic recursion depth. Its normalized comparison ratio on random input remained close to 1, providing strong experimental evidence for Θ(n log n) comparison growth.

Randomized QuickSort also showed comparison growth consistent with expected Θ(n log n). The smaller-side-first recursion strategy kept the maximum stack depth low: even for one million random elements, the measured maximum depth was only 13.

Three-way partitioning had a particularly strong effect on duplicate-heavy inputs. QuickSort processed one million duplicate-heavy elements in approximately 12.27 ms, compared with approximately 100.46 ms for random input. Because the duplicate data contains only values from 0 to 9, large groups of elements can be removed from further processing through the `= pivot` partition.

QuickSelect was significantly faster than the full sorting algorithms on random input because it continues with only one partition after each partition operation. For one million random elements, its measured execution time was approximately 11.34 ms.

The execution times are also influenced by factors outside asymptotic analysis. The JVM performs JIT compilation during execution, garbage collection can temporarily interrupt the program, and CPU caching affects memory-access performance. Random pivot selection introduces additional variation between QuickSort and QuickSelect runs. Using the median of five runs reduces the effect of occasional unusually slow measurements.

Overall, the measurements are consistent with the expected theoretical behavior of the implemented algorithms, while also demonstrating the importance of input distribution and implementation details in practical performance.
# 11. Testing

JUnit 5 is used for correctness testing.

MergeSort and QuickSort are compared with:

```java
Arrays.sort()
```

on at least 100 randomly generated arrays.

The tests also include the following edge cases:

```text
empty array
one element
all equal elements
already sorted array
```

QuickSort additionally includes a recursion-depth test using a sorted array containing 100,000 elements.

The required condition is:

```text
maxDepth <= 2 * log2(n)
```

QuickSelect is tested by sorting a copy of the same random array and verifying:

```java
select(a, k) == sorted[k]
```

for at least 100 randomly generated arrays.

Invalid QuickSelect inputs are also tested.

---

# 12. Conclusion

The assignment demonstrates how divide-and-conquer algorithms can have different performance characteristics even when they use similar partitioning or recursive techniques.

MergeSort provides predictable Θ(n log n) performance because it always divides the problem into approximately equal halves.

QuickSort provides expected Θ(n log n) performance with random pivot selection, while three-way partitioning improves its behavior for duplicate-heavy inputs.

QuickSelect demonstrates that complete sorting is unnecessary when only one order statistic is required, giving expected Θ(n) running time.

The experiments also demonstrate that theoretical asymptotic complexity describes growth rather than exact execution time. JVM behavior, memory access, garbage collection, pivot selection, and implementation details all influence real measurements.

Overall, the experimental benchmark provides a practical comparison with the theoretical complexity analysis of the implemented algorithms.
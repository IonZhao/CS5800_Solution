package com.yang.learning.cs5800.HW4;

import java.util.ArrayList;

public class Sort {

    public static void bucketSort(int[] data, int numberOfBuckets) {
        // Create an array of buckets
        ArrayList<Integer>[] buckets = new ArrayList[numberOfBuckets];
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets[i] = new ArrayList<>();
        }

        // Determine the range of values in the data
        int maxValue = Integer.MIN_VALUE;
        int minValue = Integer.MAX_VALUE;

        for (int num : data) {
            if (num > maxValue) {
                maxValue = num;
            }
            if(num < minValue) {
                minValue = num;
            }
        }

        // Distribute the input values into the buckets
        for (int num : data) {
            int bucketIndex = (num - minValue) * numberOfBuckets / (maxValue - minValue + 1);
            buckets[bucketIndex].add(num);
        }

        // Sort each bucket using insertion sort and merge the results
        int index = 0;
        for (ArrayList<Integer> bucket : buckets) {
            insertionSort(bucket);  // Use insertion sort for each bucket
            for (int num : bucket) {
                data[index++] = num;
            }
        }
    }

    // Insertion Sort function to sort a list
    private static void insertionSort(ArrayList<Integer> bucket) {
        for (int i = 1; i < bucket.size(); i++) {
            int value = bucket.get(i);
            int j = i - 1;

            // Move elements that are greater than key to one position ahead of their current position
            while (j >= 0 && bucket.get(j) > value) {
                bucket.set(j + 1, bucket.get(j));
                j--;
            }
            bucket.set(j + 1, value);
        }
    }

    public static void main(String[] args) {
        int[] data = {34, 7, 23, 32, 5, 62, 32, 7, 34, 23, 1, 99, 50, 4, 2};

        // Testing different bucket sizes
        for (int numberOfBuckets : new int[]{5, 10, 15}) {
            // Create a copy of the original data for sorting
            int[] dataCopy = data.clone();

            // Measure the time taken to sort the array
            long startTime = System.nanoTime();
            bucketSort(dataCopy, numberOfBuckets);
            long endTime = System.nanoTime();

            // Print the sorted output and the time taken
            System.out.println("Sorted output with " + numberOfBuckets + " buckets:");
            for (int num : dataCopy) {
                System.out.print(num + " ");
            }
            System.out.println("\nTime taken: " + (endTime - startTime) + " nanoseconds\n");
        }

    }
}

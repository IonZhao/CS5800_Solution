package com.yang.learning.cs5800.HW3;
import java.util.Arrays;

// Interface for objects that can be sorted
interface Comparable<T> {
    int compareTo(T other);
}
// Product Class
class Product implements Comparable<Product> {
    int id;
    int demand;

    // Constructor
    public Product(int id, int demand) {
        this.id = id;
        this.demand = demand;
    }

    // Override toString for easy display
    @Override
    public String toString() {
        return "{id: " + id + ", demand: " + demand + "}";
    }

    // Implement compareTo for sorting by demand
    @Override
    public int compareTo(Product other) {
        return Integer.compare(this.demand, other.demand);
    }
}

// Feedback Class
class Feedback implements Comparable<Feedback> {
    int rating;
    String comment;

    // Constructor
    public Feedback(int rating, String comment) {
        this.rating = rating;
        this.comment = comment;
    }

    // Override toString for easy display
    @Override
    public String toString() {
        return "{rating: " + rating + ", comment: \"" + comment + "\"}";
    }

    // Implement compareTo for sorting by rating
    @Override
    public int compareTo(Feedback other) {
        return Integer.compare(this.rating, other.rating);
    }
}

// Generic QuickSort Class
class QuickSort<T extends Comparable<T>> {

    // Method to perform Quick Sort
    public void sort(T[] items, int low, int high) {
        if (low < high) {
            int pi = partition(items, low, high);
            sort(items, low, pi - 1);
            sort(items, pi + 1, high);
        }
    }

    // Method to partition the array
    private int partition(T[] items, int low, int high) {
        T pivot = items[high];
        int i = (low - 1); // Index of bigger element

        for (int j = low; j < high; j++) {
            // If current element is greater than or equal to pivot
            if (items[j].compareTo(pivot) >= 0) {
                i++;
                // Swap items[i] and items[j]
                T temp = items[i];
                items[i] = items[j];
                items[j] = temp;
            }
        }

        // Swap items[i + 1] and items[high] (or pivot)
        T temp = items[i + 1];
        items[i + 1] = items[high];
        items[high] = temp;

        return i + 1;
    }
}

// Main Class for Warehouse Products
class Question1 {
    public static void main(String[] args) {
        // Input: Array of products
        Product[] products = {
                new Product(101, 50),
                new Product(102, 75),
                new Product(103, 20),
                new Product(104, 90),
                new Product(105, 10),
                new Product(106, 40),
                new Product(107, 60)
        };

        // Create an instance of QuickSort and sort the products
        QuickSort<Product> productQuickSort = new QuickSort<>();
        productQuickSort.sort(products, 0, products.length - 1);

        // Output: Sorted products by demand score
        System.out.println("Sorted products by demand score:");
        System.out.println(Arrays.toString(products));


    }
}
class Question2{
    public static void main(String[] args) {
        // Input: Array of feedback
        Feedback[] feedbacks = {
                new Feedback(5, "Excellent service and food!"),
                new Feedback(3, "Food was good but the service was slow."),
                new Feedback(4, "Nice ambiance, will visit again."),
                new Feedback(2, "The meal was cold and not tasty."),
                new Feedback(1, "Terrible experience, never coming back."),
                new Feedback(4, "Great food, but a bit expensive."),
                new Feedback(5, "Loved everything about this place!"),
                new Feedback(3, "Average experience, nothing special."),
                new Feedback(2, "Service was bad, but food was okay.")
        };

        // Create an instance of QuickSort and sort the feedback
        QuickSort<Feedback> feedbackQuickSort = new QuickSort<>();
        feedbackQuickSort.sort(feedbacks, 0, feedbacks.length - 1);

        // Output: Sorted feedback by rating
        System.out.println("Sorted feedback by rating:");
        System.out.println(Arrays.toString(feedbacks));
    }
}

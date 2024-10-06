package com.yang.learning.cs5800.HW3;

import java.util.ArrayList;
import java.util.Arrays;

class Task {
    int priority;
    String description;

    // Constructor
    public Task(int priority, String description) {
        this.priority = priority;
        this.description = description;
    }

    // To display task info
    @Override
    public String toString() {
        return "{priority: " + priority + ", description: \"" + description + "\"}";
    }
}

class MinHeap {
    private Task[] heap;
    private int size;
    private int capacity;

    // Constructor to initialize the heap with a given capacity
    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new Task[capacity];
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }

    // Method to insert a new task into the heap
    public void insert(Task task) {
        if (size == capacity) {
            System.out.println("Heap overflow");
            return;
        }
        // Insert the new task at the end of the heap
        size++;
        int i = size - 1;
        heap[i] = task;

        // Fix the min-heap property by adjusting the position of the inserted task
        while (i != 0 && heap[parent(i)].priority > heap[i].priority) {
            // Swap the current task with its parent
            Task temp = heap[i];
            heap[i] = heap[parent(i)];
            heap[parent(i)] = temp;

            // Move up to the parent node
            i = parent(i);
        }
    }

    // Method to extract the task with the highest priority (minimum priority value)
    public Task extractMin() {
        if (size == 0)
            return null;
        if (size == 1) {
            size--;
            return heap[0];
        }

        // Store the minimum task (root of the heap) and remove it
        Task root = heap[0];
        heap[0] = heap[size - 1];
        size--;

        // Heapify the root to maintain the min-heap property
        heapify(0);

        return root;
    }

    // Method to heapify a subtree with the root at index i
    private void heapify(int i) {
        int left = left(i);
        int right = right(i);
        int smallest = i;

        // If the left child is smaller than the current node
        if (left < size && heap[left].priority < heap[smallest].priority) {
            smallest = left;
        }

        // If the right child is smaller than the smallest found so far
        if (right < size && heap[right].priority < heap[smallest].priority) {
            smallest = right;
        }

        // If the smallest is not the root, swap it and continue heapifying
        if (smallest != i) {
            Task temp = heap[i];
            heap[i] = heap[smallest];
            heap[smallest] = temp;

            heapify(smallest);
        }
    }

    // Build the heap from an array of tasks
    public void buildHeap(Task[] tasks) {
        this.size = tasks.length;
        this.heap = Arrays.copyOf(tasks, size);

        // Call heapify from the last non-leaf node to the root
        for (int i = (size / 2) - 1; i >= 0; i--) {
            heapify(i);
        }
    }

    // Check if the heap is empty
    public boolean isEmpty() {
        return size == 0;
    }
    // Override toString method to display the heap contents
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MinHeap: [");
        for (int i = 0; i < size; i++) {
            sb.append(heap[i].toString());
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}

class Question3 {
    public static void main(String[] args) {
        // Example input: list of tasks
        Task[] tasks = {
                new Task(3, "Respond to emails"),
                new Task(5, "Fix server issue"),
                new Task(2, "Update documentation"),
                new Task(4, "Prepare meeting agenda"),
                new Task(1, "Clean workspace")
        };
        ArrayList<Task> result = new ArrayList<>();

        // Initialize the min-heap
        MinHeap minHeap = new MinHeap(tasks.length);

        // Build the heap
        minHeap.buildHeap(tasks);

        // Extract tasks in order of priority (highest priority first)
        System.out.println("Tasks sorted by priority (highest first):");
        while (!minHeap.isEmpty()) {
            Task minTask = minHeap.extractMin();
            result.addFirst(minTask);
        }
        System.out.println(result);
    }
}

class Question4{
    public static void main(String[] args) {
        // Example input: list of tasks
        Task[] tasks = {
                new Task(3, "Respond to emails"),
                new Task(5, "Fix server issue"),
                new Task(2, "Update documentation"),
                new Task(4, "Prepare meeting agenda"),
                new Task(1, "Clean workspace")
        };

        Task[] heap = new Task[5];

        // Initialize the min-heap
        MinHeap minHeap = new MinHeap(tasks.length);

        // Insert all tasks into the heap
        for (Task task : tasks) {
            minHeap.insert(task);
        }
        System.out.println(minHeap);
    }

}
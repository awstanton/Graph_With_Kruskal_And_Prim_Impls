
package javaapp3;

import java.util.ArrayList;

public class minPriorityQueue<T extends Comparable<T>> {
    protected int heapSize;
    protected ArrayList<T> minHeap;
    
    public minPriorityQueue() {
        heapSize = 0;
        minHeap = new ArrayList<>();
    }
    
    public minPriorityQueue(ArrayList<T> arr) {
        heapSize = arr.size();
        minHeap = new ArrayList<>(arr);
        
        for (int index = (heapSize / 2) - 1; index >= 0; --index)
            minHeapify(index);
    }
    
    public T heapExtractMin() {
        if (heapSize < 1)
            return null;
        T min = minHeap.get(0);
        minHeap.set(0, minHeap.get(--heapSize));
        minHeap.set(heapSize, min);
        minHeapify(0);
        return min;
    }
    protected void minHeapify(int rootIndex) {
        int leftIndex = left(rootIndex);
        int rightIndex = right(rootIndex);
        int smallerIndex;
        
        if (leftIndex < heapSize && minHeap.get(leftIndex).compareTo(minHeap.get(rootIndex)) < 0)
            smallerIndex = leftIndex;
        else
            smallerIndex = rootIndex;
        if (rightIndex < heapSize && minHeap.get(rightIndex).compareTo(minHeap.get(smallerIndex)) < 0)
            smallerIndex = rightIndex;
        
        if (smallerIndex != rootIndex) {
            T t = minHeap.get(rootIndex);
            minHeap.set(rootIndex, minHeap.get(smallerIndex));
            minHeap.set(smallerIndex, t);
            minHeapify(smallerIndex);
        }
    }
    public void heapDecreaseKey(int index, T key) {
        if (minHeap.get(index) != null && key.compareTo(minHeap.get(index)) > 0) {
            System.out.println("Error: trying to increase value");
        }
        else {
            minHeap.set(index, key);
            while (index > 0 && minHeap.get(parent(index)).compareTo(minHeap.get(index)) > 0) {
                T t = minHeap.get(index);
                minHeap.set(index, minHeap.get(parent(index)));
                minHeap.set(parent(index), t);
                index = parent(index);
             }
        }
    }
    public T heapMinimum() {
        if (heapSize > 0)
            return minHeap.get(0);
        else
            return null;
    }
    public void minHeapInsert(T t) {
        if (heapSize < minHeap.size()) {
            heapDecreaseKey(heapSize++, t);
        }
        else if (heapSize == minHeap.size()) {
            minHeap.add(t);
            heapDecreaseKey(heapSize++, t);
        }
        else {
            System.out.println("Error: heapSize cannot be larger than ArrayList size");
        }
    }
    
    public int parent(int index) {
        return ((index + 1) / 2) - 1;
    }
    public int left(int index) {
        return ((index + 1) * 2) - 1;
    }
    public int right(int index) {
        return ((index + 1) * 2);
    }
    public boolean isEmpty() {
        return (heapSize == 0);
    }
}


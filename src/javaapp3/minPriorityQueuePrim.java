
package javaapp3;

import java.util.ArrayList;
import javaapp3.AdjListMstPrim.*;

public class minPriorityQueuePrim<T extends Comparable<T>> extends minPriorityQueue<AdjListMstPrim<T>.primElement> {

    public minPriorityQueuePrim(ArrayList<AdjListMstPrim<T>.primElement> arr) {
        super(arr);
    }
    
    public primElement heapExtractMin() {
        if (heapSize < 1)
            return null;
        primElement min = minHeap.get(0);
        minHeap.set(0, minHeap.get(--heapSize));
        minHeap.set(heapSize, min);
        
        minHeap.get(heapSize).updateQueueIndex(heapSize);
        minHeap.get(0).updateQueueIndex(0);
        
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
            primElement t = minHeap.get(rootIndex);
            minHeap.set(rootIndex, minHeap.get(smallerIndex));
            minHeap.set(smallerIndex, t);
            
            minHeap.get(rootIndex).updateQueueIndex(rootIndex);
            minHeap.get(smallerIndex).updateQueueIndex(smallerIndex);
            
            minHeapify(smallerIndex);
        }
    }
    
    public void heapDecreaseKey(int index, primElement key) {
        if (minHeap.get(index) != null && key.compareTo(minHeap.get(index)) > 0) {
            System.out.println("Error: trying to increase value");
        }
        else {
            minHeap.set(index, key);
            while (index > 0 && minHeap.get(parent(index)).compareTo(minHeap.get(index)) > 0) {
                primElement t = minHeap.get(index);
                minHeap.set(index, minHeap.get(parent(index)));
                minHeap.set(parent(index), t);
                
                minHeap.get(index).updateQueueIndex(index);
                minHeap.get(parent(index)).updateQueueIndex(parent(index));
                
                index = parent(index);
             }
        }
    }
    
    public void minHeapInsert(primElement t) {
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
}

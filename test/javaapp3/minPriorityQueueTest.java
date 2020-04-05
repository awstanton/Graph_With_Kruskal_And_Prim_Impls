/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapp3;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Andrew
 */
public class minPriorityQueueTest {
    
    ArrayList<Integer> nums;
    
    public minPriorityQueueTest() {
        nums = new ArrayList<>(10);
        nums.add(20);
        nums.add(9);
        nums.add(8);
        nums.add(43);
        nums.add(31);
        nums.add(18);
        nums.add(17);
        nums.add(4);
        nums.add(50);
        nums.add(49);
    }
/*
    @Test
    public void testConstructor() {
        System.out.println("constructor");
        minPriorityQueue<Integer> Q = new minPriorityQueue<>(nums);
        for (int i = 0; i < Q.heapSize; ++i) {
            assertTrue(Q.parent(Q.minHeap.get(i)) <= Q.minHeap.get(i));
            System.out.println("element at index i = " + Q.minHeap.get(i));
        }
    }
    

    @Test
    public void testHeapExtractMin() {
        System.out.println("heapExtractMin");
        minPriorityQueue<Integer> Q = new minPriorityQueue<>(nums);
        System.out.println(Q.minHeap);
        Integer result1 = Q.heapExtractMin();
        assertEquals((Integer) 4, result1);
        System.out.println(Q.minHeap);
        Integer result2 = Q.heapExtractMin();
        assertEquals((Integer) 8, result2);
        System.out.println(Q.minHeap);
        Integer result3 = Q.heapExtractMin();
        assertEquals((Integer) 9, result3);
        System.out.println(Q.minHeap);
        Integer result4 = Q.heapExtractMin();
        assertEquals((Integer) 17, result4);
        System.out.println(Q.minHeap);
        Integer result5 = Q.heapExtractMin();
        assertEquals((Integer) 18, result5);
        System.out.println(Q.minHeap);
        Integer result6 = Q.heapExtractMin();
        assertEquals((Integer) 20, result6);
        System.out.println(Q.minHeap);
        Integer result7 = Q.heapExtractMin();
        assertEquals((Integer) 31, result7);
        System.out.println(Q.minHeap);
        Integer result8 = Q.heapExtractMin();
        assertEquals((Integer) 43, result8);
        System.out.println(Q.minHeap);
        Integer result9 = Q.heapExtractMin();
        System.out.println(Q.minHeap);
        assertEquals((Integer) 49, result9);
        Integer result10 = Q.heapExtractMin();
        System.out.println(Q.minHeap);
        assertEquals((Integer) 50, result10);
        
    }
    
    @Test
    public void testHeapDecreaseKey() {
        System.out.println("heapDecreaseKey");
        minPriorityQueue<Integer> Q = new minPriorityQueue<>(nums);
        Q.heapDecreaseKey(4, 7);
        assertEquals((Integer) 4, Q.minHeap.get(0));
        assertEquals((Integer) 7, Q.minHeap.get(1));
        assertEquals((Integer) 8, Q.minHeap.get(2));
        assertEquals((Integer) 20, Q.minHeap.get(3));
        assertEquals((Integer) 9, Q.minHeap.get(4));
        assertEquals((Integer) 18, Q.minHeap.get(5));
        assertEquals((Integer) 17, Q.minHeap.get(6));
        assertEquals((Integer) 43, Q.minHeap.get(7));
        assertEquals((Integer) 50, Q.minHeap.get(8));
        assertEquals((Integer) 49, Q.minHeap.get(9));
    }


    @Test
    public void testMinHeapInsert1() {
        System.out.println("minHeapInsert1");
        minPriorityQueue<Integer> Q = new minPriorityQueue<>(nums);
        Q.minHeapInsert(29);
        assertEquals((Integer) 4, Q.minHeap.get(0));
        assertEquals((Integer) 9, Q.minHeap.get(1));
        assertEquals((Integer) 8, Q.minHeap.get(2));
        assertEquals((Integer) 20, Q.minHeap.get(3));
        assertEquals((Integer) 29, Q.minHeap.get(4));
        assertEquals((Integer) 18, Q.minHeap.get(5));
        assertEquals((Integer) 17, Q.minHeap.get(6));
        assertEquals((Integer) 43, Q.minHeap.get(7));
        assertEquals((Integer) 50, Q.minHeap.get(8));
        assertEquals((Integer) 49, Q.minHeap.get(9));
        assertEquals((Integer) 31, Q.minHeap.get(10));
        Q.minHeapInsert(1);
        assertEquals((Integer) 1, Q.minHeap.get(0));
        assertEquals((Integer) 9, Q.minHeap.get(1));
        assertEquals((Integer) 4, Q.minHeap.get(2));
        assertEquals((Integer) 20, Q.minHeap.get(3));
        assertEquals((Integer) 29, Q.minHeap.get(4));
        assertEquals((Integer) 8, Q.minHeap.get(5));
        assertEquals((Integer) 17, Q.minHeap.get(6));
        assertEquals((Integer) 43, Q.minHeap.get(7));
        assertEquals((Integer) 50, Q.minHeap.get(8));
        assertEquals((Integer) 49, Q.minHeap.get(9));
        assertEquals((Integer) 31, Q.minHeap.get(10));
        assertEquals((Integer) 18, Q.minHeap.get(11));
        Q.minHeapInsert(100);
        assertEquals((Integer) 1, Q.minHeap.get(0));
        assertEquals((Integer) 9, Q.minHeap.get(1));
        assertEquals((Integer) 4, Q.minHeap.get(2));
        assertEquals((Integer) 20, Q.minHeap.get(3));
        assertEquals((Integer) 29, Q.minHeap.get(4));
        assertEquals((Integer) 8, Q.minHeap.get(5));
        assertEquals((Integer) 17, Q.minHeap.get(6));
        assertEquals((Integer) 43, Q.minHeap.get(7));
        assertEquals((Integer) 50, Q.minHeap.get(8));
        assertEquals((Integer) 49, Q.minHeap.get(9));
        assertEquals((Integer) 31, Q.minHeap.get(10));
        assertEquals((Integer) 18, Q.minHeap.get(11));
        assertEquals((Integer) 100, Q.minHeap.get(12));
        
    }

    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        minPriorityQueue<Integer> Q = new minPriorityQueue<>();
        assertEquals(true, Q.isEmpty());
        Q.minHeapInsert(50);
        assertEquals(false, Q.isEmpty());
        Q.heapExtractMin();
        assertEquals(true, Q.isEmpty());
        Q.minHeapInsert(50);
        Q.minHeapInsert(100);
        Q.minHeapInsert(25);
        Q.minHeapInsert(50);
        assertEquals(false, Q.isEmpty());
        Q.heapExtractMin();
        assertEquals(false, Q.isEmpty());
        Q.heapExtractMin();
        assertEquals(false, Q.isEmpty());
        Q.heapExtractMin();
        assertEquals(false, Q.isEmpty());
        Q.heapExtractMin();
        assertEquals(true, Q.isEmpty());
    }
*/    
}

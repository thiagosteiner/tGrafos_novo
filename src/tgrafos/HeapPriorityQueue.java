package tgrafos;

import java.util.Collection;
import java.util.Iterator;
import java.util.Queue;




public class HeapPriorityQueue<T extends Comparable<T>> implements Queue<T>{
    private int curSize;
    private final int maxSize;
    private final T data[];

    public HeapPriorityQueue(int size) {
        this.maxSize = size;
        this.curSize = 0;
        this.data = (T[])new Comparable<?>[size];
    }
    
    public T get(){
        if (curSize == 0) throw new RuntimeException("Queue is empty!");

        T max = data[0]; data[0] = data[--curSize];
        heapify(0);
        return max;
    }
    
    public void put(T t){
        if (curSize == maxSize) throw new RuntimeException("Queue is full!");
      
        int i = curSize++, p; data[i] = t;
        while ( i > 0 && compare(p = i >> 1, i) < 0) {
            swap(i, p);
            i = p;
        }
    }
    
    @Override
    public int size() {return curSize;}

    public int capacity() {return maxSize;}
    
    private int compare(int i, int j) {
        return data[i].compareTo(data[j]);
    }
    
    private void swap(int i, int j) {
        T t = data[i]; data[i] = data[j]; data[j] = t;
    }
    
    private void heapify(int i) {
        int max, lc = i << 1, rc = lc + 1;
        
        if (lc < curSize && compare(lc, i) > 0) max = lc; else max = i;
        
        if (rc < curSize && compare(rc, max) > 0) max = rc;
        
        if (max != i) { swap(i, max); heapify(max); }
    }

    @Override
    public boolean add(T e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean offer(T e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T poll() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T element() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public T peek() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean containsAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean addAll(Collection<? extends T> clctn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean retainAll(Collection<?> clctn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

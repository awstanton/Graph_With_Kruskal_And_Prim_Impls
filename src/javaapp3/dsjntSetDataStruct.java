
package javaapp3;

import java.util.HashMap;

public class dsjntSetDataStruct<T> {
    
    private HashMap<T, dsjntSetElmnt<T>> objectToElem;
    
    public dsjntSetDataStruct() {
        objectToElem = new HashMap<>();
    }
    
    public void makeset(T t) {
        objectToElem.put(t, new dsjntSetElmnt<>(t));
    }
    public T findSet(T t) {
        dsjntSetElmnt<T> elem = objectToElem.get(t);
        dsjntSetElmnt<T> temp = elem;
        dsjntSetElmnt<T> temp2 = temp;
        
        while (temp != temp.parent)
            temp = temp.parent;
        
        while (elem != temp) {
            temp2 = elem.parent;
            elem.parent = temp;
            elem = temp2;
        }
        return temp.element;
    }
    public void union(T ob1, T ob2) {
        T rep1 = findSet(ob1);
        T rep2 = findSet(ob2);
        dsjntSetElmnt<T> elem1 = objectToElem.get(rep1);
        dsjntSetElmnt<T> elem2 = objectToElem.get(rep2);
        if (elem1.rank > elem2.rank) {
            elem2.parent = elem1;
        }
        else {
            elem1.parent = elem2;
            if (elem1.rank == elem2.rank)
                ++elem2.rank;
        }
    }
    private class dsjntSetElmnt<T> {
        private int rank;
        private dsjntSetElmnt<T> parent;
        private T element;
        dsjntSetElmnt() {
            rank = 0;
            parent = this;
            element = null;
        }
        dsjntSetElmnt(T t) {
            rank = 0;
            parent = this;
            element = t;
        }
    }
}

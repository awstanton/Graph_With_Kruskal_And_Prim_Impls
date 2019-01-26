/* Reference:
https://stackoverflow.com/questions/420223/what-is-the-difference-between-compare-and-compareto
https://stackoverflow.com/questions/38186332/comparable-cannot-be-converted-to-t1

*/

package javaapp3;

// note use of keyword extends intead of implements, it means the same as implements here
class TreeElement<T extends Comparable<T>> implements Comparable<TreeElement<T>> {
    T element;
    TreeElement<T> parent;
    TreeElement<T> left;
    TreeElement<T> right;
    @Override // what is this for?
    public int compareTo(TreeElement<T> t) {
        return (this.element).compareTo(t.element);
    }
    public TreeElement() {
        element = null;
        parent = null;
        left = null;
        right = null;
    }
    public TreeElement(T t) {
        element = t;
        parent = null;
        left = null;
        right = null;
    }
}

public class Tree<T extends Comparable<T>> extends DataSet<T> {
    TreeElement<T> root;
    public int compare(T t1, T t2) {
        return t1.compareTo(t2);
    }
    public Tree() {
        root = null;
    }
    public Tree(T element) {
        root = new TreeElement(element);
    }
    public Tree(TreeElement<T> element) {
        root = element;
    }
    
    // Specify what behavior is on failed insert, think of exceptions and how to handle them
    @Override
    public boolean insert(T element) {
        System.out.println(element);
        if (element == null) {
            return false;
        }
        if (root == null) {
            root = new TreeElement(element);
	}
	else {
            TreeElement<T> temp = root;
            boolean done = false;
            while (!done) {
		if (compare(element, temp.element) <= 0) {
                    if (temp.left == null) {
			temp.left = new TreeElement(element);
                        temp.left.parent = temp;
			done = true;
                    }
                    else {
			temp = temp.left;
                    }
		}
		else {
                    if (temp.right == null) {
			temp.right = new TreeElement(element);
                        temp.right.parent = temp;
			done = true;
                    }
                    else {
                        temp = temp.right;
                    }
		}
            }
	}
	return true;
    }
    @Override
    public T search(T element) {
        if (root == null || element == null) {
            return null;
        }
        else {
            TreeElement<T> temp = root;
            while (temp != null) {
                if (compare(element, temp.element) == 0) {
                    return temp.element;
                }
                else if (compare(element, temp.element) < 0) {
                    temp = temp.left;
                }
                else {
                    temp = temp.right;
                }
            }
            return null;
        }
    }
    // Specify what behavior is on failed insert, think of exceptions and how to handle them
    @Override
    public boolean delete(T element) {
        if (root == null || element == null) {
            return false;
        }
        else {
            TreeElement<T> temp1 = root;
            while (temp1 != null) {
                if (compare(element, temp1.element) == 0) {
                    break;
                }
                else if (compare(element, temp1.element) < 0) {
                    temp1 = temp1.left;
                }
                else {
                    temp1 = temp1.right;
                }
            }
            if (temp1 == null) {
                return false;
            }
            else if (temp1.left != null && temp1.right != null) {
                TreeElement<T> temp2 = temp1.left;
                while (temp2.right != null) {
                    temp2 = temp2.right;
                }
                if (temp1.left != temp2) {
                    temp2.parent.right = temp2.left;
                    temp2.left.parent = temp2.parent;
                    if (temp1.parent.right == temp1)
                        temp1.parent.right = temp2;
                    else
                        temp1.parent.left = temp2;
                    temp2.parent = temp1.parent;
                    temp2.left = temp1.left;
                    temp1.left.parent = temp2;
                    temp2.right = temp1.right;
                    temp1.right.parent = temp2;
                }
                else {
                    if (temp1.parent.right == temp1)
                        temp1.parent.right = temp2;
                    else
                        temp1.parent.left = temp2;
                    temp2.parent = temp1.parent;
                    temp2.right = temp1.right;
                    temp1.right.parent = temp2;
                }
            }
            else if (temp1.left != null) {
                if (temp1.parent.right == temp1)
                    temp1.parent.right = temp1.left;
                else
                    temp1.parent.left = temp1.left;
                temp1.left.parent = temp1.parent;
            }
            else if (temp1.right != null) {
                if (temp1.parent.right == temp1)
                    temp1.parent.right = temp1.right;
                else
                    temp1.parent.left = temp1.right;
                temp1.right.parent = temp1.parent;
            }
            else {
                if (temp1.parent.right == temp1)
                    temp1.parent.right = null;
                else
                    temp1.parent.left = null;
            }
            return true;
            // at this point, nothing should refer to the "deleted" node so it should hopefully be garbage collected
        }
    }
}




// later on, work on better way of constructing these objects
    //Tree() {
    //    root = null;
    //}
    //Tree(T t) { 
    //    root = t;
    //}
/*
    public static void createTree() {
        Tree t = new Tree();
        t.elements = new T[1];
        t.root = elements[0];
        root.left = null;
        root.right = null;
    }
*/
/* Reference:
https://stackoverflow.com/questions/420223/what-is-the-difference-between-compare-and-compareto
https://stackoverflow.com/questions/38186332/comparable-cannot-be-converted-to-t1

*/

package javaapp3;


public class Tree<T extends Comparable<T>> implements SetInf<T> {
    private TreeElement<T> root;

    public Tree() {
        root = null;
    }
    public Tree(T element) {
        root = new TreeElement<>(element);
    }
    
    // Specify what behavior is on failed insert, think of exceptions and how to handle them
    @Override
    public boolean insert(T element) {
        System.out.println(element);
        if (element == null) {
            return false;
        }
        if (root == null) {
            root = new TreeElement<>(element);
	}
	else {
            TreeElement<T> temp = root;
            boolean done = false;
            while (!done) {
		if (element.compareTo(temp.element) < 0) {
                    if (temp.left == null) {
			temp.left = new TreeElement<>(element);
                        temp.left.parent = temp;
			done = true;
                    }
                    else {
			temp = temp.left;
                    }
		}
		else if (element.compareTo(temp.element) > 0) {
                    if (temp.right == null) {
			temp.right = new TreeElement<>(element);
                        temp.right.parent = temp;
			done = true;
                    }
                    else {
                        temp = temp.right;
                    }
		}
                else {
                    return false; // duplicate element not allowed
                }
            }
	}
	return true;
    }

    @Override
    public boolean search(T element) {
        if (root == null || element == null) {
            return false;
        }
        else {
            TreeElement<T> temp = root;
            while (temp != null) {
                if (element.compareTo(temp.element) == 0)
                    return true;
                else if (element.compareTo(temp.element) < 0)
                    temp = temp.left;
                else
                    temp = temp.right;
            }
            return false;
        }
    }

    @Override
    public boolean delete(T element) {
        if (root == null || element == null) {
            return false;
        }
        else {
            TreeElement<T> temp1 = root;
            while (temp1 != null) {
                if (element.compareTo(temp1.element) == 0)
                    break;
                else if (element.compareTo(temp1.element) < 0)
                    temp1 = temp1.left;
                else
                    temp1 = temp1.right;
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
    
    private class TreeElement<T extends Comparable<T>> {
        private T element;
        private TreeElement<T> parent;
        private TreeElement<T> left;
        private TreeElement<T> right;

        private TreeElement() {
            element = null;
            parent = null;
            left = null;
            right = null;
        }
        private TreeElement(T t) {
            element = t;
            parent = null;
            left = null;
            right = null;
        }
    }
}



// ideas:
// add get method to get the element with the specified value
// later on, work on better way of constructing these objects
/*
    public static void createTree() {
        Tree t = new Tree();
        t.elements = new T[1];
        t.root = elements[0];
        root.left = null;
        root.right = null;
    }
*/
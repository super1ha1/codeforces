package problem_set;

/**
 * Simple Tree in Java
 */
public class SimpleTree<E extends Comparable>  {
    private E value;
    private SimpleTree<E> left;
    private SimpleTree<E> right;

    public boolean search(E toFind){
        if (toFind.equals(value))
            return true;
         if( toFind.compareTo(value) < 0 &&  left != null )
            return left.search(toFind);
        return right != null && right.search(toFind);
    }

    public void insert(E toInsert){
        if( toInsert.compareTo(value) < 0){
            if( left != null)
                left.insert(toInsert);
            else left = new SimpleTree<E>(toInsert, null, null);
        }else {
            if ( right != null)
                right.insert(toInsert);
            else
                right = new SimpleTree<E>(toInsert, null , null);
        }
    }
    public SimpleTree(E value, SimpleTree<E> left, SimpleTree<E> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public SimpleTree<E> getLeft() {
        return left;
    }

    public void setLeft(SimpleTree<E> left) {
        this.left = left;
    }

    public SimpleTree<E> getRight() {
        return right;
    }

    public void setRight(SimpleTree<E> right) {
        this.right = right;
    }
}

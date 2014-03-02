/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package business;

/**
 *
 * @author ranjana
 */
import java.util.NoSuchElementException;

public class BinarySearchTree<T extends Comparable<? super T>> {

  private Entry<T> root;

  public BinarySearchTree() {
    root = null;
  }

  public static<T extends Comparable<? super T>> BinarySearchTree<T> createTestTree() {
    return new BinarySearchTree<T>();
  }

  public void insert(T value) {
    root = insert(value, root);
  }


  public void remove(T value) {
    root = remove(value, root);
  }

  public boolean contains(T value) {
    return valueOf(find(value, root)) != null;
  }

  private T valueOf(Entry<T> entry) {
    return entry == null ? null : entry.element;
  }

  private Entry<T> insert(T value, Entry<T> entry) {
    if (entry == null)
      entry = new Entry<T>(value);
    else if (value.compareTo(entry.element) < 0)
      entry.left = insert(value, entry.left);
    else if (value.compareTo(entry.element) > 0)
      entry.right = insert(value, entry.right);
    else
      throw new RuntimeException("Duplicate Entry : " + value.toString());
    return entry;
  }

  private Entry<T> remove(T value, Entry<T> entry) {
    if (entry == null)
      throw new NoSuchElementException("Entry not found : " + value.toString());
    
    if (value.compareTo(entry.element) < 0)
      entry.left = remove(value, entry.left);
    else if (value.compareTo(entry.element) > 0)
      entry.right = remove(value, entry.right);
    else {
      // Entry found.
      if (entry.left != null && entry.right != null) {

        // Replace with in-order successor (the left-most child of the right subtree)
        entry.element = findMin(entry.right).element;
        entry.right = removeInorderSuccessor(entry.right);

        // Replace with in-order predecessor (the right-most child of the left subtree)
        // entry.element = findMax(entry.left).element;
        // entry.left = removeInorderPredecessor(entry.left);
      } else
        entry = (entry.left != null) ? entry.left : entry.right;
    }
    return entry;
  }

  private Entry<T> removeInorderSuccessor(Entry<T> entry) {
    if (entry == null)
      throw new NoSuchElementException();
    else if (entry.left != null) {
      entry.left = removeInorderSuccessor(entry.left);
      return entry;
    } else
      return entry.right;
  }

  private Entry<T> removeInorderPredecessor(Entry<T> entry) {
    if (entry == null)
      throw new NoSuchElementException();
    else if (entry.right != null) {
      entry.right = removeInorderPredecessor(entry.right);
      return entry;
    } else
      return entry.left;
  }

  private Entry<T> findMin(Entry<T> entry) {
    if (entry != null)
      while (entry.left != null)
        entry = entry.left;

    return entry;
  }

  private Entry<T> findMax(Entry<T> entry) {
    if (entry != null)
      while (entry.right != null)
        entry = entry.right;

    return entry;
  }

  private Entry<T> find(T value, Entry<T> entry) {
    while (entry != null) {
      if (value.compareTo(entry.element) < 0)
        entry = entry.left;
      else if (value.compareTo(entry.element) > 0)
        entry = entry.right;
      else
        return entry;
    }

    return null;
  }

  private void printInOrder(Entry<T> entry) {
    if (entry != null) {
      printInOrder(entry.left);
      System.out.println(entry.element);
      printInOrder(entry.right);
    }
  }

  public void printInOrder() {
    printInOrder(root);
  }

  private static class Entry<T extends Comparable<? super T>> {
    T element;
    Entry<T> left;
    Entry<T> right;

    Entry(T theElement) {
      element = theElement;
      left = right = null;
    }
  }

  private static class Test implements Comparable<Test> {
    private String value;

    public Test(String value) {
      this.value = value;
    }

        @Override
    public String toString() {
      return value;
    }

    @Override
    public int compareTo(Test o) {
      return this.value.compareTo(o.toString());
    }
  }

  private static class Test1 extends Test {
    public Test1(String value) {
      super(value);
    }
  }

  public static void main(String[] args) {
    BinarySearchTree tree = BinarySearchTree.createTestTree();
    int size = 20;

    //for (int i = 0; i <= size; i++) {
      //tree.insert(new Test1(String.valueOf(i)));
    //}
    tree.insert(new Test1("easas"));
    tree.insert(new Test1("aasff"));
    tree.insert(new Test1("c"));
    tree.insert(new Test1("b"));
    tree.insert(new Test1("acvncvn"));
    //tree.insert(new Test1("g"));
    //tree.remove(new Test1("10"));
    tree.remove(new Test1("c"));
    //tree.remove(new Test1(String.valueOf(20)));
    tree.printInOrder();
    System.out.println("Contains (b) : " + tree.contains(new Test1("b")));
    //System.out.println("Contains (11) : " + tree.contains(new Test1(String.valueOf(11))));
  }

}
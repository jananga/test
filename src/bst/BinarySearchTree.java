/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bst;

/**
 *
 * @author ranjana
 */
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import javax.swing.JOptionPane;

public class BinarySearchTree<T extends Comparable<? super T>> {

  private Entry<T> root;
  String[] sort;
  private static int size;
  static List myList = new ArrayList();

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
    {

    }
      
    return entry;
  }

  private Entry<T> remove(T value, Entry<T> entry) {
    if (entry == null)
        JOptionPane.showMessageDialog(null, "Entry not found");
    if (value.compareTo(entry.element) < 0)
      entry.left = remove(value, entry.left);
    else if (value.compareTo(entry.element) > 0)
      entry.right = remove(value, entry.right);
    else {
      // Entry found.
      if (entry.left != null && entry.right != null) {

        entry.element = findMin(entry.right).element;
        entry.right = removeInorderSuccessor(entry.right);

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
      //System.out.println(entry.element);
      myList.add(entry.element);
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
    //run_p(status);
  }

  public static List run_p(List data, String status)
  {
     
    BinarySearchTree tree = BinarySearchTree.createTestTree();
    size = data.size();

     if(status.equals("insert"))
     {
         String val = "";

    for(int i = 0;i<size;i++)
    {
    val = String.valueOf(data.get(i));
    tree.insert(new Test1(val));
    }
    
    tree.printInOrder();
     
    }

    else if(status.equals("delete"))
    {
         String val,val1,d_val,d_val1="";
         size = data.size();
         d_val = String.valueOf(data.get(0));
         d_val1 = d_val.toLowerCase();

    for(int i = 1;i<size;i++)
    {
    val = String.valueOf(data.get(i));
    val1 = val.toLowerCase();
    tree.insert(new Test1(val1));
   
    }

     tree.remove(new Test1(d_val1));
     tree.printInOrder();

     }

    else if(status.equals("search"))
    {
        size = data.size();
        String s_val = String.valueOf(data.get(0));
        for(int i = 1;i<size;i++)
    {
    
    String val = String.valueOf(data.get(i));
    tree.insert(new Test1(val));
    }
        tree.printInOrder();
        boolean val = tree.contains(new Test1(s_val));

        if(val)
        {
        myList.add("true");
        }

        else
        {
        myList.add("false");
        }

    }

    else if(status.equals("reorder"))
    {
    tree.printInOrder();
    }

    return myList;
  }

  public static List findall(List data, String search)
  {
    String org,chnge,cahnge_s ="";
    size = data.size();

    for(int i = 0;i<size;i++)
    {
    org = String.valueOf(data.get(i));
    chnge = org.toLowerCase();
    cahnge_s = search.toLowerCase();

    if(chnge.contains(cahnge_s))
    {
        myList.add(String.valueOf(i));
    }
   
    }

  return myList;
  }

}
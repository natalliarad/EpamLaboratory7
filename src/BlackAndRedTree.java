import java.util.Arrays;

/**
 * Black and Red Tree - a self-balancing binary search tree. It has methods: {@link #getSize()},
 * {@link #add(int)},  {@link #getNode(int)}, {@link #deleteNode(int)}, {@link #toString()}, {@link
 * #fillTree(int[])}.
 *
 * @author Natallia Radaman
 * @since 03-2018
 */
public class BlackAndRedTree {

  private static final boolean RED = true;
  private static final boolean BLACK = false;
  private Node root;
  private int size = 0;

  /**
   * Return the size of the tree
   */
  public int getSize() {
    return size;
  }

  /**
   * Add new value in the tree
   *
   * @param key - new value for adding
   */
  public void add(int key) {
    root = add(root, key, false);
    root.treeNodeColor = BLACK;
  }

  /**
   * Add new leaf in the tree, compare and change boolean color values.
   *
   * @param node - leaf from which the inserts begin.
   * @param key - value for the adding.
   * @param flag - rotate the tree to the left or to the right.
   * @return node.
   */
  private Node add(Node node, int key, boolean flag) {
    if (node == null) {
      size++;
      return new Node(key, RED);
    }
    if (redColor(node.left) && redColor(node.right)) {
      swapColor(node);
    }
    int compareKeys = node.key - key;
    if (compareKeys > 0) {
      node.left = add(node.left, key, false);
      if (redColor(node) && redColor(node.left) && flag) {
        node = rotateRight(node);
      }
      if (redColor(node.left) && redColor(node.left.left)) {
        node = rotateRight(node);
      }
    } else if (compareKeys < 0) {
      node.right = add(node.right, key, true);
      if (redColor(node) && redColor(node.right) && !flag) {
        node = rotateLeft(node);
      }
      if (redColor(node.right) && redColor(node.right.right)) {
        node = rotateLeft(node);
      }
    }
    return node;
  }

  /**
   * Rotate tree leaves to the right
   */
  private Node rotateRight(Node node) {
    Node tempNode = node.left;
    node.left = tempNode.right;
    tempNode.right = node;
    tempNode.treeNodeColor = tempNode.right.treeNodeColor;
    tempNode.treeNodeColor = RED;
    return tempNode;
  }

  /**
   * Rotate tree leaves to the left
   */
  private Node rotateLeft(Node node) {
    Node tempNode = node.right;
    node.right = tempNode.left;
    tempNode.left = node;
    tempNode.treeNodeColor = tempNode.left.treeNodeColor;
    tempNode.left.treeNodeColor = RED;
    return tempNode;
  }

  /**
   * Check and change boolean value of the leaf's color
   *
   * @return color of the leaf value
   */
  private boolean redColor(Node node) {
    if (node == null) {
      return BLACK;
    } else {
      return node.treeNodeColor;
    }
  }

  /**
   * Change color of leaves in the node leaf and its right and left leaves.
   *
   * @param node - leaf for color change.
   */
  private void swapColor(Node node) {
    node.treeNodeColor = !node.treeNodeColor;
    node.left.treeNodeColor = !node.left.treeNodeColor;
    node.right.treeNodeColor = !node.right.treeNodeColor;
  }

  /**
   * Get node from the tree.
   *
   * @param key - value of the node.
   * @return node with a particular key.
   */
  public Node getNode(int key) {
    if (root == null) {
      System.out.println("There is no elements in the tree.");
      return null;
    }
    Node tempNode = root;
    while (tempNode != null) {
      if ((tempNode.key - key) > 0) {
        tempNode = tempNode.left;
      } else if ((tempNode.key - key) < 0) {
        tempNode = tempNode.right;
      } else {
        return tempNode;
      }
    }
    return null;
  }

  /**
   * Delete tne node with a particular key.
   *
   * @param key - value of the leaf, which has to be deleted.
   */
  public void deleteNode(int key) {
    if (getNode(key) == null) {
      return;
    }
    root = deleteNode(root, key);
    size--;
  }

  /**
   * Delete tne node with a particular key. Search starts from a specific node
   */
  private Node deleteNode(Node node, int key) {
    if (node.key - key > 0) {
      if (!redColor(node.left) && !redColor(node.left.left)) {
        node = moveRedLeft(node);
      }
      node.left = deleteNode(node.left, key);
    } else {
      if (redColor(node.left)) {
        node = rotateRight(node);
      }
      if (node.key == key && node.right == null) {
        return null;
      }
      if (!redColor(node.right) && !redColor(node.right.left)) {
        node = moveRedRight(node);
      }
      if (node.key == key) {
        Node tempNode = min(node.right);
        node.key = tempNode.key;
        node.right = deleteMin(node.right);
      } else {
        node.right = deleteNode(node.right, key);
      }
    }
    return balance(node);
  }

  /**
   * Change the color value in the particular node, check color of the descendants and rotate the
   * tree if necessary.
   */
  private Node moveRedLeft(Node node) {
    swapColor(node);
    if (redColor(node.right.left)) {
      node.right = rotateRight(node.right);
      node = rotateLeft(node);
      swapColor(node);
    }
    return node;
  }

  /**
   * Change the color value in the particular node, check color of the descendants and rotate the
   * tree if necessary.
   */
  private Node moveRedRight(Node node) {
    swapColor(node);
    if (redColor(node.right.left)) {
      node.right = rotateRight(node.right);
      node = rotateLeft(node);
      swapColor(node);
    }
    return node;
  }

  /**
   * A recursion function to navigate through tree nodes
   */
  private Node min(Node node) {
    if (node.left == null) {
      return node;
    } else {
      return min(node.left);
    }
  }

  private Node deleteMin(Node node) {
    if (node.left == null) {
      return null;
    }
    if (!redColor(node.left) && !redColor(node.left.left)) {
      node = moveRedLeft(node);
    }
    node.left = deleteMin(node.left);
    return balance(node);
  }

  /**
   * Balance tree in the particular node and its right and left leaves
   *
   * @param node - leaf which has to ba balanced
   * @return balanced node
   */
  private Node balance(Node node) {
    if (redColor(node.right)) {
      node = rotateLeft(node);
    }
    if (redColor(node.left) && redColor(node.left.left)) {
      node = rotateRight(node);
    }
    if (redColor(node.left) && redColor(node.right)) {
      swapColor(node);
    }
    return node;
  }

  /**
   * Print black and red tree with all levels of the tree
   */
  private void printBlackAndRedTree() {
    printTreeElement(root, 0);
  }

  /**
   * Print black and red tree beginning with a particular node
   *
   * @param node - leaf of the tree form which print begins.
   */
  private void printTreeElement(Node node, int levelOfTree) {
    StringBuilder builder = new StringBuilder();
    if (node != null) {
      for (int i = 0; i <= levelOfTree; i++) {
        builder.append("-");
      }
      builder.append(String.valueOf(node.key) + " " + (node.treeNodeColor ? "red" : "black"));
      System.out.println(builder);
      levelOfTree++;
      printTreeElement(node.left, levelOfTree);
      printTreeElement(node.right, levelOfTree);
    }
  }

  /**
   * Print black and red tree and size of the tree
   *
   * @return String value - size of the tree
   */
  public String toString() {
    printBlackAndRedTree();
    return "A size of the tree is " + String.valueOf(size);
  }


  /**
   * Inner class with node - leaf of the tree - instance.
   */
  private class Node {

    private int key;
    private boolean treeNodeColor;
    private Node left;
    private Node right;

    public Node(int key, boolean treeNodeColor) {
      this.key = key;
      this.treeNodeColor = treeNodeColor;
    }

    @Override
    public String toString() {
      return String.valueOf(key) + (treeNodeColor ? "red" : "black");
    }
  }

  /**
   * To fill tree with elements from the array with int values.
   *
   * @param array - int[] element for adding in the tree.
   * @return tree with elements from th e array.
   */
  public static BlackAndRedTree fillTree(int[] array) {
    BlackAndRedTree tree = new BlackAndRedTree();
    for (int i : array) {
      tree.add(i);
    }
    System.out.println(Arrays.toString(array));
    System.out.println(tree);
    return tree;
  }
}

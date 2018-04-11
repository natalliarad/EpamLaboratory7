import java.util.Arrays;

/**
 * Stack with the parameterized parameter. The stack size is variable and can increase if program
 * checks a file with a large number of parentheses. It has method: {@link
 * #addElementInStack(Object)}, {@link #getElementInStack()}, {@link #readTopElement()}, {@link
 * #isEmpty()}.
 *
 * @author Natallia Radaman
 * @since 03-2018
 */
public class StackUtil<T> {

  private final static int DEFAULT_CAPACITY = 16;
  private T[] arrayForStack;
  private int size;
  private int top;

  /**
   * A constructor that creates a stack of a given size
   *
   * @param size - capacity of the StackUtil
   */
  public StackUtil(int size) {
    this.size = size;
    arrayForStack = (T[]) new Object[size];
    top = -1;
  }

  /**
   * A constructor that creates a stack with the default capacity
   */
  public StackUtil() {
    this(DEFAULT_CAPACITY);
  }

  /**
   * Add element in the stackUtil
   *
   * @param element - value for adding
   */
  public void addElementInStack(T element) {
    confirmCapacity(size + 1);
    arrayForStack[++top] = element;
  }

  /**
   * Get an top element of the stackUtil
   */
  public T getElementInStack() {
    if (!isEmpty()) {
      return arrayForStack[top--];
    }
    return null;
  }

  /**
   * Read an top element of the stackUtil. The element stays on the top of the stackUtil.
   *
   * @return element from the top of the stack.
   */
  public T readTopElement() {
    if (!isEmpty()) {
      return arrayForStack[top];
    }
    return null;
  }

  /**
   * Check if the array is empty
   *
   * @return true - if the array is empty.
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Checks if there is enough capacity in the stackUtil. If it is not enough, then the capacity is
   * increased by one and a half times.
   */
  private void confirmCapacity(int capacity) {
    int oldCapacity = arrayForStack.length;
    if (oldCapacity < capacity) {
      int newCapacity = (oldCapacity * 3) / 2 + 1;
      arrayForStack = Arrays.copyOf(arrayForStack, newCapacity);
    }
  }
}
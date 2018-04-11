/**
 * Black and Red Tree - a self-balancing binary search tree. It has methods: {@link
 * #checkBrackets(String)}, {@link #getErrorCounter()}.
 *
 * @author Natallia Radaman
 * @since 03-2018
 */
public class BracketsCheck {

  private StackUtil<Brackets> stackUtil = new StackUtil<>();
  private int errorCounter = 0;

  /**
   * Check the correctness of the brackets in the content in the string format.
   */
  public void checkBrackets(String content) {
    for (int i = 0; i < content.length(); i++) {
      String strElement = content.substring(i, i + 1);
      switch (strElement) {
        case "(":
        case "{":
        case "[":
        case "<":
          Brackets.pushOpeningBracket(strElement, stackUtil);
          break;
        case ")":
        case "}":
        case "]":
        case ">":
          if (stackUtil.isEmpty()) {
            System.out.println("Error: " + strElement + "in the position - " + i);
            errorCounter++;
          }
          if (!Brackets.popCorrectClosingBracket(strElement, stackUtil)) {
            stackUtil.getElementInStack();
            System.out.println("Error: " + strElement + "in the position - " + i);
            errorCounter++;
          }
          break;
        default:
          break;
      }
    }
  }

  public int getErrorCounter() {
    return errorCounter;
  }
}

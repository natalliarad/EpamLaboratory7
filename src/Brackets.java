/**
 * A class that contains the specified parenthesis types. It has method: {@link
 * #pushOpeningBracket(String, StackUtil)}, {@link #popCorrectClosingBracket(String, StackUtil)}.
 *
 * @author Natallia Radaman
 * @since 03-2018
 */
public enum Brackets {
  OPENING_ROUND_BRACKET("("),
  CLOSING_ROUND_BRACKET(")"),
  OPENING_CURLY_BRACKET("{"),
  CLOSING_CURLY_BRACKET("}"),
  OPENING_SQUARE_BRACKET("["),
  CLOSING_SQUARE_BRACKET("]"),
  OPENING_TRIANGULAR_BRACKET("<"),
  CLOSING_TRIANGULAR_BRACKET(">");

  private String bracketAsString;

  Brackets(String bracketAsString) {
    this.bracketAsString = bracketAsString;
  }

  public String getBracketAsString() {
    return bracketAsString;
  }

  /**
   * Check the type of parentheses. If the parenthesis is of the opening type, then add it to the
   * stack.
   *
   * @param bracket - String value, bracket.
   * @param stack - stack in which add all brackets in the file.
   */
  public static void pushOpeningBracket(String bracket, StackUtil<Brackets> stack) {
    if (OPENING_CURLY_BRACKET.getBracketAsString().equals(bracket)) {
      stack.addElementInStack(OPENING_CURLY_BRACKET);
    }
    if (OPENING_SQUARE_BRACKET.getBracketAsString().equals(bracket)) {
      stack.addElementInStack(OPENING_SQUARE_BRACKET);
    }
    if (OPENING_ROUND_BRACKET.getBracketAsString().equals(bracket)) {
      stack.addElementInStack(OPENING_ROUND_BRACKET);
    }
    if (OPENING_TRIANGULAR_BRACKET.getBracketAsString().equals(bracket)) {
      stack.addElementInStack(OPENING_TRIANGULAR_BRACKET);
    }
  }

  /**
   * Check whether the type of the closing parenthesis matches the opening bracket that is currently
   * at the top of the stack. If the parentheses are of the same type, then the opening bracket is
   * removed from the stack.
   *
   * @param bracket - closing bracket.
   * @param stack - stack in which add all brackets in the file.
   * @return true if the brackets are the same type, false - if not.
   */
  public static boolean popCorrectClosingBracket(String bracket, StackUtil<Brackets> stack) {
    if (CLOSING_CURLY_BRACKET.getBracketAsString().equals(bracket)) {
      if (stack.readTopElement() == OPENING_CURLY_BRACKET) {
        stack.getElementInStack();
        return true;
      }
    }
    if (CLOSING_SQUARE_BRACKET.getBracketAsString().equals(bracket)) {
      if (stack.readTopElement() == OPENING_SQUARE_BRACKET) {
        stack.getElementInStack();
        return true;
      }
    }
    if (CLOSING_ROUND_BRACKET.getBracketAsString().equals(bracket)) {
      if (stack.readTopElement() == OPENING_ROUND_BRACKET) {
        stack.getElementInStack();
        return true;
      }
    }
    if (CLOSING_TRIANGULAR_BRACKET.getBracketAsString().equals(bracket)) {
      stack.getElementInStack();
      return true;
    }
    return false;
  }
}

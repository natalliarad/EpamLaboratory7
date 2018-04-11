public class EntryPoint {

  public static void main(String[] args) {
    String fileContent = "";
    try {
      fileContent = FromFileReader.readFromFile("task_brackets.rtf");
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
    }
    if (fileContent.length() > 0) {
      System.out.println("Example of the BracketChecker\n\n");
      BracketsCheck check = new BracketsCheck();
      check.checkBrackets(fileContent);
      System.out.println("In your file there is " + check.getErrorCounter() + " errors.\n\n");
    }

    System.out.println("\nExample of the Black and red tree\n");
    int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
    int[] shuffleArray = new int[]{135, 1, 434, 9, 228, 1117, 23, 33, 8, 7, 10};
    BlackAndRedTree tree1 = BlackAndRedTree.fillTree(array);
    BlackAndRedTree tree2 = BlackAndRedTree.fillTree(shuffleArray);
    tree2.deleteNode(1);
    System.out.println(tree2.getSize() + " - new size after deleting 1 element");
  }
}

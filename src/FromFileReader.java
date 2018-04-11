import java.io.BufferedReader;
import java.io.FileReader;

/**
 * A helper class for reading information from a file. It has method: {@link
 * #readFromFile(String)}.
 *
 * @author Natallia Radaman
 * @since 03-2018
 */
public class FromFileReader {

  /**
   * Open a file, read information from it, and return it in a string format
   *
   * @param fileName - path to the file.
   * @return content form the file in the String format
   * @throws Exception - in a case of the invalid file path.
   */
  public static String readFromFile(String fileName) throws Exception {
    String fileContent;
    fileContent = "";
    String line;

    try {
      FileReader fileReader = new FileReader(fileName);
      BufferedReader bufferedReader = new BufferedReader(fileReader);
      while ((line = bufferedReader.readLine()) != null) {
        fileContent = fileContent + line;
      }
    } catch (Exception ex) {
      System.out.println(ex.getMessage());
      System.out.println("Invalid file path was entered. Program is exited.\n");
      System.exit(0);
    }
    return fileContent;
  }
}

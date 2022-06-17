import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class Main {
  public static void main(String[] args) {
    InputParser inputParser = new InputParser();
    try {
      BufferedReader br = new BufferedReader(new FileReader("input.txt"));
      String inputString;
			while ((inputString = br.readLine()) != null) {
				inputParser.parseTextInput(inputString.trim());
			}
    } catch(IOException e) {
      System.out.println("Oops! Error in reading the input from file.");
      e.printStackTrace();
    }
  }
}

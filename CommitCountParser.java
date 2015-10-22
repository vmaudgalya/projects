/********************************************************************************************
*
* @author Varun Maudgalya
* @email vmaudgalya@gmail.com
*
* [Instructions]
*  0. Go into the git directory
*  1. Run this command in your terminal: 
*  git log --author='GITHUB_USER.NAME_HERE' --oneline --shortstat > out.txt
*
*  2. Compile the source code: javac CommitCountParser.java
*
*  3. Run the class file: java CommitCountParser
*
* [TODO]
*  TODO #1: Figure out how to use getRuntime.exec(command) to run unix command from Java.
*  TODO #2: Query user to find github user.name to make viewing the source code unnecessary.
*
********************************************************************************************/

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CommitCountParser {
  public static void main(String[] args) {
    try (BufferedReader br = new BufferedReader(new FileReader("out.txt"))) {
      String line = null;
      String[] words = null;
      int insertions = 0, deletions = 0;
      while ((line = br.readLine()) != null) {
        words = line.split(" ");
        if ((words.length > 2) && (words[2].equals("files") || words[2].equals("file"))) {
          if (words.length > 3) insertions += Integer.parseInt(words[4]);
          if (words.length > 6) deletions += Integer.parseInt(words[6]);
        }
      }
      printOutput(insertions, deletions);
    } catch (IOException error) {
      System.out.println("Error: " + error.getMessage());
    }
  }

  public static void printOutput(int insertions, int deletions) {
    System.out.printf("Total insertions: %d\n", insertions);
    System.out.printf("Total deletions: %d\n", deletions);
    System.out.printf("Total analyzed: %d\n", insertions + deletions);
  }

}

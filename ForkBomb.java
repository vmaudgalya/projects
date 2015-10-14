import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
public class ForkBomb {  
  public static void main(String[] args) {
    ForkBomb fb = new ForkBomb();
    String name = fb.getClass().getName() + "1";
    try {
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(name + ".java")));
      String program = "import java.io.BufferedWriter;" + 
                  "import java.io.FileWriter;" + 
                  "import java.io.IOException;" + 
                  "import java.io.PrintWriter;" + 
                  "public class " + name + " {  " +
  "public static void main(String[] args) {" +
    name + " fb = new "+name+"();" +
    "String name = fb.getClass().getName() + \"1\";" +
    "try {" +
      "PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(name + \".java\")));" +
    "out.println(\"\");" +
    "} catch (IOException e) {" +
      "e.printStackTrace();" +
   " }"+
  "}"+
"}";
      System.out.println(program);
      out.println(program);
      out.flush();
      out.close();

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

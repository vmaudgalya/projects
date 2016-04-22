/*
 * 
 * @author Varun Maudgalya
 * Some interesting benchmarks
 * 
 */
public class Search {

  /*
   * Interpolation Search
   * O(log(logn)) running time
   * Useful when you know your data is coming from a uniform distribution
   * 
   * y = mx + b
   * y = (y2-y1/x2-x1) * (x-x1) + y1
   * index = (maxIndex-minIndex/a[maxIndex]-a[minIndex]) * (target - a[minIndex]) + minIndex
   * 
   */
  private static int interpolationSearch(int[] a, int target) {
    if (a == null || a.length == 0) return -1;
    int low = 0;
    int high = a.length-1;
    int index = 0;
    while (a[low] < target && a[high] > target) {
      index = (((high-low)/(a[high]-a[low])) * (target - a[low])) + low;
      if (a[index] < target) {
        low = index+1;
      } else if (a[index] > target) {
        high = index-1;
      } else {
        return index;
      }
    }
    return (a[index] == target ? index : -1);
  }
  
  /*
   * Binary Search
   * O(logn) running time
   * 
   * 
   */
  private static int binarySearch(int[] a, int target) {
    int low = 0;
    int high = a.length - 1;
    int mid = 0;
    while (low <= high) {
      mid = low + (high-low)/2; // Avoid integer overflow
      if (a[mid] > target) {
        high = mid-1;
      } else if (a[mid] < target) {
        low = mid+1;
      } else {
        return mid;
      }
    }
    return -1;
  }
  
  
  private static void testSearch(int[] a, int target, String search) {
    if (search.equals("binary")) {
      long start = System.nanoTime();
      System.out.println(binarySearch(a, target));
      long end = System.nanoTime();
      System.out.println("Binary Search took about " + (end-start) + " nanoseconds");
    } else {
      long start = System.nanoTime();
      System.out.println(interpolationSearch(a, target));
      long end = System.nanoTime();
      System.out.println("Interpolation Search took about " + (end-start) + " nanoseconds");
    }
  }
  
  public static void main(String[] args) {
    int[] a = new int[1000000];
    for (int i = 0; i < a.length; i++) a[i] = i+10;
    int target = 10;
    testSearch(a, target, "binary");
    testSearch(a, target, "interpolation");
  }
}


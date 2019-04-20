class Main {
  public static void main(String[] args) {
    System.out.println("Hello world!");
    // printShiftedTriangle(4, 4, '*');
    printPineTree(3, '*');
  }

  public static void printShiftedTriangle(int n, int m, char symbol) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m + 2 * n - 1; j++) {
        if (j < m)
          System.out.print(" ");
        else if (j < m + n - 1 - i)
          System.out.print(" ");
        else if (j < m + n + i)
          System.out.print("*");        
        else 
          System.out.print(" ");          
      }
      System.out.println();
    }
  }

  public static void printPineTree(int n, char symbol) {
    for (int i = 2; i <= n + 1; i++) {
      printShiftedTriangle(i, n + 1 - i, symbol);
    }
  }  
}

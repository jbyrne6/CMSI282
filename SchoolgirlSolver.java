public class SchoolgirlSolver {

  int[][] walkingOrderTable = new int[7][15];
  boolean[][] openSpot = new boolean[15][15];
  char[] key = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O'};

  public static void populateOrderTable (int[][] orderTable) {
    // populate the walkingOrderTable with constraints
    for (int y = 0; y < 7; y++) {
      for (int x = 0; x < 15; x++) {
        if (y == 0) {
          orderTable[y][x] = x;
        } else {
          if (x == 0) {
            orderTable[y][x] = 0;
          } else if (x == 3) {
            orderTable[y][x] = 1;
          } else if (x == 6) {
            orderTable[y][x] = 2;
          }
        }
        System.out.print(orderTable[y][x]);
        System.out.print(" ");
      }
      System.out.println();
    }
  }

  public boolean canGroup(int currentGirl, int y, int x){
    int girlSlot = x%3;
    int girl = 0;
    boolean group = false;
    for(int i = 1; 0 <= girlSlot; i++){
      girl = walkingOrderTable[y][x - i];
      group = openSpot[girl][currentGirl];
      if( girl > currentGirl ){
        group = false;
      }
    }
    return group;
  }

  //Check to make sure that added value in slot 0 is allowed.
  // if( **index** %3 == 0){
  //   if( **currentGirl** < walkingOrderTable[y][x-3] ) {
  //     return false;
  //   }
  // }
  //
  // Check to make sure that the x = 1 slot is correct
  // if( x == 1 ){
  //   if( **currentGirl** < walkingOrderTable[y-1][x] ){
  //     return false;
  //   }
  // }
  //
  // M N and O aka 12, 13, 14 are in the **index** %3 == 2 

  //System.out.println(Arrays.toString(boolArr));
  public static void populateOpenSpot (boolean[][] openSpot) {
    // populate openSpot with all true
    for (int y = 0; y < 15; y++) {
      for (int x = 0; x < 15; x++) {
        openSpot[y][x] = true;
        System.out.print(openSpot[y][x]);
        System.out.print(" ");
      }
      System.out.println();
    }
  }

  public static void main (String[] args) {
    int[][] kevin = new int[7][15];
    populateOrderTable(kevin);

    boolean[][] kevin2 = new boolean[15][15];
    populateOpenSpot(kevin2);

    kevin[x][y].canGroup(currentGirl)
  }
}

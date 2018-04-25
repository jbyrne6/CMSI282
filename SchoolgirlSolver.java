import java.util.Arrays;

public class SchoolgirlSolver {

  int[][] girlGroups;
  boolean[][] adjMatrix;
  char[] girlValue;

  public SchoolgirlSolver() {
      girlGroups = new int[7][15];
      adjMatrix = new boolean[15][15];
      girlValue = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O'};
  }

  public int[][] populateOrderTable () {

    for (int y = 0; y < 7; y++) {
      for (int x = 0; x < 15; x++) {
        if (y == 0) {
          girlGroups[y][x] = x;
        } else {
          if (x == 0) {
            girlGroups[y][x] = 0;
          } else if (x == 3) {
            girlGroups[y][x] = 1;
          } else if (x == 6) {
            girlGroups[y][x] = 2;
          }
        }
      }
    }
    return girlGroups;
  }

  public boolean[][] populateAdjMatrix () {
    // populate adjMatrix with all true
    for (int y = 0; y < 15; y++) {
      for (int x = 0; x < 15; x++) {
          adjMatrix[y][x] = true;
          if( x >= 0 && x <= 2 && y >= 0 && y <=2 ){
            adjMatrix[y][x] = false;
          }
          else if( x >= 3 && x <=5 && y >= 3 && y <=5 ){
            adjMatrix[y][x] = false;
          }
          else if( x >= 6 && x <=8 && y >= 6 && y <=8 ){
            adjMatrix[y][x] = false;
          }
          else if( x >= 9 && x <=11 && y >= 9 && y <=11 ){
            adjMatrix[y][x] = false;
          }
          else if( x >= 12 && x <=14 && y >= 12 && y <=14 ){
            adjMatrix[y][x] = false;
          }
      }
    }
    return adjMatrix;
  }

  //the y and x are passed in so we know what current slot (day and row) that we are working on
  public boolean canPlace(int currentGirl, int y, int x ){
    int girlSlot = x%3;
    //System.out.println(girlSlot);
    int lastGirl = 0;
    boolean group = false;
    if( currentGirl > 14){
      return false;
    }
    if( x%3 == 0 && x != 0 ){
      if(currentGirl < girlGroups[y][x-3] ){
        return false;
      }
    }
    if( x == 1 && currentGirl < girlGroups[y-1][x] ){
      return false;
    }
    for(int i = 1; i <= girlSlot; i++){
      lastGirl = girlGroups[y][x - i];
      //System.out.println(lastGirl);
      group = adjMatrix[lastGirl][currentGirl];
      //System.out.println(group);
      if( lastGirl > currentGirl ){
        group = false;
      }
      if( group == false ){
        return group;
      }
      // System.out.println(group);
    }
    return group;
  }

  public void fillAdjMatrix( int currentGirl, int y, int x ) {
    for (int i = x%3; i > 0; i--) {
      adjMatrix[currentGirl][girlGroups[y][x - i]] = false;
      adjMatrix[girlGroups[y][x - i]][currentGirl] = false;
    }
  }

  public void clearAdjMatrix( int currentGirl, int y, int x ){
    for(int i = x%3; i > 0; i--){
      adjMatrix[girlGroups[y][x-i]][currentGirl] = true;
      adjMatrix[currentGirl][girlGroups[y][x-i]] = true;

    }
  }


  // Check to make sure that the x = 1 slot is correct
  //
  // M N and O aka 12, 13, 14 are in the **index** %3 == 2

  //System.out.println(Arrays.toString(boolArr));
      int numOfRuns = 0;
  public int[][] solver( int currentGirl, int y, int x ){
    //System.out.println(currentGirl);
    // for(int i = 0; i < 7; i++){
    //   for(int j = 0; j < 15; j++){
    //     System.out.print( this.girlGroups[i][j] );
    //   }
    //   System.out.println();
    // }
    // for(int i = 0; i < 15; i++){
    //   for(int j = 0; j < 15; j++){
    //     System.out.print( Boolean.toString(this.adjMatrix[i][j]) + " ");
    //   }
    //   System.out.println();
    // }
    // System.out.println();
    // System.out.println("Current Girl = " + currentGirl);
    // System.out.println("Current X    = " + x);
    // System.out.println("X % 3        = " + x%3);
    // System.out.println("Current Y    = " + y);
    // System.out.println("Can Place    = " + this.canPlace( currentGirl, y, x));

    numOfRuns ++;
    if( x < 15){
      if( y == 0 ){
        return solver(currentGirl, y + 1, x);
      }
      if( x == 0 || x == 3 || x == 6 ){
        return solver(currentGirl, y, x + 1);
      }

      if( this.canPlace( currentGirl, y, x) ){
        System.out.println("can place !!");
        girlGroups[y][x] = currentGirl;
        this.fillAdjMatrix(currentGirl, y, x);
        // if(numOfRuns == 2){
        //   return girlGroups;
        // }
        return solver( currentGirl + 1, y, x + 1 );
      }
      else if( x%3 != 2 && currentGirl <= 11 ) {
        //System.out.println("bumping girl up 1");
        return solver( currentGirl + 1, y, x);
      }
      else if (x%3 == 2 && currentGirl <= 14) {
        //System.out.println("bumping girl up 1");
        return solver( currentGirl + 1, y, x);
      }
      else if( x == 1  && currentGirl > 11 ){
        //System.out.println("recursed 1 !!!");
        this.clearAdjMatrix(girlGroups[y][x - 1], y, x);
        return solver(girlGroups[y-1][14] + 1, y - 1, 14);
      }
      else if( x%3 !=2 && currentGirl > 11){
        //System.out.println("recursed 2 !!!");
        this.clearAdjMatrix(girlGroups[y][x - 1], y, x);
        return solver( girlGroups[y][x - 1] + 1, y, x - 1);
      }
      else if(x%3 == 2 && currentGirl > 14 ){
        //System.out.println(" recused 3 !!!");
        this.clearAdjMatrix(girlGroups[y][x - 1], y, x);
        return solver( girlGroups[y][x - 1] + 1, y, x - 1);
      }
    }
    else if( y < 7 && x >= 15 ){
      //System.out.println("made it to the end!");
      return solver( 1, y + 1, 0);
    }
    //System.out.println("Is returning girlGroups");
    return girlGroups;
  }

  public static void main (String[] args) {
    SchoolgirlSolver girl = new SchoolgirlSolver();
    boolean[][] b = girl.populateAdjMatrix();
    int[][] i = girl.populateOrderTable();
    int[][] theFinalSolution;


    // System.out.println(girl.canPlace( 2, 1, 1));
    // System.out.println(girl.canPlace( 3, 1, 1));

    //this format of updateAdjMatrix allows for update girlGroups in the current and last slot(s)
    //girl.updateAdjMatrix( 1, 0 );
    //girl.updateAdjMatrix( 2, 0 );
    //girl.updateAdjMatrix( 1, 2 );
    //girl.initConditionAdjMatrix();

    girl.solver( 0, 0, 0);

    // for (int in = 0; in < 5; in++) {
    //   girl.fillAdjMatrix(4 + in, 8);
    // }

    // for(int y = 0; y < 15; y++){
    //   for(int x = 0; x < 15; x++){
    //     System.out.print(girl.adjMatrix[y][x] + " " );
    //   }
    //   System.out.println();
    // }

    System.out.println();

    // girl.girlGroups[1][1] = 3;
    // System.out.println(girl.canPlace( 4, 1, 2 ));
    // System.out.println(girl.canPlace( 5, 1, 2 ));
    // System.out.println(girl.canPlace( 6, 1, 2 ));
    // girl.fillAdjMatrix( 6, 1, 2);
    // System.out.println();

    // for(int y = 0; y < 7; y++){
    //   for(int x = 0; x < 15; x++){
    //     System.out.print( girl.girlGroups[y][x] + " " );
    //   }
    //   System.out.println();
    // }

    // for(int y = 0; y < 15; y++){
    //   for(int x = 0; x < 15; x++){
    //     System.out.print(girl.adjMatrix[y][x] + " " );
    //   }
    //   System.out.println();
    // }

    // for ( int y = 0; y < 15; y++ ){
    //   for ( int x = 0; x < 15 ; x++ ){
    //     System.out.print( girl.adjMatrix[y][x] + " ");
    //   }
    //   System.out.println();
    // }
    //
    // int currentGirl = 2;
    // int y = 0;
    // int x = 1;
    //
    // boolean a = girl.canPlace(currentGirl, y, x);
    // boolean aa = girl.canPlace(currentGirl + 1, y, x);
    // System.out.println(a + " " + aa);
  }
}

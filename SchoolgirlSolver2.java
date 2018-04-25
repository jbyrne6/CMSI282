import java.util.Arrays;

public class SchoolgirlSolver2 {

  int[] girlGroups = new int[105];
  boolean[][] adjMatrix = new boolean[15][15];
  boolean[][] dayMatrix = new boolean[7][15];
  char[] girlValue = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O'};
  int[] girlsToPlace = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};
  private final int LAST_SLOT = 104;

  public int[] populateOrderTable () {
    for(int x : girlGroups ){
      girlGroups[x] = 0;
    }
    return girlGroups;
  }

  public void populateMatricies () {
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

    for(int i = 0; i < 7; i++){
      for (int j = 0; j < 15; j++) {
        dayMatrix[i][j] = true;
      }
    }
  }

  public boolean canPlace(int currentWalker, int currentSlot){
    int girlSlot = currentSlot%3;
    int lastGirl = 0;
    boolean group = true;
    if( canWalkToday( currentSlot ) ){
      System.out.println("can walk!");
      if( currentWalker > 14){
        System.out.println("1");
        return false;
      }
      if( currentSlot%3 == 0 && currentSlot/15 != 0 ){
        if(currentWalker < girlGroups[currentSlot] ){
          System.out.println("2");
          return false;
        }
      }
      if( currentSlot > 15 && currentSlot%15 == 1 && currentWalker < girlGroups[currentSlot - 15 ] ){
        System.out.println("3");
        return false;
      }
      for(int i = girlSlot; i > 0; i-- ){
        lastGirl = girlGroups[currentSlot - 1];
        group = adjMatrix[lastGirl][currentWalker];
        if( lastGirl > currentWalker ){
          System.out.println("4");
          group = false;
        }
        if( group == false ){
          System.out.println("5");
          return group;
        }
      }
    }
    return group;
  }

  public boolean canWalkToday(int slot){
    return dayMatrix[slot/15][slot%15];
  }

  public void placeGirl(int currentWalker, int currentSlot ){
    this.fillAdjMatrix( currentWalker, currentSlot);
    girlGroups[ currentSlot ] = currentWalker;
    dayMatrix[currentSlot/15][currentWalker] = false;
  }

  public void fillAdjMatrix( int currentWalker, int currentSlot) {
    for (int i = currentSlot%3; i > 0; i--) {
      adjMatrix[currentWalker][girlGroups[currentSlot - 1]] = false;
      adjMatrix[girlGroups[currentSlot - 1]][currentWalker] = false;
    }
  }

  public void clearMatricies( int currentSlot, int previousSlot ){
    for(int i = (currentSlot)%3; i > 0; i--){
      adjMatrix[girlGroups[currentSlot - i]][currentSlot] = true;
      adjMatrix[currentSlot][girlGroups[currentSlot - i]] = true;
      dayMatrix[currentSlot/15][ girlGroups[currentSlot] ] = true;
    }
  }

  public int determineFirstWalker( int slot){
    if( slot/3 == 0 && slot%3 == 0 ){
      return 0;
    }
    if( slot/3 == 1 && slot%3 == 1 ){
      return 1;
    }
    if( slot/3 == 2 && slot%3 == 2 ){
      return 2;
    }
    if( girlGroups[slot] == 0 ){
      return 3;
    }
    if( girlGroups[slot] < girlGroups[slot - 1] ){
      return girlGroups[slot - 1] + 1;
    }
    return girlGroups[slot] + 1;
  }

  public int determineLastWalker(int slot){
    if( slot%3 != 2 ){
      return 11;
    }
    else{
      return 14;
    }
  }

  int counter = 0;
  public void placeGirlsFrom( int slot ){
    System.out.println( "count :: " + counter++ );
    System.out.println( "Slot :: " + slot );
    int start = determineFirstWalker( slot );
    int end = determineLastWalker( slot );
    System.out.println("start :: " + start);
    System.out.println( "end :: " + end);
    for( int x = start; x < end; x++ ){
      if( this.canPlace( girlsToPlace[x], slot ) ){
        System.out.println("can place!");
        this.placeGirl( girlsToPlace[x], slot);
        if( slot == LAST_SLOT){
          this.finish();
        }
        else{
          this.placeGirlsFrom( slot + 1 );
        }
      }
      else{
        System.out.println("cannot place");
        this.clearMatricies( slot - 1, slot - 2);
        this.placeGirlsFrom( slot - 1 );
      }
    }
  }

  public String finish(){
    return Arrays.toString(girlGroups);
  }

  public static void main( String args[]){

    SchoolgirlSolver2 girl = new SchoolgirlSolver2();
    //System.out.println(Arrays.toString(girl.girlGroups));

    girl.populateOrderTable();
    girl.populateMatricies();
    //
    // for ( int y = 0; y < 15; y++ ){
    //   for ( int x = 0; x < 15 ; x++ ){
    //     System.out.print( girl.adjMatrix[y][x] + " ");
    //   }
    //   System.out.println();
    // }
    // for ( int y = 0; y < 7; y++ ){
    //   for ( int x = 0; x < 15 ; x++ ){
    //     System.out.print( girl.dayMatrix[y][x] + " ");
    //   }
    //   System.out.println();
    // }
    girl.placeGirlsFrom(0);
    System.out.println( girl.finish() );
  }
}

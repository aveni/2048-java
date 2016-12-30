import java.util.Scanner;


public class MainGame
{



  public static void playGame(Grid grid)
  {
    Scanner sc = new Scanner(System.in);

    boolean won= false;
    while(grid.canMove() && !won)
    { 
      int madeMove = 0;
      while (madeMove != 1)
      {
        String s= sc.next();
        if (s.equals("d")){madeMove = grid.move(0)[0];}
        if (s.equals("w")){madeMove = grid.move(1)[0];}
        if (s.equals("a")){madeMove = grid.move(2)[0];}
        if (s.equals("s")){madeMove = grid.move(3)[0];}
      }

      if (Math.random()<.9) grid.addRandomTile(2);
      else grid.addRandomTile(4);

      if (grid.getLargest() == 2048) won=true;
      System.out.println(grid);
    }

    if (won) System.out.println("YOU WON!");
    else System.out.println("YOU LOST.");
  }


  public static void playAI(Grid grid, AI ai)
  {
    boolean won = false;
    while(grid.canMove() && !won)
    {
      int move = ai.bestMove(grid); grid.move(move);

      if (move == 0) System.out.println("RIGHT");
      if (move == 1) System.out.println("UP");
      if (move == 2) System.out.println("LEFT");
      if (move == 3) System.out.println("DOWN");

      if (Math.random()<.9) grid.addRandomTile(2);
      else grid.addRandomTile(4);

      if (grid.getLargest() == 2048) won=true;
      System.out.println(grid);
    }

    if (won) System.out.println("YOU WON!");
    else System.out.println("YOU LOST.");
  }


  public static double testAI(AI ai, int numTests, int goal)
  {
    int numWin = 0;
    for (int i=0; i<numTests; i++)
    {
      if (i%(numTests/5) == 0) System.out.println("Testing:" + i);
      Grid grid = new Grid();
      for (int j=0; j<2; j++)
      {
        if (Math.random()<.9) grid.addRandomTile(2);
        else grid.addRandomTile(4);
      }
      boolean won = false;      

      while(grid.canMove() && !won)
      {
        int move = ai.bestMove(grid); grid.move(move);
        if (Math.random()<.9) grid.addRandomTile(2);
        else grid.addRandomTile(4);
        if (grid.getLargest() == goal){won=true; numWin++;}
      }
    }
    return (numWin*1.0)/numTests;
  }


  public static void main(String[] args)
  {  
    /*
    //setup
    Grid grid = new Grid();  
    AI ai = new BestCombine();


    for (int i=0; i<2; i++)
    {
      if (Math.random()<.9) grid.addRandomTile(2);
      else grid.addRandomTile(4);
    }

    System.out.println(grid + "--------------------------------------");

//    playGame(grid);
    playAI(grid, ai);
     */


    AI aiRand = new AI();
    CircleSpam aiCS = new CircleSpam();
    MostCombine aiMC = new MostCombine();
    BestCombine aiBC = new BestCombine();
    MCLayered MCL =  new MCLayered(4);
    BCLayered BCL = new BCLayered(4);

    int numTests = 500;
    int goal = 2048;


    System.out.println("numTests=" + numTests + "\tgoal=" + goal +"\n-------------------------------");
    //System.out.println(testAI(aiRand, numTests, goal));
    //System.out.println(testAI(aiCS, numTests, goal));
    //System.out.println(testAI(aiMC, numTests, goal));
    //System.out.println(testAI(aiBC, numTests, goal));
    //System.out.println(testAI(MCL, numTests, goal));
    System.out.println(testAI(BCL, numTests, goal));



  }







}

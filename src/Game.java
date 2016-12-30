import java.util.Scanner;


public class Game
{

  public static void main(String[] args)
  {
    Scanner sc = new Scanner(System.in);

    //setup
    Grid grid = new Grid();  
    for (int i=0; i<2; i++)
    {
      if (Math.random()<.9) grid.addRandomTile(2);
      else grid.addRandomTile(4);
    }

    System.out.println(grid + "--------------------------------------");
    
    
    
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
      System.out.println("--------------------------------------");
      System.out.println(grid + "--------------------------------------");
    }

    if (won) System.out.println("YOU WON!");
    else System.out.println("YOU LOST.");

  }

}

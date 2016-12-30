
public class AI
{
  
  public static int numComb(Grid grid, int move)
  {
    Grid g = new Grid(grid);
    int flag = g.move(move)[0];
    
    if (flag == 0) return -1;
    else return grid.getNumTiles() - g.getNumTiles();
  }
  
  public static int[] mostComb(Grid grid)
  {
    int best = 0;
    int bestMove = 0;
    
    for (int i=0; i<=3; i++)
    {
      int cur = numComb(grid, i);
      if (cur>best){best = cur; bestMove = i;}
    }    
    return new int[]{bestMove, best};
  }
    
  public static int[] bestComb(Grid grid)
  {
    int best = 0;
    int bestMove = 0;
    
    for (int i=0; i<=3; i++)
    {
      Grid g = new Grid(grid);
      int[] a = g.move(i);
      if (a[0] != 0 && a[1]>best){best = a[1]; bestMove = i;}
    }    
    return new int[]{bestMove, best};
  }
  

  
  public int bestMove(Grid grid)
  {
    return (int)(4*Math.random());
  }
}

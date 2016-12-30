
public class BCLayered extends AI
{
private int l;
  
  public BCLayered(){l = 2;}
  public BCLayered(int layers){l = layers;}
  
  private double[] best(Grid grid, int layers)
  {
    if (layers == 1) return new double[] {(double)bestComb(grid)[0], (double)bestComb(grid)[1]};
    
    else
    {
      double best = 0.0;
      double bestMove = 0;
      
      for (int i=0; i<4; i++)
      {
        Grid g = new Grid(grid);
        int[] a = g.move(i);
        if (a[0] != 0)
        {
          int N = a[1];
          //System.out.println(g); System.out.println(mostComb(g)[1]);
          N+=.91*best(g, layers-1)[1];
          
          //System.out.println("l = " + layers + "\ti = " + i + "\tN = " + N);
          if (N>best){bestMove = i; best = N;} 
        }  
      }
      return new double[] {bestMove, best};
    }
  }
  
  public int bestMove(Grid grid)
  {
    return (int)best(grid, l)[0];
  }
}

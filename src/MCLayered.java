
public class MCLayered extends AI
{
  private int l;
  
  public MCLayered(){l = 2;}
  public MCLayered(int layers){l = layers;}
  
  private double[] best(Grid grid, int layers)
  {
    if (layers == 1) return new double[] {(double)mostComb(grid)[0], (double)mostComb(grid)[1]};
    
    else
    {
      double best = 0.0;
      double bestMove = 0;
      
      for (int i=0; i<4; i++)
      {
        Grid g = new Grid(grid);
        if (numComb(g, i) != -1)
        {
          double N = numComb(g, i);
          g.move(i);
          //System.out.println(g); System.out.println(mostComb(g)[1]);
          N+=.75*(best(g, layers-1)[1]);
          
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

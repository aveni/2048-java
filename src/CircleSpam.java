
public class CircleSpam extends AI
{
  private int c;

  public CircleSpam(){c = 3;}

  public int bestMove(Grid grid)
  {
    c = (c+1)%4;
    return c;
  }
}

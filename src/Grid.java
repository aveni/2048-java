import java.util.Arrays;


public class Grid
{
  private int[][] grid;

  public Grid() {grid = new int[4][4];}  

  public Grid(int N) {grid = new int[N][N];}

  public Grid(Grid other)
  {
    int[][] a = other.getGrid();
    grid = a;
  }

  private static int log2(int a)
  {
  return (int)(Math.log(a)/Math.log(2));
  }

  public int getValue(int i, int j){return grid[i][j];}
  public void setValue(int i, int j, int N){grid[i][j] = N;}

  public int getNumTiles()
  {
    int c=0;
    for (int[] a: grid)
    {
      for (int n: a) if(n!=0) c++;
    }
    return c;
  }

  public int getNumEmpty()
  {
    int c=0;
    for (int[] a: grid)
    {
      for (int n: a) if(n==0) c++;
    }
    return c;
  }

  public int[][] getGrid()
  {
    int[][] a = new int[grid.length][grid.length];
    for (int i=0; i<grid.length; i++)
    {
      for (int j=0; j<grid.length; j++)
      {
        a[i][j] = grid[i][j];
      }
    }
    return a;
  }

  private int[] left()
  {
    int flag = 0;
    int N = 0;
    for (int i=0; i<grid.length; i++)
    {
      int least = -1;
      for (int j=1; j<grid.length; j++)
      {
        if (grid[i][j] != 0)
        {
          int c = j;
          while (c-1>least && grid[i][c-1] == 0)
          {
            grid[i][c-1] = grid[i][c];
            grid[i][c] = 0;
            c--; flag = 1;
          }

          if(c-1>least && grid[i][c] == grid[i][c-1])
          {
            grid[i][c-1] += grid[i][c]; N+= log2(grid[i][c]);
            grid[i][c] = 0;
            least = c-1; flag = 1;
          }
        }
      }
    }
    return new int[] {flag, N};
  }

  private int[] right()
  {
    int flag = 0;
    int N = 0;
    for (int i=0; i<grid.length; i++)
    {
      int farthest = grid.length;
      for (int j=grid.length-2; j>=0; j--)
      {
        if (grid[i][j] != 0)
        {
          int c = j;
          while (c+1<farthest && grid[i][c+1] == 0 )
          {
            grid[i][c+1] = grid[i][c];
            grid[i][c] = 0;
            c++; flag = 1;
          }

          if(c+1<farthest && grid[i][c] == grid[i][c+1])
          {
            grid[i][c+1] += grid[i][c]; N+=log2(grid[i][c]);
            grid[i][c] = 0;
            farthest = c+1; flag = 1;
          }
        }
      }
    }
    return new int[] {flag, N};
  }
  

  private int[] up()
  {
    int flag = 0;
    int N = 0;
    for (int i=0; i<grid.length; i++)
    {
      int least = -1;
      for (int j=1; j<grid.length; j++)
      {
        if (grid[j][i] != 0)
        {
          int c = j;
          while (c-1>least && grid[c-1][i] == 0)
          {
            grid[c-1][i] = grid[c][i];
            grid[c][i] = 0;
            c--; flag = 1;
          }

          if(c-1>least && grid[c][i] == grid[c-1][i])
          {
            grid[c-1][i] += grid[c][i]; N+=log2(grid[c][i]);          
            grid[c][i] = 0;
            least = c-1; flag = 1;
          }
        }
      }
    }
    return new int[] {flag, N};
  }


  private int[] down()
  {
    int flag = 0;
    int N = 0;
    for (int i=0; i<grid.length; i++)
    {
      int farthest = grid.length;
      for (int j=grid.length-2; j>=0; j--)
      {
        if (grid[j][i] != 0)
        {
          int c = j;
          while (c+1<farthest && grid[c+1][i] == 0 )
          {
            grid[c+1][i] = grid[c][i];
            grid[c][i] = 0;
            c++; flag = 1;
          }

          if(c+1<farthest && grid[c][i] == grid[c+1][i])
          {
            grid[c+1][i] += grid[c][i]; N+=log2(grid[c][i]);
            grid[c][i] = 0;
            farthest = c+1; flag = 1;
          }
        }
      }
    }
    return new int[] {flag, N};
  }

  public int[] move(int N)
  {
    if (N == 0) return this.right();
    if (N == 1) return this.up();
    if (N == 2) return this.left();
    if (N == 3) return this.down();
    
    else return new int[] {0, -1};

  }

  public boolean canMove()
  {
    for (int i=0; i<=3; i++)
    {
      Grid g = new Grid(this);
      if(g.move(i)[0] == 1) return true;
    }

    return false;
  }

  public int getLargest()
  {
    int largest = 0;
    for (int i=0; i<grid.length; i++)
    {
      for (int j=0; j<grid.length; j++)
      {
        if (grid[i][j] > largest) largest = grid[i][j];
      }
    }
    return largest;
  }

  
  public boolean addRandomTile(int N)
  {
    int[][] positions = new int[grid.length*grid.length][2];
    int numOpen = 0;
    for (int i=0; i<grid.length; i++)
    {
      for (int j=0; j<grid.length; j++)
      {
        if (grid[i][j] == 0){positions[numOpen][0] = i; positions[numOpen][1] = j; numOpen++;}
      }
    }
    if (numOpen == 0) return false;

    int rand = (int)(Math.random()*numOpen);
    grid[positions[rand][0]][positions[rand][1]] = N;
    return true;
  }

  public String toString()
  {
    String s = "";
    for (int i=0; i<grid.length; i++)
    {
      for (int j=0; j<grid.length; j++)
      {
        if (grid[i][j] == 0) s+="-\t";
        else s+=grid[i][j] + "\t";
      }
      s+="\n";
    }

    return s;
  }

}

import java.util.Random;

public class Figures {

    private int[][] coordinates = new int[4][2];
    private Shapes pieceShape;

    public enum Shapes {
        Noshape(new int[][]{ {0,0}, {0,0}, {0,0}, {0,0}}),
        Zshape(new int[][]{ {0,0}, {0,1}, {1,1}, {1,2}}),
        Sshape(new int[][]{ {2,2}, {2,1}, {3,1}, {3,0}}),
        Lineshape(new int[][]{ {0,0}, {0,1}, {0,2}, {0,3}}),
        Tshape(new int[][]{ {0,1}, {1,1}, {2,1}, {1,2}}),
        Squareshape(new int[][]{ {0,2}, {0,1}, {0,0}, {1,0}}),
        Lshape(new int[][]{ {0,1}, {0,2}, {0,3}, {1,3}}),
        Jshape(new int[][]{ {1,1}, {1,2}, {1,3}, {0,3}});

        private int coords[][];

        Shapes(int[][] coords) {
            this.coords = coords;
        }
    }

    public void setShape(Shapes shape)
    {
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 2; j++)
            {
                coordinates[i][j] = shape.coords[i][j];
            }
            Shapes pieceShape = shape;
        }
    }

    public void setRandomShape()
    {
        Random random = new Random();
        int a = Math.abs(random.nextInt()) % 7 + 1;
        Shapes[] values = Shapes.values();

        setShape(values[a]);
    }

    public int[][] getCoords()
    {
        return coordinates;
    }

    public int getX(int i) {
        return coordinates[i][0];
    }
    public int getY(int i) {
        return coordinates[i][1];
    }

    public Shapes getShape()
    {
        return pieceShape;
    }
}

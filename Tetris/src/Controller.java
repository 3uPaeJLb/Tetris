public class Controller
{
    private Board board;

    public Controller(Board board)
    {
        this.board = board;
    }

    public void moveLeft(int curX)
    {
        int newX  = curX - board.BLOCK_SIZE;
        board.setCurX(newX);
    }

    public void moveRight(int curX)
    {
        int newX  = curX + board.BLOCK_SIZE;
        board.setCurX(newX);
    }
}

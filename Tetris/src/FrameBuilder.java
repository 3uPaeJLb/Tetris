import javax.swing.*;

public class FrameBuilder extends JFrame {
    public FrameBuilder()
    {
        super("Tetris");
    }

    public void buildFrame()
    {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100,100,315, 518);
        getContentPane().setLayout(null);

        Board board = new Board();
        board.setBounds(0 , 0, 300, 480);

        add(board);

        setVisible(true);
    }
}


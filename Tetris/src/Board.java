import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {
    private final int BOARD_HEIGHT = 510;
    private final int BOARD_WIDTH = 330;
    private final int BLOCK_SIZE = 30;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //drawing the board
        g.setColor(Color.black);
        g.fillRect(0,0,BOARD_WIDTH, BOARD_HEIGHT);

        g.setColor(Color.white);

        for(int i = 0; i < BOARD_HEIGHT; i++)
        {
            g.drawLine(0, i * BLOCK_SIZE, BOARD_HEIGHT, i * BLOCK_SIZE);
        }

        for (int i = 0; i < BOARD_WIDTH; i++)
        {
            g.drawLine(i * BLOCK_SIZE, 0, i * BLOCK_SIZE, BOARD_HEIGHT);
        }
    }

}

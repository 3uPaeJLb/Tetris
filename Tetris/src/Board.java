import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    private final int BOARD_HEIGHT = 510;
    private final int BOARD_WIDTH = 330;
    private final int BLOCK_SIZE = 30;

    private int[][] coordinates;
    private Figures figure = new Figures();
    private int x;
    private int y;
    private int curX = 120;
    private int curY = 0;

    public Board() {
        figure.setRandomShape();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //drawing the board
        g.setColor(Color.black);
        g.fillRect(0,0,BOARD_WIDTH, BOARD_HEIGHT);

        //drawing shapes
        coordinates = figure.getCoords();

        for (int i = 0; i < 4; i++) {
            x = figure.getX(i) * BLOCK_SIZE;
            y = figure.getY(i) * BLOCK_SIZE;
            g.setColor(Color.RED);
            g.fillRect(curX + x, curY + y, BLOCK_SIZE, BLOCK_SIZE);
        }

        //drawing lines
        g.setColor(Color.white);

        for(int i = 0; i < BOARD_HEIGHT; i++) {
            g.drawLine(0, i * BLOCK_SIZE, BOARD_HEIGHT, i * BLOCK_SIZE);
        }

        for (int i = 0; i < BOARD_WIDTH; i++) {
            g.drawLine(i * BLOCK_SIZE, 0, i * BLOCK_SIZE, BOARD_HEIGHT);
        }
    }
}

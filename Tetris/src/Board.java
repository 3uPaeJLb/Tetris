import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel {

    private static final int BOARD_HEIGHT = 480;
    private static final int BOARD_WIDTH = 300;
    public static final int BLOCK_SIZE = 30;
    private static final int BLOCKS_IN_HEiGH = 16;
    private static final int BLOCKS_IN_WIDTH = 10;

    private int[][] coordinates;
    private Figures figure = new Figures();
    private int curX;
    private int curY;
    private Timer timer;
    private Figures.Shapes[] board;
    private boolean finishedFall = false;
    private int boardTop = 0;
    private Controller controller;

    public void setCurX(int newX) {
        curX = newX;
    }

    public Board() {
        figure.setRandomShape();
        curX = 120;
        curY = 0;
        controller = new Controller(this);
        setFocusable(true);

        board = new Figures.Shapes[BLOCKS_IN_WIDTH * BLOCKS_IN_HEiGH];
        for (int i = 0; i < BLOCKS_IN_WIDTH * BLOCKS_IN_HEiGH; i++) {
            board[i] = Figures.Shapes.Noshape;
        }

        addKeyListener(new TAdapter());

        timer = new Timer(400, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (finishedFall) {
                    finishedFall = false;
                    saveFigure();
                    newFigure();
                } else
                    oneLineDown();
            }
        });
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.black);
        g.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

        for (int i = 0; i < BLOCKS_IN_HEiGH * BLOCKS_IN_WIDTH; i++) {
            if (board[i] != Figures.Shapes.Noshape) {
                if (boardTop < BLOCKS_IN_HEiGH + 1 - (i / BLOCKS_IN_WIDTH) + 1) {
                    boardTop = (i / BLOCKS_IN_WIDTH) + 1 - BLOCKS_IN_HEiGH + 1;
                }
                int x = i % BLOCKS_IN_WIDTH;
                int y = i / BLOCKS_IN_WIDTH;
                g.setColor(Color.RED);
                g.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
            }
        }

        if (BLOCKS_IN_HEiGH - boardTop < 4) {
            timer.stop();
        }

        int x = 0;
        int y = 0;

        coordinates = figure.getCoords();

        for (int i = 0; i < 4; i++) {
            x = figure.getX(i) * BLOCK_SIZE;
            y = figure.getY(i) * BLOCK_SIZE;
            g.setColor(Color.RED);
            g.fillRect(x + curX, y + curY, BLOCK_SIZE, BLOCK_SIZE);
        }

        g.setColor(Color.white);

        for (int i = 0; i < BOARD_HEIGHT; i++) {
            g.drawLine(0, i * BLOCK_SIZE, BOARD_HEIGHT, i * BLOCK_SIZE);
        }

        for (int i = 0; i < BOARD_WIDTH; i++) {
            g.drawLine(i * BLOCK_SIZE, 0, i * BLOCK_SIZE, BOARD_HEIGHT);
        }
    }

    private void oneLineDown() {
        if (ableToMove(figure, curX, curY + BLOCK_SIZE)) {
            blockDropped();
        } else

            finishedFall = true;
    }

    private void blockDropped() {
        for (int i = 0; i < 4; i++) {
            int x = curX + figure.getX(i);
            int y = curY + figure.getY(i);
        }

        if (finishedFall) {
            newFigure();
        }
    }

    private void newFigure() {
        figure.setRandomShape();
        curX = BOARD_WIDTH / 2;
        curY = 0;

        if (!ableToMove(figure, curX, curY)) {
            figure.setShape(Figures.Shapes.Noshape);
            timer.stop();
        }
    }

    private void saveFigure() {
        for (int i = 0; i < 4; i++) {
            int x = curX + figure.getX(i) * BLOCK_SIZE;
            int y = curY + figure.getY(i) * BLOCK_SIZE;

            board[((y / BLOCK_SIZE) * BLOCKS_IN_WIDTH) + (x / BLOCK_SIZE)] = figure.getShape();
        }
    }

    private boolean ableToMove(Figures newFigure, int newX, int newY) {
        for (int i = 0; i < 4; i++) {
            int x = newX + this.figure.getX(i) * BLOCK_SIZE;
            int y = newY + this.figure.getY(i) * BLOCK_SIZE;

            if ((y >= BOARD_HEIGHT) || (y < 0) || (x >= BOARD_WIDTH) || (x < 0)) {
                return false;
            }

            if (blockPosition(x, y) != Figures.Shapes.Noshape)
                return false;
        }

        this.figure = newFigure;
        this.curX = newX;
        this.curY = newY;

        repaint();

        return true;
    }

    private Figures.Shapes blockPosition(int x, int y) {
        return board[((y / BLOCK_SIZE) * BLOCKS_IN_WIDTH) + (x / BLOCK_SIZE)];
    }

    class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent event) {
            int code = event.getKeyCode();

            switch (code) {
                case KeyEvent.VK_LEFT -> {
                    int check = 0;

                    if (ableToMove(figure, curX, curY)) {
                        for (int i = 0; i < 4; i++) {
                            int x = curX + figure.getX(i) * BLOCK_SIZE;
                            int y = curY + figure.getY(i) * BLOCK_SIZE;

                            check++;

                            if (blockPosition(x - 1, y) != Figures.Shapes.Noshape) {
                                break;
                            }
                        }
                    }

                    if (check == 4)
                        controller.moveLeft(curX);

                    check = 0;
                }

                case KeyEvent.VK_RIGHT -> {
                    int check1 = 0;

                    if (ableToMove(figure, curX, curY)) {
                        for (int i = 0; i < 4; i++) {
                            int x = curX - figure.getX(i);
                            int y = curY + figure.getY(i);

                            check1++;

                            if (blockPosition(x + 1, y) != Figures.Shapes.Noshape) {
                                break;
                            }
                        }
                    }

                    if (check1 == 4)
                        controller.moveRight(curX);

                    check1 = 0;
                }
            }
        }
    }
}


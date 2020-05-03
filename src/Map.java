import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Map extends JPanel {
    private GameWindow gameWindow;

    public static final int MODE_H_V_A = 0;
    public static final int MODE_H_V_H = 1;

    private int fieldSizeX;
    private int fieldSizeY;
    private int winLength;

    private int cellHeight;
    private int cellWidth;

    private boolean isInit = false;

    static public EndGameWindow endGameWindow;
    int mode;
    int turn = 0;




    public Map(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setBackground(Color.GRAY);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                update(e);
            }
        });

    }

    private void update(MouseEvent e) {
        int cellX = e.getX() / cellWidth;
        int cellY = e.getY() / cellHeight;

        if(!Logic.gameFinished & mode == MODE_H_V_A) {
            Logic.setHumanXY(cellX, cellY);
        }
        if(!Logic.gameFinished & mode == MODE_H_V_H & turn !=2) {
            Logic.setHumanX(cellX, cellY);
            turn = 2;
        } else if(!Logic.gameFinished & mode == MODE_H_V_H & turn ==2) {
            Logic.setHumanO(cellX, cellY);
            turn = 1;
        }
        if (Logic.gameFinished){
            endGameWindow = new EndGameWindow();
            endGameWindow.setVisible(true);
        }
        System.out.println(cellX + " " + cellY);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {
        if (!isInit) {
            return;
        }

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        cellHeight = panelHeight / fieldSizeY;
        cellWidth = panelWidth / fieldSizeX;

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3.0f));

        for (int i = 0; i < fieldSizeY; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }

        for (int i = 0; i < fieldSizeX; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }

        for (int i = 0; i < Logic.SIZE ; i++) {
            for (int j = 0; j < Logic.SIZE; j++) {
                if(Logic.map[i][j]==Logic.DOT_O){
                    drawO(g,j,i);
                }
                if(Logic.map[i][j]==Logic.DOT_X){
                    drawX(g,j,i);
                }
            }
        }
    }

    private void drawO(Graphics g, int cellX, int cellY){
        Graphics2D g2D = (Graphics2D)g;

        g.setColor(new Color(0,0,255));
        g.drawOval(cellX*cellWidth , cellY*cellHeight, cellWidth,cellHeight );


    }
    private void drawX(Graphics g, int cellX, int cellY){
        g.setColor(new Color(255, 3, 0));
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3.0f));
        g.drawLine(cellX*cellWidth , cellY*cellHeight,
                (cellX+1)*cellWidth , (cellY+1)*cellHeight );
        g.drawLine(cellX*cellWidth , (cellY+1)*cellHeight,
                (cellX+1)*cellWidth , cellY*cellHeight );

    }


    void startNewGame(int gameMode, int fieldSizeX, int fieldSizeY, int winLength) {
//        System.out.println(gameMode + " " + fieldSizeX + " " + fieldSizeY + " " + winLength);
        this.fieldSizeX = fieldSizeX;
        this.fieldSizeY = fieldSizeY;
        this.winLength = winLength;
        isInit = true;
        repaint();
        mode = gameMode;
        turn = 0;
    }
}
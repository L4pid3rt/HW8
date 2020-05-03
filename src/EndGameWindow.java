import javax.swing.*;
import java.awt.*;

public class EndGameWindow extends JFrame {
    private static final int WIN_HEIGHT = 150;
    private static final int WIN_WIDTH  = 100;
    private static final int WIN_POS_X  = 650;
    private static final int WIN_POS_Y  = 450;
    private String labelName;

    public EndGameWindow() {
        setBounds(WIN_POS_X,WIN_POS_Y,WIN_WIDTH,WIN_HEIGHT);
        setTitle("Win Box");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,1));

        labelName = String.format("%s wins!", Logic.winnerName);
        JLabel label1 = new JLabel(labelName);
        label1.setHorizontalAlignment(JLabel.CENTER);
        add(label1);
        JButton btnEndGame = new JButton("Al'right!");
        add(btnEndGame);
        btnEndGame.addActionListener(e->{
            removeAll();
            setVisible(false);
        });
    }

}

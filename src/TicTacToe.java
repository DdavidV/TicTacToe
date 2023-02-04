import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;
    int turn = 0;

    TicTacToe(){
        //set game frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        //set textfield for title
        textField.setBackground(new Color(25,25,25));
        textField.setForeground(new Color(25,255,0));
        textField.setFont(new Font("SansSerif",Font.BOLD,75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic-Tac-Toe");
        textField.setOpaque(true);

        //set title panel
        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,800,100);

        //set game area
        button_panel.setLayout(new GridLayout(3,3));

        //create buttons for game area
        for(int i = 0; i < 9; i++){
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("SansSerif",Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            buttons[i].setBackground(Color.LIGHT_GRAY);
        }

        //add everything together
        title_panel.add(textField);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++){
            if(e.getSource()==buttons[i]){
                if(player1_turn){
                    if(buttons[i].getText().equals("")){
                        buttons[i].setForeground(new Color(255,0,0));
                        buttons[i].setText("X");
                        player1_turn = false;
                        textField.setText("O turn");
                        check();
                    }
                } else{
                    if(buttons[i].getText().equals("")){
                        buttons[i].setForeground(new Color(0,0,255));
                        buttons[i].setText("O");
                        player1_turn = true;
                        textField.setText("X turn");
                        check();
                    }
                }
            }
        }
    }


    private void firstTurn(){
        if(random.nextInt() % 2 == 0){
            player1_turn = true;
            textField.setText("X turn");
        } else{
            player1_turn = false;
            textField.setText("O turn");
        }
    }

    private void check() {
        String combination = "";
        int[] loc = new int[3];
        for(int i = 0; i < 8; i++){
            switch (i){
                case 0:
                    combination = buttons[0].getText() + buttons[1].getText() + buttons[2].getText();
                    loc[0] = 0;
                    loc[1] = 1;
                    loc[2] = 2;
                    break;
                case 1:
                    combination = buttons[3].getText() + buttons[4].getText() + buttons[5].getText();
                    loc[0] = 3;
                    loc[1] = 4;
                    loc[2] = 5;
                    break;
                case 2:
                    combination = buttons[6].getText() + buttons[7].getText() + buttons[8].getText();
                    loc[0] = 6;
                    loc[1] = 7;
                    loc[2] = 8;
                    break;
                case 3:
                    combination = buttons[0].getText() + buttons[3].getText() + buttons[6].getText();
                    loc[0] = 0;
                    loc[1] = 3;
                    loc[2] = 6;
                    break;
                case 4:
                    combination = buttons[1].getText() + buttons[4].getText() + buttons[7].getText();
                    loc[0] = 1;
                    loc[1] = 4;
                    loc[2] = 7;
                    break;
                case 5:
                    combination = buttons[2].getText() + buttons[5].getText() + buttons[8].getText();
                    loc[0] = 2;
                    loc[1] = 5;
                    loc[2] = 8;
                    break;
                case 6:
                    combination = buttons[2].getText() + buttons[4].getText() + buttons[6].getText();
                    loc[0] = 2;
                    loc[1] = 4;
                    loc[2] = 6;
                    break;
                case 7:
                    combination = buttons[0].getText() + buttons[4].getText() + buttons[8].getText();
                    loc[0] = 0;
                    loc[1] = 4;
                    loc[2] = 8;
                    break;
            }
            if(combination.equals("XXX")){
                Win(loc[0],loc[1],loc[2],"X");
            } else if (combination.equals("OOO")) {
                Win(loc[0],loc[1],loc[2],"O");
            }
        }
    }

    private void Win(int a, int b, int c, String win){
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);
        for (int i = 0; i < 9; i++){
            buttons[i].setEnabled(false);
        }
        textField.setText(win +" wins");

        int result = JOptionPane.showConfirmDialog(frame,"Do you want to play again?", win +" wins",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if(result == JOptionPane.YES_OPTION){
            reset();
        }else if (result == JOptionPane.NO_OPTION){
            System.exit(0);
        }else {
            System.out.println("none");
        }
    }

    private void reset(){
        for (int i = 0; i < 9; i++){
            buttons[i].setText("");
            buttons[i].setBackground(Color.LIGHT_GRAY);
            buttons[i].setEnabled(true);
        }
    }
}

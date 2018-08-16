package com.allybros.UIComponent;

import com.allybros.Game;
import com.allybros.Unit.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GameInıtWindow extends JFrame{
    public GameInıtWindow(){


        JPanel gameInit = new JPanel();
        gameInit.setLayout(new BoxLayout(gameInit,BoxLayout.Y_AXIS));
        JPanel gameOptions = new JPanel();
        gameOptions.setLayout(new GridLayout(4,1));
        JPanel numberOfPlayersPanel = new JPanel();
        JButton startGame = new JButton("Start Game!");
        startGame.setAlignmentX(0.5f);

        JPanel mapOptionsPanel = new JPanel();

        JLabel mapSizeLabel = new JLabel("Map Size: ");
        JTextField mapSizeX = new JTextField();
        mapSizeX.setColumns(3);
        JLabel mapSizeMultip = new JLabel(" x ");
        JTextField mapSizeY = new JTextField();
        mapSizeY.setColumns(3);

        mapOptionsPanel.add(mapSizeLabel);
        mapOptionsPanel.add(mapSizeX);
        mapOptionsPanel.add(mapSizeMultip);
        mapOptionsPanel.add(mapSizeY);


        String [] playerNumbersList = {"2 Players", "3 Players","4 Players"};
        JComboBox optionPlayerNumbers = new JComboBox(playerNumbersList);

        String[] playerColorsList = {"Red","Blue","Green","Magenta","Cyan","Yellow","Orange"};

        JPanel player1OptionPanel = new JPanel();
        JLabel player1NameLabel = new JLabel("Player 1 Name:");
        JTextField player1NameField = new JTextField();
        player1NameField.setColumns(15);
        player1NameField.setFont( new Font("Verdana", Font.PLAIN, 16));
        JComboBox optionPlayer1Color = new JComboBox(playerColorsList);
        player1OptionPanel.add(player1NameLabel);
        player1OptionPanel.add(player1NameField);
        player1OptionPanel.add(optionPlayer1Color);


        JPanel player2OptionPanel = new JPanel();
        JLabel player2NameLabel = new JLabel("Player 2 Name:");
        JTextField player2NameField = new JTextField();
        player2NameField.setColumns(15);
        player2NameField.setFont( new Font("Verdana", Font.PLAIN, 16));
        JComboBox optionPlayer2Color = new JComboBox(playerColorsList);
        player2OptionPanel.add(player2NameLabel);
        player2OptionPanel.add(player2NameField);
        player2OptionPanel.add(optionPlayer2Color);


        JPanel player3OptionPanel = new JPanel();
        JLabel player3NameLabel = new JLabel("Player 3 Name:");
        JTextField player3NameField = new JTextField();
        player3NameField.setColumns(15);
        player3NameField.setFont( new Font("Verdana", Font.PLAIN, 16));
        JComboBox optionPlayer3Color = new JComboBox(playerColorsList);
        player3OptionPanel.add(player3NameLabel);
        player3OptionPanel.add(player3NameField);
        player3OptionPanel.add(optionPlayer3Color);


        JPanel player4OptionPanel = new JPanel();
        JLabel player4NameLabel = new JLabel("Player 4 Name:");
        JTextField player4NameField = new JTextField();
        player4NameField.setColumns(15);
        player4NameField.setFont( new Font("Verdana", Font.PLAIN, 16));
        JComboBox optionPlayer4Color = new JComboBox(playerColorsList);
        player4OptionPanel.add(player4NameLabel);
        player4OptionPanel.add(player4NameField);
        player4OptionPanel.add(optionPlayer4Color);

        gameOptions.add(player1OptionPanel);
        gameOptions.add(player2OptionPanel);

        optionPlayerNumbers.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ıtemEvent) {
                switch (optionPlayerNumbers.getSelectedIndex()) {
                    case 0:
                        gameOptions.removeAll();
                        gameOptions.add(player1OptionPanel);
                        gameOptions.add(player2OptionPanel);
                        break;
                    case 1:
                        gameOptions.removeAll();
                        gameOptions.add(player1OptionPanel);
                        gameOptions.add(player2OptionPanel);
                        gameOptions.add(player3OptionPanel);
                        break;
                    case 2:
                        gameOptions.removeAll();
                        gameOptions.add(player1OptionPanel);
                        gameOptions.add(player2OptionPanel);
                        gameOptions.add(player3OptionPanel);
                        gameOptions.add(player4OptionPanel);
                        break;
                }
                revalidate();
                repaint();
            }
        });
        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int x = Integer.parseInt(mapSizeX.getText());
                int y = Integer.parseInt(mapSizeY.getText());
                int numberOfPlayers = optionPlayerNumbers.getSelectedIndex()+2;
                String player1Name;
                String player2Name;
                String player3Name;
                String player4Name;
                int player1Color;
                int player2Color;
                int player3Color;
                int player4Color;
                Player[] players = new Player[numberOfPlayers];

                switch (numberOfPlayers){
                    case 2:
                        player1Name = player1NameField.getText();
                        player1Color = optionPlayer1Color.getSelectedIndex();
                        players[0] = new Player(player1Name,player1Color);

                        player2Name = player2NameField.getText();
                        player2Color = optionPlayer2Color.getSelectedIndex();
                        players[1] = new Player(player2Name,player2Color);
                        break;

                    case 3:
                        player1Name=player1NameField.getText();
                        player1Color=optionPlayer1Color.getSelectedIndex();
                        players[0] = new Player(player1Name,player1Color);

                        player2Name=player2NameField.getText();
                        player2Color=optionPlayer2Color.getSelectedIndex();
                        players[1] = new Player(player2Name,player2Color);

                        player3Name=player3NameField.getText();
                        player3Color=optionPlayer3Color.getSelectedIndex();
                        players[2] = new Player(player3Name,player3Color);
                        break;

                    case 4:
                        player1Name=player1NameField.getText();
                        player1Color=optionPlayer1Color.getSelectedIndex();
                        players[0] = new Player(player1Name,player1Color);

                        player2Name=player2NameField.getText();
                        player2Color=optionPlayer2Color.getSelectedIndex();
                        players[1] = new Player(player2Name,player2Color);

                        player3Name=player3NameField.getText();
                        player3Color=optionPlayer3Color.getSelectedIndex();
                        players[2] = new Player(player3Name,player3Color);

                        player4Name=player4NameField.getText();
                        player4Color=optionPlayer4Color.getSelectedIndex();
                        players[3] = new Player(player4Name,player4Color);
                        break;

                }

                new Game(x,y,players);
                dispose();

            }
        });
        JLabel optionPlayersLabel = new JLabel("Number of Players:");
        numberOfPlayersPanel.add(optionPlayersLabel);
        numberOfPlayersPanel.add(optionPlayerNumbers);
        gameInit.add(numberOfPlayersPanel);
        gameInit.add(mapOptionsPanel);
        gameInit.add(gameOptions);
        gameInit.add(startGame);
        add(gameInit);
        setSize(450,250);
        setTitle("Capture Num");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        show();

    }

    public static void main(String[] args) {
        new GameInıtWindow();
    }
}

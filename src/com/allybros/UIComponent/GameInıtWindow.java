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

    private static int counter;

    public GameInıtWindow(){

        JPanel gameInit = new JPanel();
        gameInit.setLayout(new BoxLayout(gameInit,BoxLayout.Y_AXIS));
        JPanel playerOptionsPanel = new JPanel(new GridLayout(4,1));
        JPanel gameOptionsPanel = new JPanel(new GridLayout(2,2));
        JButton startGame = new JButton("Start Game!");
        startGame.setAlignmentX(0.5f);

        //Map Form
        JPanel mapForm = new JPanel();
        JTextField mapSizeX = new JTextField(3);
        JTextField mapSizeY = new JTextField(3);
        mapForm.add(mapSizeX);
        mapForm.add(new JLabel("X"));
        mapForm.add(mapSizeY);

        //Number Of Players Form
        String [] playerNumbersList = {"2 Players", "3 Players","4 Players"};
        JComboBox optionPlayerNumbers = new JComboBox(playerNumbersList);

        //Player Options Class
        class PlayerFormPanel extends JPanel {
            String[] playerColorsList = {"Red","Blue","Green","Magenta","Cyan","Yellow","Orange"};
            JTextField playerNameField;
            JComboBox optionPlayerColor;

            PlayerFormPanel(){
                counter ++;
                playerNameField = new JTextField(15);
                playerNameField.setFont( new Font("Verdana", Font.PLAIN, 16));
                optionPlayerColor = new JComboBox(playerColorsList);
                optionPlayerColor.setSelectedIndex(counter-1);
                add(new JLabel("Player "+counter+" Name:"));
                add(playerNameField);
                add(optionPlayerColor);
            }

            String getPlayerName() {
                return playerNameField.getText();
            }

            int getColorNumber(){
                return optionPlayerColor.getSelectedIndex();
            }
        }

        //Player Forms List
        PlayerFormPanel[] playerFormPanels = new PlayerFormPanel[4];
        for (int i = 0; i<4; i++){
            playerFormPanels[i] = new PlayerFormPanel();
        }

        for (int i = 0; i<2; i++){
            playerOptionsPanel.add(playerFormPanels[i]);
        }

        optionPlayerNumbers.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ıtemEvent) {
                playerOptionsPanel.removeAll();
                for (int i = 0; i <= optionPlayerNumbers.getSelectedIndex()+1; i++){
                    playerOptionsPanel.add(playerFormPanels[i]);
                }
                revalidate();
                repaint();
            }
        });

        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int x = 15;
                int y = 10;
                try {
                    x = Integer.parseInt(mapSizeX.getText());
                    y = Integer.parseInt(mapSizeY.getText());
                }catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(null,"Please fix map size values.");
                    return;
                }

                int numberOfPlayers = optionPlayerNumbers.getSelectedIndex()+2;
                Player[] players = new Player[numberOfPlayers];
                for (int i = 0; i < numberOfPlayers; i++) {
                    players[i] = new Player(playerFormPanels[i].getPlayerName(),playerFormPanels[i].getColorNumber());
                }
                new Game(x, y, players);
                dispose();
            }
        });

        //Insert into Game Options Panel
        gameOptionsPanel.add(new JLabel("Number of Players:"));
        gameOptionsPanel.add(optionPlayerNumbers);
        gameOptionsPanel.add(new JLabel("Map Size:"));
        gameOptionsPanel.add(mapForm);

        //Build Window
        gameInit.add(gameOptionsPanel);
        gameInit.add(playerOptionsPanel);
        gameInit.add(startGame);
        gameInit.setBorder(new EmptyBorder(16,16,16,16));
        add(gameInit);
        setSize(500,250);
        setTitle("Ally Bros Ülke Kapıcı v 1.0");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        show();

    }

}

package com.allybros;

import com.allybros.UIComponent.MapPanel;
import com.allybros.UIComponent.NDataPanel;
import com.allybros.Unit.Player;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.allybros.Unit.Player.getPlayerById;

public class Game {

    public static int turn = 1;
    private static Player currentPlayer = getPlayerById(1);

    public static NDataPanel dataPanel;
    public static JFrame mainWindow;
    public static MapPanel mapPanel;

    public Game(int x, int y, Player ... players){
        mainWindow = new JFrame();
        mapPanel = new MapPanel(x,y);
        dataPanel = new NDataPanel(players);

        JButton completeTurn = new JButton("Complete Turn");
        completeTurn.setFont( new Font("serif", Font.BOLD, 18));
        completeTurn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Game.completeTurn();
            }
        });

        mainWindow.setSize(x*70,y*75);
        mainWindow.setLayout(new BorderLayout());
        mainWindow.add(mapPanel,BorderLayout.CENTER);
        mainWindow.add(dataPanel,BorderLayout.NORTH);
        mainWindow.add(completeTurn, BorderLayout.SOUTH);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.show();
    }

    public static void completeTurn(){
        turn++;
        int id = turn%Player.numberOfPlayers() == 0 ? Player.numberOfPlayers() : turn%Player.numberOfPlayers();
        currentPlayer = getPlayerById(id);
        dataPanel.update();
        System.out.println(turn);
        if (turn%Player.numberOfPlayers() == 1) {
           payment();
        }
        mainWindow.setTitle(currentPlayer.name+"'s Turn - Ülke Kapmaca");
    }

    public static void payment(){
        Player player;
        for (int i = 1;i<=Player.numberOfPlayers();i++){
            player = getPlayerById(i);
            player.newTurnPoints();
        }
        dataPanel.update();
    }

    public static Player getCurrentPlayer(){
        return currentPlayer;
    }
}

package com.allybros.UIComponent;

import com.allybros.Game;
import com.allybros.Main;
import com.allybros.Unit.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class DataPanel extends JPanel {

    ArrayList<Player> players = new ArrayList<>();

    public DataPanel(Player ... players){
        for (Player player : players){
            add(new PlayerDataPanel(player));
            this.players.add(player);
        }
    }

    public void update(){
        removeAll();
        for (Player player : this.players){
            add(new PlayerDataPanel(player));
        }
        revalidate();
        repaint();
    }

    public void updatePlayers(Player ... players){
        this.players.clear();
        for (Player player : players){
            this.players.add(player);
        }
    }

}

class PlayerDataPanel extends JPanel {

    public PlayerDataPanel(Player player){
        JLabel playerName = new JLabel(player.name);
        playerName.setForeground(player.getColor());
        setLayout(new GridLayout(2,1));
        add(playerName);
        JPanel subDataPanel = new JPanel();
        subDataPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        subDataPanel.setLayout(new GridLayout(2,2,10,5));
        subDataPanel.add(new JLabel("Tax Income: "));
        subDataPanel.add(new JLabel(Integer.toString(player.getTaxincome())));
        subDataPanel.add(new JLabel("Points: "));
        subDataPanel.add(new JLabel(Integer.toString(player.getPoints())));
        if (player.isCurrentPlayer()) subDataPanel.setBackground(Color.lightGray);
        else subDataPanel.setBackground(Color.white);
        add(subDataPanel);
    }

}

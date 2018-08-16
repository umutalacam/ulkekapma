package com.allybros.UIComponent;

import com.allybros.Game;
import com.allybros.Unit.Player;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class NDataPanel extends JPanel {
    ArrayList<Player> players = new ArrayList<>();
    JPanel playersPanel = new JPanel();

    public NDataPanel(Player ... players){

        for (Player player : players){
            this.players.add(player);
            playersPanel.add(new PlayerNamePanel(player));
        }


        setLayout(new GridLayout(2,1));
        add(playersPanel);
        add(new NPlayerDataPanel(Game.getCurrentPlayer()));


    }

    public void update(){
        playersPanel.removeAll();
        removeAll();
        for (Player player : players){
            playersPanel.add(new PlayerNamePanel(player));
        }
        add(playersPanel);
        add(new NPlayerDataPanel(Game.getCurrentPlayer()));
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

class PlayerNamePanel extends JPanel{

      PlayerNamePanel(Player player){
          JLabel playerName = new JLabel(player.name);
          playerName.setForeground(player.getColor());
          setBorder(new EmptyBorder(5,5,5,5));
          if (player == Game.getCurrentPlayer()) setBackground(Color.WHITE);
          else setBackground(Color.LIGHT_GRAY);
          add(playerName);
      }

}

class NPlayerDataPanel extends JPanel{
    JLabel points;
    JLabel income;
    JLabel id;
    NPlayerDataPanel(Player currentPlayer){
        setBorder(new EmptyBorder(15,15,15,15));
        setLayout(new GridLayout(1,4,15,15));
        add(new JLabel("Points: "+currentPlayer.getPoints()));
        add(new JLabel("Tax: "+currentPlayer.getTaxincome()));
        add(new JLabel("LuckPay: +"+currentPlayer.paid));
        add(new JLabel("Income: +"+(currentPlayer.paid+currentPlayer.getTaxincome())));
    }
}

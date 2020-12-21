package com.allybros.Unit;

import com.allybros.Game;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Player {
    public String name;
    private int id;
    private int points;
    private int taxincome;
    private int colorNumber;
    private static int counter;
    private static ArrayList<Player> players = new ArrayList<>();
    public int paid;
    public boolean haveCountry;

    public Player(String name, int colorNumber){
        counter++;
        this.name = name;
        this.id = counter;
        this.points = 10;
        this.colorNumber = colorNumber;
        this.haveCountry = false;
        players.add(this);
    }

    public Color getColor(){
        switch (this.colorNumber) {
            case 0:
                return Color.RED;
            case 1:
                return Color.BLUE;
            case 2:
                return Color.GREEN;
            case 3:
                return Color.MAGENTA;
            case 4:
                return Color.CYAN;
            case 5:
                return Color.YELLOW;
            case 6:
                return Color.ORANGE;
        }
        return Color.LIGHT_GRAY;
    }

    public int getId() {
        return id;
    }

    public static Player getPlayerById(int id){
        return players.get(id-1);
    }

    public int getTaxincome() {
        return taxincome;
    }

    public int getPoints(){
        return this.points;
    }





    public int newTurnPoints(){
        int newPoints = rollDice() + taxincome;
        this.points += (newPoints);
        paid = newPoints-taxincome;
        return newPoints;
    }


    public void increaseTaxIncome(int value){
        this.taxincome += value;
    }

    public void reduceTaxIncome(int value){
        this.taxincome -= value;
    }

    public boolean spendPoints(int value){
        if (this.points>=value) {
            this.points-=value;
            if(!this.haveCountry){
                this.haveCountry = true;
            }
            return true;
        }else return false;
    }

    public void increasePoints(int value){
        this.points+=value;
    }


    public int rollDice() {
        return (int) (Math.random() * 12);
    }



    public static int numberOfPlayers(){
        return counter;
    }

    public boolean isCurrentPlayer(){
        return this.getId() == Game.getCurrentPlayer().getId();
    }


    public int gameOver(){
        JOptionPane.showMessageDialog(Game.mainWindow,"'"+this.name+"' has been defeated!","Bad news!",JOptionPane.WARNING_MESSAGE);
        players.remove(this);
        counter--;
        Player[] playerArray = new Player[counter];
        for (int i=0;i<counter;i++) {
            playerArray[i]=players.get(i);
        }
        Game.dataPanel.updatePlayers(playerArray);
        Game.dataPanel.update();
        if (counter==1) {
            JOptionPane.showMessageDialog(Game.mainWindow,"'"+players.get(0).name+"'"+" won the game!","Game Over",JOptionPane.WARNING_MESSAGE);
            Game.mapPanel.disableMap();
        }
        return this.points;
    }

}

package com.allybros.Unit;

import com.allybros.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static com.allybros.UIComponent.MapPanel.getMapX;

public class Country extends JButton{
    private int col;
    private int row;
    private int value;
    private Player owner;
    private static ArrayList<Country> countries = new ArrayList<>();

    public Country(int col, int row) {
        this.col = col;
        this.row = row;
        this.value = (int)(Math.random()*20)+10;
        this.owner = null;
        this.setFont(new Font("Arial",Font.BOLD,16));
        this.setText(Integer.toString(value));
        this.setBackground(Color.DARK_GRAY);
        this.setForeground(Color.lightGray);
        Country current = this;
        countries.add(this);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.out.printf("Region: (%d,%d) clicked, getting country: (%d,%d)\n",col,row,getCountryByColRow(col,row).col,getCountryByColRow(col,row).row);
                if (current.owner == Game.getCurrentPlayer()) {
                    String input;
                    input = JOptionPane.showInputDialog(Game.mapPanel,"Increase value:",null);
                    if (input != null) {
                        try {
                            current.invest(Game.getCurrentPlayer(),Integer.parseInt(input));
                        }catch (Exception ex){
                            JOptionPane.showMessageDialog(null,"Please enter numeric values","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }else {
                    int answer = JOptionPane.showConfirmDialog(Game.mapPanel,"Are you sure you want to capture this region?\nIt will cost you "+value+" points","Capture",JOptionPane.YES_NO_OPTION);
                    if (answer==0) current.take(Game.getCurrentPlayer());
                }
            }
        });
    }


    public void take(Player owner) {
        int tax;
        if (owner.spendPoints(this.value)){
            tax = (int) (0.1*this.value);
            if (this.owner != null) {
                this.owner.reduceTaxIncome(tax);
                if (this.owner.getTaxincome() == 0) {
                    owner.increasePoints(this.owner.getPoints());
                    this.owner.gameOver();
                }
            }

            this.owner = owner;
            this.value *=2;
            tax = (int) (0.1*this.value);
            owner.increaseTaxIncome(tax);
            update();
            Game.dataPanel.update();
        }else {
            JOptionPane.showMessageDialog(Game.mainWindow,"Insufficent points","Error",JOptionPane.ERROR_MESSAGE);
        }

    }

    public void invest(Player player, int value){
        int tax;
        if (player.spendPoints(value)){
            tax = (int) (0.1*this.value);
            player.reduceTaxIncome(tax);
            this.value+=value;
            tax = (int) (0.1*this.value);
            player.increaseTaxIncome(tax);
            update();
            Game.dataPanel.update();
        }else {
            JOptionPane.showMessageDialog(Game.mainWindow,"Insufficent points","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    public void update(){
        this.setText(Integer.toString(value));
        this.setBackground(owner.getColor());
        this.setForeground(Color.BLACK);
    }

    public int getValue() {
        return value;
    }

    public static Country getCountryByColRow(int col,int row){
        if (col<0||row<0) return null;
        int index;
        index = col + ((getMapX()) * row);
        return countries.get(index);
    }


    public static boolean isBounds(Player player, Country country){
        boolean isbounds = false;
        int col = country.col;
        int row = country.row;
        if (getCountryByColRow(col-1,row-1).owner==player) isbounds = true;
        return isbounds;
    }

}

package com.allybros.UIComponent;

import com.allybros.Unit.Country;

import javax.swing.*;
import java.awt.*;

public class MapPanel extends JPanel {

    Country[][] countries;
    private static int x;
    private static int y;

    public MapPanel(int x, int y) {

        MapPanel.x = x;
        MapPanel.y = y;

        GridLayout layout = new GridLayout(y,x); //Create layout
        countries = new Country[x][y];
        setLayout(layout);

        for (int i = 0; i < y; i++){
            for (int j= 0 ; j < x; j++) {
                countries[j][i] = new Country(j,i);
                add(countries[j][i]);
            }
        }
    }

    public void disableMap(){
        for (int i = 0; i < y ; i++){
            for (int j= 0 ; j < x; j++) {
                countries[j][i].setEnabled(false);
            }
        }
    }


    public static int getMapX() {
        return x;
    }


    public static int getMapY() {
        return y;
    }
}

package com.team7.view;
import static java.lang.Math.*;


public class HexMechanical {
    public static float calculateHeight(float side){
      return Float.parseFloat(String.valueOf(Math.sin(DegreesToRadians(30))*side));

    }
    public static float calculateRadius(float side){
        return Float.parseFloat(String.valueOf(cos(DegreesToRadians(30))*side));
    }
    public static double DegreesToRadians(double degrees)
    {
        return degrees * Math.PI / 180;
    }
}

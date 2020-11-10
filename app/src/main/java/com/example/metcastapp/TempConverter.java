package com.example.metcastapp;

import java.lang.Math;

public class TempConverter {
    final double KELVIN_T = 273.15;
    public String convertToCelsium(float tempK){
        int tempC = (int) Math.round(tempK-273.15);
        return String.valueOf(tempC);
    }
}

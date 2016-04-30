package com.example.xinzhang.pastoralmate;

/**
 * Created by xinzhang on 24/04/16.
 */
public class MessageReturnedLocation {
    private double x;
    private double y;



    private String z;
    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public MessageReturnedLocation(double x, double y, String z){
        this.x = x;
        this.y = y;
        this.z = z;
    }
}


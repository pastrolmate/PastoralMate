package com.example.xinzhang.pastoralmate;

/**
 * Created by xinzhang on 24/04/16.
 */
public class MessageDetails {
    private String iWater;
    private String iGrass;
    private String iOrigin;
    public MessageDetails (String iWater, String iGrass, String iOrigin){
        this.iWater = iWater;
        this.iGrass = iGrass;
        this.iOrigin = iOrigin;
    }

    public String getiWater() {
        return iWater;
    }

    public void setiWater(String iWater) {
        this.iWater = iWater;
    }

    public String getiGrass() {
        return iGrass;
    }

    public void setiGrass(String iGrass) {
        this.iGrass = iGrass;
    }

    public String getiOrigin() {
        return iOrigin;
    }

    public void setiOrigin(String iOrigin) {
        this.iOrigin = iOrigin;
    }
}

package com.example.xinzhang.pastoralmate;

import java.util.ArrayList;

/**
 * Created by xinzhang on 24/04/16.
 */
public class MessageBestLocs {
    private ArrayList<MessageReturnedLocation> locations;
    public ArrayList<MessageReturnedLocation> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<MessageReturnedLocation> locations) {
        this.locations = locations;
    }


    public MessageBestLocs(ArrayList<MessageReturnedLocation> locations){
        locations = new ArrayList<>();
        for(MessageReturnedLocation l : locations){
            locations.add(l);
        }
    }

}

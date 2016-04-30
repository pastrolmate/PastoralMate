package com.example.xinzhang.pastoralmate;

/**
 * Created by xinzhang on 24/04/16.
 */
public class MessageAskLocation {
    private String command;
    private MessageLocation location;
    public MessageAskLocation(String command, MessageLocation location) {
        this.command = command;
        this.location = location;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public MessageLocation getLocation() {
        return location;
    }

    public void setLocation(MessageLocation location) {
        this.location = location;
    }
}

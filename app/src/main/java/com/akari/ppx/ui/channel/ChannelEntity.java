package com.akari.ppx.ui.channel;


public class ChannelEntity {
    private String mName;

    public String getPureName() {
        int index = mName.indexOf('|');
        return index != -1 ? mName.substring(0, index) : mName;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }
}

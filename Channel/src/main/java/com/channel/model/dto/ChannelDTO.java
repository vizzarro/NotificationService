package com.channel.model.dto;

public class ChannelDTO {
    private int id;
    private String name;
    private String info;
    private int numSub;
    public  ChannelDTO(){}

    public ChannelDTO(String name, String info, int numSub){
        this.name=name;
        this.info=info;
        this.numSub=numSub;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public int getNumSub() {
        return numSub;
    }

    public void setNumSub(int numSub) {
        this.numSub = numSub;
    }
}

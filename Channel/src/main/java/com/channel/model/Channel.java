package com.channel.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "channel")
public class Channel {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String info;
    private int numSub;

    public  Channel(){

    }

    public Channel(String name, String info, int numSub){
        this.name=name;
        this.info=info;
        this.numSub=numSub;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

package com.company;
public class device {
     private final String name;
    private final String type;
    public device(String name, String type){
        this.name = name;
        this.type = type;
    }

    public String getDevice() {
        return name;
    }
    public String getType() {
        return type;
    }
}

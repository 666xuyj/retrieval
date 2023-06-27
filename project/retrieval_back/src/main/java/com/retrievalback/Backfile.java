package com.retrievalback.entity;

import java.util.ArrayList;
import java.util.Map;

public class Backfile {
    private String path;
    private ArrayList<String> infos = new ArrayList<String>();
    public Backfile(){

    }

    public Backfile(String path, ArrayList<String> infos) {
        this.path = path;
        this.infos = infos;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public void setInfos(ArrayList<String> infos) {
        this.infos = infos;
    }

    public ArrayList<String> getInfos() {
        return this.infos;
    }

    public void setInfo(String value){
        this.infos.add(value);
    }


}

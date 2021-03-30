package com.example.konaking;

import java.util.HashMap;
import java.util.Map;

public class NoticeData {
    HashMap<String,String> map=new HashMap<>();
    String Position="";
    public String getPosition(){
        return Position;
    }
    public void setPosition(String position){
        Position=position;
    }
    public void PositionClear(){ String Position=""; }

    public void setMap(String key,String Content){
        map.put(key,Content);
    }
    public String getMap(String key){
        return map.get(key);
    }
    public void ClearMap(){
        map.clear();
    }
}

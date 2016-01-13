package com.example.abhi.simulation;

/**
 * Created by Abhi on 10-Jan-16.
 */
public class Row {
    private String name;
    private int pic_id;
    private String status;

    public Row(String name,int pic_id,String status){
        this.name=name;
        this.pic_id=pic_id;
        this.status=status;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }

    public void setPic_id(int pic_id){
        this.pic_id=pic_id;
    }

    public int getPic_id(){
        return this.pic_id;
    }

    public void setStatus(String status){
        this.status=status;
    }

    public String getStatus(){
        return this.status;
    }
}

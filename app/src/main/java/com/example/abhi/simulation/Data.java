package com.example.abhi.simulation;

/**
 * Created by Abhi on 12-Jan-16.
 */
public class Data {
    int id;
    String Name;
    int age;
    String gender;
    String interests[];

    public Data(){

    }

    public Data(int id,String Name,int age,String gender,String interests[]){
        this.id=id;
        this.Name=Name;
        this.age=age;
        this.gender=gender;
        this.interests=interests;
    }
    public Data(String Name,int age,String gender,String interests[]){
        this.Name=Name;
        this.age=age;
        this.gender=gender;
        this.interests=interests;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id=id;
    }

    public int getAge(){
        return this.age;
    }

    public void setAge(int Age){
        this.age=Age;
    }

    public String getName(){
        return this.Name;
    }

    public void setName(String Name){
        this.Name=Name;
    }

    public String getGender(){
        return this.gender;
    }

    public void setGender(String gender){
        this.gender=gender;
    }

    public String[] getInterests(){
        return this.interests;
    }

    public void setInterests(String[] id){
        this.interests=id;
    }
}

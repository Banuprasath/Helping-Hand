package com.example.cardview.Pg7;

public class RecyclerData {
    private int id;
    private String fname;
    public RecyclerData(int id,String fname){
        this.id=id;
        this.fname=fname;
    }
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }
    public String getFname(){
        return this.fname;
    }
    public void setFname(String fname){
        this.fname=fname;
    }

}

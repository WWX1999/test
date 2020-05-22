package com.example.test.model;

public class SpecialModel {
    private int id;
    private String pakageType;
    private float weight;

    public SpecialModel(){

    }
    public SpecialModel(int id,String pakageType,float weight){
        setId(id);
        setPakageType(pakageType);
        setWeight(weight);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPakageType() {
        return pakageType;
    }

    public void setPakageType(String pakageType) {
        this.pakageType = pakageType;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "SpecialModel{" +
                "id=" + id +
                ", pakageType='" + pakageType + '\'' +
                ", weight=" + weight +
                '}';
    }
}

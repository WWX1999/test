package com.example.test.model;

public class PackageModel {
    private int id;
    private float x;
    private float y;
    private float z;
    private float weight;

    public PackageModel(){

    }

    public PackageModel(int id, int x, int y, int z, float weight) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.z = z;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "PackageModel{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", weight=" + weight +
                '}';
    }
}

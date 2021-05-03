package com.m2comm.test.clone_test;

import java.util.ArrayList;

public class A implements Cloneable {
    private String name;
    private String age;
    private ArrayList<Data>data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public static class Data implements Cloneable{
        public Data(String address) {
            this.address = address;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "address='" + address + '\'' +
                    '}';
        }

        public String address;

        public Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "A{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}

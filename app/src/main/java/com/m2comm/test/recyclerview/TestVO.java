package com.m2comm.test.recyclerview;

public class TestVO {
    private String imageURL;
    private int count;

    public TestVO(String imageURL, int count) {
        this.imageURL = imageURL;
        this.count = count;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

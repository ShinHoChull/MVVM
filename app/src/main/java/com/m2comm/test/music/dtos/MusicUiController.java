package com.m2comm.test.music.dtos;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class MusicUiController implements Parcelable {

    public Uri uri;
    public String title;
    public String singerName;
    public Bitmap bitmap;


    public MusicUiController(Uri uri, String title, String singerName, Bitmap bitmap) {
        this.uri = uri;
        this.title = title;
        this.singerName = singerName;
        this.bitmap = bitmap;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public Uri getUri() {
        return uri;
    }

    public String getTitle() {
        return title;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    //Uri uri, String title, String singerName, Bitmap bitmap
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.uri,flags);
        dest.writeString(this.title);
        dest.writeString(this.singerName);
        dest.writeParcelable(this.bitmap,flags);
    }

    protected MusicUiController(Parcel parcel) {
        // must be same order with writeToParcel()
        /**
         *전역변수 classLoader는 null값이다.대신
         * ClassLoader.getSystemClassLoader 값을 넣어주면 파서블 객체를 가져올수있음./
         * 그리고 반드시 생성자 순서대로 직렬화 시켜야함. * 까먹지 맙시다..
         * */
        //this.uri = parcel.readParcelable(this.uri.getClass().getClassLoader());

        this.uri = parcel.readParcelable(ClassLoader.getSystemClassLoader());
        this.title = parcel.readString();
        this.singerName = parcel.readString();
        this.bitmap = parcel.readParcelable(ClassLoader.getSystemClassLoader());
    }

    // create Parcelable
    public static final Parcelable.Creator<MusicUiController> CREATOR = new Parcelable.Creator<MusicUiController>() {
        @Override
        public MusicUiController createFromParcel(Parcel parcel) {
            return new MusicUiController(parcel);
        }
        @Override
        public MusicUiController[] newArray(int size) {
            return new MusicUiController[size];
        }
    };
}

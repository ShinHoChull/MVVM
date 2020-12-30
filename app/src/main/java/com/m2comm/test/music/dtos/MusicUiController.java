package com.m2comm.test.music.dtos;

import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class MusicUiController {
    public Uri uri;
    public MediaMetadataRetriever retriever;

    public MusicUiController(Uri uri, MediaMetadataRetriever retriever) {
        this.uri = uri;
        this.retriever = retriever;
    }

}

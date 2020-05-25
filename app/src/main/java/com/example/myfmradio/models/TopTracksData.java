package com.example.myfmradio.models;

import java.util.List;
import com.squareup.moshi.Json;

import org.w3c.dom.Attr;

public class TopTracksData {

    @Json(name = "@attr")
    private Attr attr;
    @Json(name = "track")
    private List<TrackData> track = null;

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }

    public TopTracksData withAttr(Attr attr) {
        this.attr = attr;
        return this;
    }

    public List<TrackData> getTrack() {
        return track;
    }

    public void setTrack(List<TrackData> track) {
        this.track = track;
    }

    public TopTracksData withTrack(List<TrackData> track) {
        this.track = track;
        return this;
    }

}

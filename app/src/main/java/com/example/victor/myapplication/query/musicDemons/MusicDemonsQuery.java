package com.example.victor.myapplication.query.musicDemons;

interface Query {
    void done(MusicDemonsSongWithLyricsResponse mdswlr);
}

public class MusicDemonsQuery implements Query {
    private String trackName;
    private String artist;
    public MusicDemonsQuery(String trackName, String artist) {
        this.trackName = trackName;
        this.artist = artist;
    }

    public String getTrackName() {
        return trackName;
    }

    public String getArtist() {
        return artist;
    }

    public void done(MusicDemonsSongWithLyricsResponse mdswlr) {

    }
}

package com.example.victor.myapplication.query.musicDemons;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

class MusixmatchResponse {
    Header header;
    JsonElement body;
    class Header {
        int status_code;
        double execute_time;
        int available;
    }
}

class ErrorResponseBody {
    String msg;
}

class TrackSearchBody {
    Track[] track_list;
    class Track {
        int track_id;
        int commontrack_id;
        int has_lyrics;
    }
}

class MatcherLyricsBody {
    int lyrics_id;
    String lyrics_body;
}

class MusixmatchResponseDeserializer implements JsonDeserializer<MusixmatchResponse> {
    public MusixmatchResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        MusixmatchResponse response = new MusixmatchResponse();
        JsonObject jsonObject = json.getAsJsonObject();
        JsonObject message = jsonObject.getAsJsonObject("message");
        response.header = context.deserialize(message.getAsJsonObject("header"), MusixmatchResponse.Header.class);
        response.body = message.get("body");
        return response;
    }
}

class TrackSearchBodyDeserializer implements JsonDeserializer<TrackSearchBody> {
    public TrackSearchBody deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        TrackSearchBody body = new TrackSearchBody();
        JsonArray trackList = json.getAsJsonObject().getAsJsonArray("track_list");
        body.track_list = new TrackSearchBody.Track[trackList.size()];
        for (int i = 0; i < trackList.size(); i++) {
            body.track_list[i] = context.deserialize(trackList.get(i), TrackSearchBody.Track.class);
        }
        return body;
    }
}

class MatcherLyricsBodyDeserializer implements JsonDeserializer<MatcherLyricsBody> {
    public MatcherLyricsBody deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        MatcherLyricsBody matcherLyricsBody = new MatcherLyricsBody();
        JsonObject lyrics = json.getAsJsonObject().getAsJsonObject("lyrics");
        matcherLyricsBody.lyrics_body = lyrics.get("lyrics_body").getAsString();
        matcherLyricsBody.lyrics_id = lyrics.get("lyrics_id").getAsInt();
        return matcherLyricsBody;
    }
}

package com.example.victor.myapplication.query.musixmatch;

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

class TrackSearchBody {
    Track[] track_list;
    class Track {
        int track_id;
        int commontrack_id;
        int has_lyrics;
    }
}

class ErrorResponseBody {
    String msg;
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

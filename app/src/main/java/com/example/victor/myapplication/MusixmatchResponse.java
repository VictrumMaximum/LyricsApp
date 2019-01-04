package com.example.victor.myapplication;

class MusixmatchResponse {
    Message message;
    class Message {
        Header header;
        Body body;
        class Header {
            int status_code;
            double execute_time;
            int available;
        }
        class Body {
            Track[] track_list;
            class Track {
                ActualTrack track;
                class ActualTrack {
                    int track_id;
                    int commontrack_id;
                    int has_lyrics;
                }
            }
        }
    }
}

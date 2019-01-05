package com.example.victor.myapplication.query.apiSeeds;

public class ApiSeedsResponse {
    Result result;
    class Result {
        Artist artist;
        Track track;
        Copyright copyright;
        String probability;
        String similarity;
        class Artist {
            String name;
        }
        class Track {
            String name;
            String text;
            Lang lang;
            class Lang {
                String code;
                String name;
            }
        }
        class Copyright {
            String notice;
            String artist;
            String text;
        }
    }
}

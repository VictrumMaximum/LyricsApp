package com.example.victor.myapplication.query.apiSeeds;

public class ApiSeedsResponse {
    public Result result;
    public class Result {
        public Artist artist;
        public Track track;
        Copyright copyright;
        String probability;
        String similarity;
        public class Artist {
            public String name;
        }
        public class Track {
            public String name;
            public String text;
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

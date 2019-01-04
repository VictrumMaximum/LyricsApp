package com.example.victor.myapplication.query.musixmatch;

import android.os.AsyncTask;

import com.example.victor.myapplication.MainActivity;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

public class LyricsFetcher extends AsyncTask<Void, Void, MusixmatchResponse> {
    private WeakReference<MainActivity> activity;
    private MusixmatchQuery mmq;
    private static final String baseURL = "https://api.musixmatch.com/ws/1.1/";
    private static final String errorString = "{\"message\":{\"header\":{\"status_code\":401,\"execute_time\":0.00031113624572754},\"body\":\"\"}}";
    private static final String testString = "{\"message\":{\"header\":{\"status_code\":200,\"execute_time\":0.024497985839844,\"available\":16},\"body\":{\"track_list\":[{\"track\":{\"track_id\":87256617,\"track_name\":\"Sultans of Swing\",\"track_name_translation_list\":[],\"track_rating\":1,\"commontrack_id\":49080546,\"instrumental\":0,\"explicit\":1,\"has_lyrics\":1,\"has_subtitles\":1,\"has_richsync\":0,\"num_favourite\":0,\"album_id\":21090007,\"album_name\":\"Italian Dire Straits Live At Blue Note\",\"artist_id\":27386821,\"artist_name\":\"Italian Dire Straits\",\"track_share_url\":\"https:\\/\\/www.musixmatch.com\\/lyrics\\/Italian-Dire-Straits\\/Sultans-of-Swing?utm_source=application&utm_campaign=api&utm_medium=5+KANELOPOYLOY%3A1409608335821\",\"track_edit_url\":\"https:\\/\\/www.musixmatch.com\\/lyrics\\/Italian-Dire-Straits\\/Sultans-of-Swing\\/edit?utm_source=application&utm_campaign=api&utm_medium=5+KANELOPOYLOY%3A1409608335821\",\"restricted\":0,\"updated_time\":\"2016-03-13T13:27:55Z\",\"primary_genres\":{\"music_genre_list\":[]}}},{\"track\":{\"track_id\":14385789,\"track_name\":\"Sultans of Swing\",\"track_name_translation_list\":[],\"track_rating\":68,\"commontrack_id\":61734,\"instrumental\":0,\"explicit\":1,\"has_lyrics\":1,\"has_subtitles\":1,\"has_richsync\":1,\"num_favourite\":3537,\"album_id\":12878934,\"album_name\":\"Dire Straits\",\"artist_id\":134,\"artist_name\":\"Dire Straits\",\"track_share_url\":\"https:\\/\\/www.musixmatch.com\\/lyrics\\/Dire-Straits\\/Sultans-of-Swing?utm_source=application&utm_campaign=api&utm_medium=5+KANELOPOYLOY%3A1409608335821\",\"track_edit_url\":\"https:\\/\\/www.musixmatch.com\\/lyrics\\/Dire-Straits\\/Sultans-of-Swing\\/edit?utm_source=application&utm_campaign=api&utm_medium=5+KANELOPOYLOY%3A1409608335821\",\"restricted\":0,\"updated_time\":\"2018-05-23T02:23:34Z\",\"primary_genres\":{\"music_genre_list\":[{\"music_genre\":{\"music_genre_id\":21,\"music_genre_parent_id\":34,\"music_genre_name\":\"Rock\",\"music_genre_name_extended\":\"Rock\",\"music_genre_vanity\":\"Rock\"}}]}}},{\"track\":{\"track_id\":36135931,\"track_name\":\"Sultans Of Swing\",\"track_name_translation_list\":[],\"track_rating\":25,\"commontrack_id\":14919547,\"instrumental\":0,\"explicit\":1,\"has_lyrics\":1,\"has_subtitles\":1,\"has_richsync\":0,\"num_favourite\":18,\"album_id\":15652137,\"album_name\":\"The Music Of Dire Straits\",\"artist_id\":25002887,\"artist_name\":\"Sultans Of Swing (dire Straits Sound-a-like)\",\"track_share_url\":\"https:\\/\\/www.musixmatch.com\\/lyrics\\/Sultans-Of-Swing-dire-Straits-Sound-a-like\\/Sultans-Of-Swing?utm_source=application&utm_campaign=api&utm_medium=5+KANELOPOYLOY%3A1409608335821\",\"track_edit_url\":\"https:\\/\\/www.musixmatch.com\\/lyrics\\/Sultans-Of-Swing-dire-Straits-Sound-a-like\\/Sultans-Of-Swing\\/edit?utm_source=application&utm_campaign=api&utm_medium=5+KANELOPOYLOY%3A1409608335821\",\"restricted\":0,\"updated_time\":\"2015-06-22T02:21:25Z\",\"primary_genres\":{\"music_genre_list\":[{\"music_genre\":{\"music_genre_id\":14,\"music_genre_parent_id\":34,\"music_genre_name\":\"Pop\",\"music_genre_name_extended\":\"Pop\",\"music_genre_vanity\":\"Pop\"}}]}}},{\"track\":{\"track_id\":37114982,\"track_name\":\"Sultans of Swing (In the Style of Dire Straits) [Karaoke Version]\",\"track_name_translation_list\":[],\"track_rating\":37,\"commontrack_id\":15405084,\"instrumental\":0,\"explicit\":0,\"has_lyrics\":0,\"has_subtitles\":0,\"has_richsync\":0,\"num_favourite\":0,\"album_id\":15718261,\"album_name\":\"A Tribute to 70's Hits, Vol. 1 (Karaoke Version)\",\"artist_id\":134,\"artist_name\":\"Dire Straits\",\"track_share_url\":\"https:\\/\\/www.musixmatch.com\\/lyrics\\/Dire-Straits\\/Sultans-of-Swing-In-the-Style-of-Dire-Straits-Karaoke-Version?utm_source=application&utm_campaign=api&utm_medium=5+KANELOPOYLOY%3A1409608335821\",\"track_edit_url\":\"https:\\/\\/www.musixmatch.com\\/lyrics\\/Dire-Straits\\/Sultans-of-Swing-In-the-Style-of-Dire-Straits-Karaoke-Version\\/edit?utm_source=application&utm_campaign=api&utm_medium=5+KANELOPOYLOY%3A1409608335821\",\"restricted\":0,\"updated_time\":\"2013-12-24T19:24:01Z\",\"primary_genres\":{\"music_genre_list\":[]}}},{\"track\":{\"track_id\":157975833,\"track_name\":\"Sultans of Swing - Live at Live Aid, Wembley Stadium, 13th July 1985\",\"track_name_translation_list\":[],\"track_rating\":25,\"commontrack_id\":87011244,\"instrumental\":0,\"explicit\":1,\"has_lyrics\":1,\"has_subtitles\":1,\"has_richsync\":0,\"num_favourite\":0,\"album_id\":30081880,\"album_name\":\"Live Aid (Live, 13th July 1985)\",\"artist_id\":134,\"artist_name\":\"Dire Straits\",\"track_share_url\":\"https:\\/\\/www.musixmatch.com\\/lyrics\\/Dire-Straits\\/Sultans-of-Swing-Live-at-Live-Aid-Wembley-Stadium-13th-July-1985?utm_source=application&utm_campaign=api&utm_medium=5+KANELOPOYLOY%3A1409608335821\",\"track_edit_url\":\"https:\\/\\/www.musixmatch.com\\/lyrics\\/Dire-Straits\\/Sultans-of-Swing-Live-at-Live-Aid-Wembley-Stadium-13th-July-1985\\/edit?utm_source=application&utm_campaign=api&utm_medium=5+KANELOPOYLOY%3A1409608335821\",\"restricted\":0,\"updated_time\":\"2018-09-08T14:21:01Z\",\"primary_genres\":{\"music_genre_list\":[]}}},{\"track\":{\"track_id\":84791025,\"track_name\":\"Sultans Of Swing - Live At The BBC\",\"track_name_translation_list\":[],\"track_rating\":26,\"commontrack_id\":47700196,\"instrumental\":0,\"explicit\":1,\"has_lyrics\":1,\"has_subtitles\":1,\"has_richsync\":0,\"num_favourite\":26,\"album_id\":20929315,\"album_name\":\"Live At The BBC\",\"artist_id\":134,\"artist_name\":\"Dire Straits\",\"track_share_url\":\"https:\\/\\/www.musixmatch.com\\/lyrics\\/Dire-Straits\\/Sultans-Of-Swing-Live-At-The-BBC?utm_source=application&utm_campaign=api&utm_medium=5+KANELOPOYLOY%3A1409608335821\",\"track_edit_url\":\"https:\\/\\/www.musixmatch.com\\/lyrics\\/Dire-Straits\\/Sultans-Of-Swing-Live-At-The-BBC\\/edit?utm_source=application&utm_campaign=api&utm_medium=5+KANELOPOYLOY%3A1409608335821\",\"restricted\":0,\"updated_time\":\"2015-12-30T05:58:35Z\",\"primary_genres\":{\"music_genre_list\":[]}}},{\"track\":{\"track_id\":134758902,\"track_name\":\"Sultans of Swing (Redux)\",\"track_name_translation_list\":[],\"track_rating\":1,\"commontrack_id\":11604993,\"instrumental\":0,\"explicit\":1,\"has_lyrics\":1,\"has_subtitles\":1,\"has_richsync\":1,\"num_favourite\":8,\"album_id\":16208518,\"album_name\":\"Sultans of Swing - Live in Germany\",\"artist_id\":134,\"artist_name\":\"Dire Straits\",\"track_share_url\":\"https:\\/\\/www.musixmatch.com\\/lyrics\\/Dire-Straits\\/Sultans-of-Swing-Redux?utm_source=application&utm_campaign=api&utm_medium=5+KANELOPOYLOY%3A1409608335821\",\"track_edit_url\":\"https:\\/\\/www.musixmatch.com\\/lyrics\\/Dire-Straits\\/Sultans-of-Swing-Redux\\/edit?utm_source=application&utm_campaign=api&utm_medium=5+KANELOPOYLOY%3A1409608335821\",\"restricted\":0,\"updated_time\":\"2014-03-17T10:35:26Z\",\"primary_genres\":{\"music_genre_list\":[{\"music_genre\":{\"music_genre_id\":21,\"music_genre_parent_id\":34,\"music_genre_name\":\"Rock\",\"music_genre_name_extended\":\"Rock\",\"music_genre_vanity\":\"Rock\"}}]}}},{\"track\":{\"track_id\":149074934,\"track_name\":\"Sultans of Swing - Acoustic Cover\",\"track_name_translation_list\":[],\"track_rating\":26,\"commontrack_id\":82854321,\"instrumental\":0,\"explicit\":0,\"has_lyrics\":0,\"has_subtitles\":0,\"has_richsync\":0,\"num_favourite\":0,\"album_id\":28689262,\"album_name\":\"Relaxing Classical Playlist: Chilled and Pleasant Mornings\",\"artist_id\":35812534,\"artist_name\":\"Dire Straits feat. Gregory Alley\",\"track_share_url\":\"https:\\/\\/www.musixmatch.com\\/lyrics\\/Dire-Straits-feat-Gregory-Alley\\/Sultans-of-Swing-Acoustic-Cover?utm_source=application&utm_campaign=api&utm_medium=5+KANELOPOYLOY%3A1409608335821\",\"track_edit_url\":\"https:\\/\\/www.musixmatch.com\\/lyrics\\/Dire-Straits-feat-Gregory-Alley\\/Sultans-of-Swing-Acoustic-Cover\\/edit?utm_source=application&utm_campaign=api&utm_medium=5+KANELOPOYLOY%3A1409608335821\",\"restricted\":0,\"updated_time\":\"2018-04-18T16:56:21Z\",\"primary_genres\":{\"music_genre_list\":[]}}},{\"track\":{\"track_id\":85942102,\"track_name\":\"Sultans Of Swing - Live At The Royal Albert Hall\",\"track_name_translation_list\":[],\"track_rating\":1,\"commontrack_id\":48362692,\"instrumental\":0,\"explicit\":1,\"has_lyrics\":1,\"has_subtitles\":1,\"has_richsync\":0,\"num_favourite\":0,\"album_id\":21004798,\"album_name\":\"Sultans Of Swing - The Very Best Of Dire Straits\",\"artist_id\":134,\"artist_name\":\"Dire Straits\",\"track_share_url\":\"https:\\/\\/www.musixmatch.com\\/lyrics\\/Dire-Straits\\/Sultans-Of-Swing-Live-At-The-Royal-Albert-Hall?utm_source=application&utm_campaign=api&utm_medium=5+KANELOPOYLOY%3A1409608335821\",\"track_edit_url\":\"https:\\/\\/www.musixmatch.com\\/lyrics\\/Dire-Straits\\/Sultans-Of-Swing-Live-At-The-Royal-Albert-Hall\\/edit?utm_source=application&utm_campaign=api&utm_medium=5+KANELOPOYLOY%3A1409608335821\",\"restricted\":0,\"updated_time\":\"2016-04-11T03:40:15Z\",\"primary_genres\":{\"music_genre_list\":[]}}},{\"track\":{\"track_id\":99521980,\"track_name\":\"Sultans Of Swing - Live At Hammersmith Odeon\",\"track_name_translation_list\":[],\"track_rating\":25,\"commontrack_id\":54903209,\"instrumental\":0,\"explicit\":1,\"has_lyrics\":1,\"has_subtitles\":1,\"has_richsync\":0,\"num_favourite\":0,\"album_id\":22015082,\"album_name\":\"Alchemy - Dire Straits Live - 1 & 2\",\"artist_id\":134,\"artist_name\":\"Dire Straits\",\"track_share_url\":\"https:\\/\\/www.musixmatch.com\\/lyrics\\/Dire-Straits\\/Sultans-Of-Swing-Live-At-Hammersmith-Odeon?utm_source=application&utm_campaign=api&utm_medium=5+KANELOPOYLOY%3A1409608335821\",\"track_edit_url\":\"https:\\/\\/www.musixmatch.com\\/lyrics\\/Dire-Straits\\/Sultans-Of-Swing-Live-At-Hammersmith-Odeon\\/edit?utm_source=application&utm_campaign=api&utm_medium=5+KANELOPOYLOY%3A1409608335821\",\"restricted\":0,\"updated_time\":\"2016-04-11T03:40:14Z\",\"primary_genres\":{\"music_genre_list\":[]}}}]}}}";

    public LyricsFetcher(WeakReference<MainActivity> activity, MusixmatchQuery mmq) {
        this.activity = activity;
        this.mmq = mmq;
    }

    @Override
    protected void onPreExecute() {
        System.out.println("Pre Execute");
    }

    @Override
    protected MusixmatchResponse doInBackground(Void... voids) {
        System.out.println("Do In Background");
        StringBuilder sb = new StringBuilder();
        sb.append(baseURL).append(mmq.getType()).append("?");
        Map<String, String> params = mmq.getParams();
        Set<String> keys = params.keySet();
        for (String key: keys) {
            sb.append("&").append(key).append("=").append(params.get(key));
        }
        System.out.println(sb.toString());
        try {
            URL url = new URL(sb.toString());
            // default settings are ok.
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
//            InputStream inputStream = httpCon.getInputStream();
            InputStream inputStream = new ByteArrayInputStream(testString.getBytes("UTF-8"));
            InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
//            System.out.println(new BufferedReader(reader).readLine());
            MusixmatchResponse response = new GsonBuilder()
                    .registerTypeAdapter(MusixmatchResponse.class, new MusixmatchResponseDeserializer()).create()
                    .fromJson(reader, MusixmatchResponse.class);
            reader.close();
            inputStream.close();
            return response; // TODO : change back to response
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(MusixmatchResponse mmr) {
        System.out.println("On Post Execute");
        mmq.success(mmr);
    }
}

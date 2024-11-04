package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Artist> artistList = new ArrayList<>();
    public static List<Song> songs = new ArrayList<>();

    @PostConstruct
    public void init() {
        artistList.add(new Artist(1L, "Ariana", "Grande", "Arian Grande Bio"));
        artistList.add(new Artist(2L, "Ed", "Sheeran", "Ed Sheeran Bio"));
        artistList.add(new Artist(3L, "Taylor", "Swift", "Taylor Swift Bio"));
        artistList.add(new Artist(4L, "Bruno", "Mars", "Bruno Mars Bio"));
        artistList.add(new Artist( 5L, "Billie", "Eilish", "Billie Eilish Bio"));

        songs.add(new Song("001", "7 rings", "Pop", 2019));
        songs.add(new Song("002", "Shape of You", "Pop", 2017));
        songs.add(new Song("003", "Blank Space", "Pop", 2014));
        songs.add(new Song("004", "Uptown Funk", "Funk", 2014));
        songs.add(new Song("005", "Bad Guy", "Pop", 2019));
    }
}
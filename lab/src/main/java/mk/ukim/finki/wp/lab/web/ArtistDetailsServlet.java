package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.ArtistServiceImpl;
import mk.ukim.finki.wp.lab.service.SongServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ArtistDetailsServlet", urlPatterns = "/artistDetails")
public class ArtistDetailsServlet extends HttpServlet {
    private final SpringTemplateEngine templateEngine;
    private final SongServiceImpl songService;
    private final ArtistServiceImpl artistService;

    public ArtistDetailsServlet(SpringTemplateEngine templateEngine, SongServiceImpl songService, ArtistServiceImpl artistService) {
        this.templateEngine = templateEngine;
        this.songService = songService;
        this.artistService = artistService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String artistId = req.getParameter("artistRadio");
        Artist a = artistService.findById(Long.parseLong(artistId));

        List<Song> songList = songService.listSongs();
        List<Song> songs = new ArrayList<>();
        for (Song song : songList) {
            for (Artist artist : song.getPerformers()) {
                if (artist.getId() == a.getId()) {
                    songs.add(song);
                }
            }
        }

        IWebExchange iWebExchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(iWebExchange);
        context.setVariable("entity", a);
        context.setVariable("songList", songs);
        templateEngine.process("artistDetails.html", context, resp.getWriter());
    }
}

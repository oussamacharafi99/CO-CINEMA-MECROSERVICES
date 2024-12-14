package co.cinema.filmservice.sevices;

import co.cinema.filmservice.models.Film;
import co.cinema.filmservice.repositories.FilmRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }

    public Map<String, String> createFilm(Film film){
        Map<String, String> response = new HashMap<>();
        try{
            filmRepository.save(film);
            response.put("status", "success");
            response.put("message", "The film added succeeded!");
        }catch ( Exception ex){
            System.out.println("error"+ ex.getMessage());
            response.put("status", "error");
            response.put("message", "The film isn't added  !");
        }
        return response;
    }

    public Map<String, String> updateFilm(Integer id, Film film) {
        Map<String, String> response = new HashMap<>();
        Film newFilm = filmRepository.findById(id).orElseThrow();
        try {
            newFilm.setName(film.getName());
            newFilm.setDescription(film.getDescription());
            newFilm.setMovieDuration(film.getMovieDuration());
            newFilm.setMovieTime(film.getMovieTime());
            newFilm.setMovieDate(film.getMovieDate());
            newFilm.setPrice(film.getPrice());
            newFilm.setSeatsNumber(film.getSeatsNumber());

            filmRepository.save(newFilm);
            response.put("status", "success");
            response.put("message", "The film is updated !");
        } catch (Exception ex) {
            System.out.println("error" + ex.getMessage());
            response.put("status", "error");
            response.put("message", "The film isn't update !");
        }
        return response;
    }

    public Map<String, String> deleteFilm(Integer id) {
        Map<String, String> response = new HashMap<>();
        try {
            filmRepository.deleteById(id);
            response.put("status", "success");
            response.put("message", "The film is deleted !");
        } catch (Exception ex) {
            System.out.println("error" + ex.getMessage());
            response.put("status", "error");
            response.put("message", "The film isn't deleted !");
        }
        return response;
    }

    public List<Film> getAllFilms(){
        return filmRepository.findAll();
    }

    public Film getFilmById(Integer id){
        return filmRepository.findById(id).orElseThrow(RuntimeException::new);
    }
}

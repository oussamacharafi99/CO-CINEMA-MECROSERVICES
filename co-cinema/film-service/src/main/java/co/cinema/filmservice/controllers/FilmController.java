package co.cinema.filmservice.controllers;

import co.cinema.filmservice.models.Film;
import co.cinema.filmservice.sevices.FilmService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/film")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @PostMapping("add")
    public Map<String, String> create(@RequestBody Film film){
        return filmService.createFilm(film);
    }

    @PutMapping("update/{id}")
    public Map<String, String> update(@PathVariable Integer id, @RequestBody Film film){
        return filmService.updateFilm(id, film);
    }

    @DeleteMapping("delete/{id}")
    public Map<String, String> delete(@PathVariable Integer id){
        return filmService.deleteFilm(id);
    }

    @GetMapping("get-all")
    public List<Film> getFilms(){
        return filmService.getAllFilms();
    }

    @GetMapping("get/{id}")
    public Film getFilmById(@PathVariable Integer id){
        return filmService.getFilmById(id);
    }
}

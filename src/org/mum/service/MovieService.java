/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mum.service;

import java.util.ArrayList;
import java.util.List;
import org.mum.model.Movie;

/**
 *
 * @author Mingwei
 */
public class MovieService {
    
    static List<Movie> MOVIE = new ArrayList<Movie>();
    static {
        Movie m = new Movie();
        Movie m1 = new Movie();
        Movie m2 = new Movie();
        m.setId("1");
        m.setTitle("Avatar");
        m.setGenre("0");
        m.setDescription("On the lush alien world of Pandora live the Na'vi, beings who appear primitive but are highly evolved. Because the planet's environment is poisonous, human/Na'vi hybrids, called Avatars, must link to human minds to allow for free movement on Pandora. Jake Sully (Sam Worthington), a paralyzed former Marine, becomes mobile again through one such Avatar and falls in love with a Na'vi woman (Zoe Saldana). As a bond with her grows, he is drawn into a battle for the survival of her world.");
        m.setDuration("150");
        m.setImageUrl("/org/mum/assets/img/avatar.jpg");
        m1.setId("2");
        m1.setTitle("The Matrix");
        m1.setGenre("1");
        m1.setDescription("Neo (Keanu Reeves) believes that Morpheus (Laurence Fishburne), an elusive figure considered to be the most dangerous man alive, can answer his question -- What is the Matrix? Neo is contacted by Trinity (Carrie-Anne Moss), a beautiful stranger who leads him into an underworld where he meets Morpheus. They fight a brutal battle for their lives against a cadre of viciously intelligent secret agents. It is a truth that could cost Neo something more precious than his life.");
        m1.setDuration("115");
        m1.setImageUrl("/org/mum/assets/img/the_matrix.jpg");
        m2.setId("3");
        m2.setTitle("Batman & Robin");
        m2.setGenre("2");
        m2.setDescription("This superhero adventure finds Batman (George Clooney) and his partner, Robin (Chris O'Donnell), attempting to the foil the sinister schemes of a deranged set of new villains, most notably the melancholy Mr. Freeze (Arnold Schwarzenegger), who wants to make Gotham into an arctic region, and the sultry Poison Ivy (Uma Thurman), a plant-loving femme fatale. As the Dynamic Duo contends with these bad guys, a third hero, Batgirl (Alicia Silverstone), joins the ranks of the city's crime-fighters.");
        m2.setDuration("109");
        m2.setImageUrl("/org/mum/assets/img/batman_and_robin.jpg");
        MOVIE.add(m);
        MOVIE.add(m1);
        MOVIE.add(m2);
    }
    public static List<Movie> getMovieList(){
        return MOVIE;
    }
    
    public static String addMovie(Movie movie){
        MOVIE.add(movie);
        return "Added successfully";
    }
    
    public static String updateMovie(Movie movie){
        int i = 0;
        for(Movie m : MOVIE){
            if(m.getId().equals(movie.getId()))
                break;
            i++;
        }
        MOVIE.remove(i);
        MOVIE.add(movie);
        return "Updated successfully";
    }
    
    public static String deleteMovie(Movie movie){
        int i = 0;
        for(Movie m : MOVIE){
            if(m.getId().equals(movie.getId()))
                break;
            i++;
        }
        MOVIE.remove(i);
        return "Deleted successfully";
    }
    
    public static List<Movie> fuzzyQuery(String keyword){
        List<Movie> res = new ArrayList<Movie>();
        for(Movie m : MOVIE)
            if(m.getTitle().contains(keyword) || m.getDescription().contains(keyword))
                res.add(m);
        return res;
    }
}

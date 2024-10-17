package org.example.dao;

import org.example.model.Movie;

import java.sql.*;
import java.util.List;

public class MovieDAO implements DAO<Movie>{
    private static Connection connection = null;
    public static final String SELECT_FROM_MOVIES = "select * from pelicula;";
    public static final String INSERT_INTO_MOVIES = "insert into pelicula (id, titulo, año, genero) values (?, ?, ?, ?);";
    public static final String SELECT_FROM_GAMES_WHERE_YEAR = "select * from pelicula where año = ?;";
    public MovieDAO(Connection con) { connection = con; }
    @Override
    public List<Movie> findByYear(Integer year) {
        List<Movie> movies = null;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_FROM_GAMES_WHERE_YEAR)) {
            ps.setInt(1, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                movies.add(new Movie(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getInt("año"),
                        rs.getString("genero")
                ));
                }
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
        return movies;
    }

    @Override
    public String TotalMovies() {
        Integer total = null;
        try (PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) FROM pelicula;")){
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Hay un total de "+total+" películas";
    }

    @Override
    public List<Movie> findAll() {
        return null;
    }

    @Override
    public Movie findById(Integer id) {
        return null;
    }

    @Override
    public void save(Movie movie) {
        try(PreparedStatement ps = connection.prepareStatement(INSERT_INTO_MOVIES)) {
            ps.setString(1, String.valueOf(movie.getId()));
            ps.setString(2, movie.getTitle());
            ps.setInt(3,    movie.getYear());
            ps.setString(4, movie.getGenre());

            if (ps.executeUpdate() == 1) {
                ResultSet rs = ps.getGeneratedKeys();
                rs.next();
                movie.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Movie movie) {

    }

    @Override
    public void delete(Movie movie) {

    }


}

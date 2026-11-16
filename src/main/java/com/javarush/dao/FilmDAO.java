package com.javarush.dao;

import com.javarush.domain.Customer;
import com.javarush.domain.Film;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


public class FilmDAO extends  GenericDAO<Film>{

    public FilmDAO( SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }

public  Film getFirstAvailableFilmForRent(){
    Query<Film> query = getCurrentSession().createQuery("select f from Film f where not exists (select 1 from Inventory i where i.film = f)", Film.class);
    query.setMaxResults(1);
    return query.getSingleResult();
}

}
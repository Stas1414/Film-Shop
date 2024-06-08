package com.example.demo.repositories;

import com.example.demo.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface FilmRepository extends JpaRepository<Film,Long> {
    Film findByNameAndPrice(String name,int price);

//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE Film SET name=?,  yearOfProduction=?,author=?,price=?,description=? WHERE film_id=?",nativeQuery = true)
//    void update(@Param("id") Long id
//                @Param("name") String name);
}

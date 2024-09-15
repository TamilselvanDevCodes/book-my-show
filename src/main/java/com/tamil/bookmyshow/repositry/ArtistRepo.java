package com.tamil.bookmyshow.repositry;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tamil.bookmyshow.entity.Artist;

public interface ArtistRepo extends JpaRepository<Artist, Integer>{
	 Optional<Artist> findByName(String name);
}

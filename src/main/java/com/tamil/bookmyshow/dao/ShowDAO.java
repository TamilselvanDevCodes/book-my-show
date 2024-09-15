package com.tamil.bookmyshow.dao;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tamil.bookmyshow.entity.Show;
import com.tamil.bookmyshow.repositry.ShowRepo;

@Repository
public class ShowDAO {
 @Autowired
 private ShowRepo repo;
 
   public Show save(Show show) {
	   return repo.save(show);
   }
   public Show find(int id) {
	   Optional<Show>opt=repo.findById(id);
	   if(opt.isPresent())return opt.get();
	   return null;
   }
   public Show find(LocalDate date,LocalTime time,int theatreId) {
	   Optional<Show>opt=repo.findByDateOfStartAndTimeOfStartAndTheatreId(date, time,theatreId);
	   if(opt.isPresent())return opt.get();
	   return null;
   }
   public List<Show> find(LocalDate date,int theatreId) {
	   List<Optional<Show>> list=new ArrayList<>();
	   list=repo.findByDateOfStartAndTheatreId(date, theatreId);
	  List<Show>show=new ArrayList<>();
	  
	  for(Optional<Show> opt:list) {
		if(opt.isPresent())show.add( opt.get());  
	  }
	  return show;
	  
   }
   public Show delete(int id) {
	   Show s=find(id);
	   if (s==null)return null;
	   repo.deleteById(id);
	   return s;
   }
   public Show update(Show show,int id) {
	   if(show==null)return null;
	   show.setId(id);
	   return repo.save(show);
   }
}

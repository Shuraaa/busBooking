package com.bbs.app.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import com.bbs.app.model.Route;

public interface RouteRepository extends JpaRepository<Route, Integer>, PagingAndSortingRepository<Route, Integer> {

	List<Route> findByPrice(double price);

	List<Route> findByStartContaining(String start);

	List<Route> findByEndContaining(String end);

	List<Route> findByStartDate(@DateTimeFormat(pattern = "yyyy-MM-dd") @Param("startDate") Date startDate);

	@Query("From Route t where t.start=:start and t.end=:end and t.startDate=:startDate")
	List<Route> findByStartAndEndAndStartDateContaining(@Param("start") String start, @Param("end") String end,
			@DateTimeFormat(pattern = "yyyy-MM-dd") @Param("startDate") Date startDate);

}

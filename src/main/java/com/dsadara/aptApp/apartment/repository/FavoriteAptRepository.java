package com.dsadara.aptApp.apartment.repository;

import com.dsadara.aptApp.apartment.entity.FavoriteApt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteAptRepository extends JpaRepository<FavoriteApt, Long> {
}

package com.dsadara.aptApp.apartment.repository;

import com.dsadara.aptApp.apartment.entity.CommentApt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentAptRepository extends JpaRepository<CommentApt, Long> {
}

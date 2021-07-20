package com.murilonerdx.blogex.repository;

import com.murilonerdx.blogex.entities.MyRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<MyRepository, Long> {

}

package com.murilonerdx.blogex.repository;

import com.murilonerdx.blogex.entities.GitInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GithubInfoRepository extends JpaRepository<GitInfo, Long> {
}

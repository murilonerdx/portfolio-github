package com.murilonerdx.blogex.repository;

import com.murilonerdx.blogex.entities.GithubInfo;
import com.murilonerdx.blogex.entities.ProjectGithub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GithubInfoRepository extends JpaRepository<ProjectGithub, Long> {
}

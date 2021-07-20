package com.murilonerdx.blogex.services;

import com.murilonerdx.blogex.entities.ProjectGithub;
import com.murilonerdx.blogex.repository.GithubInfoRepository;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GithubInfoService {

  private final GithubInfoRepository repository;


  @Autowired
  public GithubInfoService(GithubInfoRepository repository) {
    this.repository = repository;
  }

  public Page<ProjectGithub> getPaginatedGitInfos(Pageable pageable) {
    return repository.findAll(pageable);
  }

  public List<ProjectGithub> getAll(){
    return repository.findAll();
  }


}

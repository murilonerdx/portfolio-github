package com.murilonerdx.blogex.services;

import com.murilonerdx.blogex.entities.ProjectGithub;
import com.murilonerdx.blogex.repository.GithubInfoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GithubInfoService {

  private final GithubInfoRepository repository;

  @Autowired
  public GithubInfoService(GithubInfoRepository repository) {
    this.repository = repository;
  }


  public List<ProjectGithub> getAll(){
    return repository.findAll();
  }


}

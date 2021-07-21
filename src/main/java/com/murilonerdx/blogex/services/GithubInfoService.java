package com.murilonerdx.blogex.services;

import com.murilonerdx.blogex.entities.GitInfo;
import com.murilonerdx.blogex.repository.GithubInfoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GithubInfoService {

  private final GithubInfoRepository repository;


  @Autowired
  public GithubInfoService(GithubInfoRepository repository) {
    this.repository = repository;
  }

  public Page<GitInfo> getPaginatedGitInfos(Pageable pageable) {
    return repository.findAll(pageable);
  }

  public List<GitInfo> getAll(){
    return repository.findAll();
  }


}

package com.murilonerdx.blogex.services;

import com.murilonerdx.blogex.entities.MyRepository;
import com.murilonerdx.blogex.repository.ArticleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

  private final ArticleRepository repository;

  @Autowired
  public ArticleService(ArticleRepository repository) {
    this.repository = repository;
  }

  public List<MyRepository> getAll(){
    return repository.findAll();
  }


}

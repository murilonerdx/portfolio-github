package com.murilonerdx.blogex.controller;

import com.murilonerdx.blogex.entities.MyRepository;
import com.murilonerdx.blogex.services.ArticleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {

  private final ArticleService service;

  @Autowired
  public ArticleController(ArticleService service) {
    this.service = service;
  }

  @GetMapping("/")
  public String getAllArticles(Model model) {
    List<MyRepository> repositories = service.getAll();
    model.addAttribute("repositories", repositories);
    return "index";
  }

  @GetMapping("/about")
  public String redirectAboutMe(){
    return "about";
  }

}

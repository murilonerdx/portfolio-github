package com.murilonerdx.blogex.controller;

import com.murilonerdx.blogex.entities.ProjectGithub;
import com.murilonerdx.blogex.services.GithubInfoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GithubInfoController {

  private final GithubInfoService service;

  @Autowired
  public GithubInfoController(GithubInfoService service) {
    this.service = service;
  }

  @GetMapping("/")
  public String getAllArticles(Model model) {
    List<ProjectGithub> repositories = service.getAll();
    model.addAttribute("repositories", repositories);
    return "index";
  }

  @GetMapping("/about")
  public String redirectAboutMe(){
    return "about";
  }

}

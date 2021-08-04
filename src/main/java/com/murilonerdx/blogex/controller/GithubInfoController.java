package com.murilonerdx.blogex.controller;

import com.murilonerdx.blogex.entities.GitInfo;
import com.murilonerdx.blogex.services.GithubInfoService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
  public String getAllGitInfo(HttpServletRequest request, Model model) {
    int page = 0; //default page number is 0 (yes it is weird)
    int size = 10; //default page size is 10

    if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
      page = Integer.parseInt(request.getParameter("page")) - 1;
    }

    if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
      size = Integer.parseInt(request.getParameter("size"));
    }

    model.addAttribute("customers", service.getPaginatedGitInfos(PageRequest.of(page, size)));
    Page<GitInfo> paginatedGitInfos = service.getPaginatedGitInfos(PageRequest.of(page, size));
    List<GitInfo> content = paginatedGitInfos.getContent().stream().peek(x->{
      if(x.getLanguage() == null){
        x.setLanguage("FORKED");
        x.setDescription("Project forked for studies");
      }
    }).collect(Collectors.toList());;
    model.addAttribute("repositories", content);

    return "index";
  }

  @GetMapping("/about")
  public String redirectAboutMe() {
    return "about";
  }

}

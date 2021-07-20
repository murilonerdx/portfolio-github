package com.murilonerdx.blogex.controller;

import com.murilonerdx.blogex.entities.ProjectGithub;
import com.murilonerdx.blogex.services.GithubInfoService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GithubInfoController {

  private final GithubInfoService service;

  @Autowired
  public GithubInfoController(GithubInfoService service) {
    this.service = service;
  }

  @GetMapping("/")
  public String getAllArticles(HttpServletRequest request, Model model) {
    int page = 0; //default page number is 0 (yes it is weird)
    int size = 5; //default page size is 10

    if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
      page = Integer.parseInt(request.getParameter("page")) - 1;
    }

    if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
      size = Integer.parseInt(request.getParameter("size"));
    }

    model.addAttribute("customers", service.getPaginatedGitInfos(PageRequest.of(page, size)));
    model.addAttribute("repositories",  service.getPaginatedGitInfos(PageRequest.of(page, size)));

    return "index";
  }

  @GetMapping("/about")
  public String redirectAboutMe(){
    return "about";
  }

}

package com.murilonerdx.blogex.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;
import org.hibernate.Hibernate;


@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProjectGithub {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long id;
  private String nodeId;
  private String name;
  private String fullName;
  private String htmlUrl;
  private String description;
  private String cloneUrl;
  private String language;
  private String updatedAt;

  public ProjectGithub(GithubInfo github) {
    this.id = null;
    this.language = github.getLanguage();
    this.nodeId = github.getNode_id();
    this.name = github.getName();
    this.fullName = github.getFull_name();
    this.htmlUrl = github.getHtml_url();
    this.description = github.getDescription();
    this.cloneUrl = github.getClone_url();
    this.updatedAt = github.getUpdated_at();
  }

  @JsonFormat(pattern="dd/MM/yyyy")
  private LocalDate date;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    ProjectGithub article = (ProjectGithub) o;

    return id != null && id.equals(article.id);
  }

  @Override
  public int hashCode() {
    return 150676408;
  }


  @SneakyThrows
  public int compareTo(ProjectGithub element) {
    SimpleDateFormat dateFormat = new SimpleDateFormat();
    Date date1 = dateFormat.parse(this.updatedAt);
    Date date2 = dateFormat.parse(element.updatedAt);
    int res = 0;
    if (date1.getTime() < date2.getTime()) {
      res =- 1;
    }
    if (date1.getTime() > date2.getTime()) {
      res = 1;
    }
    return res;
  }


}

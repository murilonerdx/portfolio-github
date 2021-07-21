package com.murilonerdx.blogex.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GitInfoDTO {
  private String id;
  private String node_id;
  private String name;
  private String full_name;
  private String html_url;
  private String description;
  private String clone_url;
  private String language;
  private String updated_at;
}

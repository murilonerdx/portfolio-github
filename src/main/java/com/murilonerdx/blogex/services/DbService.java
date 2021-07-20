package com.murilonerdx.blogex.services;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.murilonerdx.blogex.entities.MyRepository;
import com.murilonerdx.blogex.entities.RepositoryGithub;
import com.murilonerdx.blogex.repository.ArticleRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import javax.lang.model.element.Element;
import org.apache.catalina.WebResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.error.MarkedYAMLException;

@Service
public class DbService {

  List<MyRepository> myRepositories = new LinkedList<>();

  private final ArticleRepository repository;

  @Autowired
  public DbService(ArticleRepository repository) {
    this.repository = repository;
  }

  @Bean
  public void instantiateTestDatabase() throws Exception {

    String myUrl = "https://api.github.com/users/murilonerdx/repos";

    try {
      URL url = new URL(myUrl);
      HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

      if (conexao.getResponseCode() != 200) {
        throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());
      }

      BufferedReader resposta = new BufferedReader(
          new InputStreamReader((conexao.getInputStream())));
      String jsonEmString = converteJsonEmString(resposta);

      Type listType = new TypeToken<ArrayList<RepositoryGithub>>() {
      }.getType();
      List<RepositoryGithub> repositories = new Gson().fromJson(jsonEmString, listType);
      repositories.forEach(x -> this.myRepositories.add(new MyRepository(x)));
      repository.saveAll((myRepositories));
    } catch (Exception e) {
      throw new Exception("ERRO: " + e);
    }
  }

  public static String converteJsonEmString(BufferedReader buffereReader) throws IOException {
    String resposta, jsonEmString = "";
    while ((resposta = buffereReader.readLine()) != null) {
      jsonEmString += resposta;
    }
    return jsonEmString;
  }


}

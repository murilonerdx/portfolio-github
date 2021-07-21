package com.murilonerdx.blogex.services;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.murilonerdx.blogex.entities.GitInfo;
import com.murilonerdx.blogex.entities.GitInfoDTO;
import com.murilonerdx.blogex.repository.GithubInfoRepository;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class DbService {

  List<GitInfo> myRepositories = new LinkedList<>();

  private final GithubInfoRepository repository;

  @Autowired
  public DbService(GithubInfoRepository repository) {
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

      Type listType = new TypeToken<ArrayList<GitInfoDTO>>() {}.getType();
      List<GitInfoDTO> repositories = new Gson().fromJson(jsonEmString, listType);
      repositories.forEach(x -> this.myRepositories.add(new GitInfo(x)));
      repository.saveAll((myRepositories));
    } catch (Exception e) {
      throw new Exception("ERRO: " + e);
    }
  }

  public static String converteJsonEmString(BufferedReader buffereReader) throws IOException {
    String resposta;
    StringBuilder jsonEmString = new StringBuilder();
    while ((resposta = buffereReader.readLine()) != null) {
      jsonEmString.append(resposta);
    }
    return jsonEmString.toString();
  }


}

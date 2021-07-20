package com.murilonerdx.blogex.config;

import com.murilonerdx.blogex.services.DbService;
import java.net.MalformedURLException;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class DatabaseInitializer {

  public DbService dbService;

  @Autowired
  public DatabaseInitializer(DbService dbService) {
    this.dbService = dbService;
  }

  @Bean
  public boolean instantiateDatabase() throws Exception {
    dbService.instantiateTestDatabase();
    return true;
  }
}

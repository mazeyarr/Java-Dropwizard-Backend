package com.iipsen2.app;

import com.iipsen2.app.checks.DatabaseHealthCheck;
import com.iipsen2.app.daos.DAO;
import com.iipsen2.app.providers.TokenProvider;
import com.iipsen2.app.resources.HtmlPageResource;
import com.iipsen2.app.resources.UserResource;
import com.iipsen2.app.services.UserService;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

import java.util.HashMap;

public class MainService extends Application<MainConfiguration> {
  final static String API_VERSION = "V0.0.2";
  public static TokenProvider tokenProvider;

  public static void main(String[] args) throws Exception {
    new MainService().run(args);
  }

  @Override
  public void initialize(Bootstrap<MainConfiguration> bootstrap) {
    bootstrap.addBundle(new MigrationsBundle<MainConfiguration>() {
      @Override
      public PooledDataSourceFactory getDataSourceFactory(final MainConfiguration configuration) {
        return configuration.getDataSourceFactory();
      }
    });
  }

  @Override
  public void run(MainConfiguration configuration, Environment environment) throws Exception {
    final DBIFactory factory = new DBIFactory();
    final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
    final DAO dao = jdbi.onDemand(DAO.class);

    environment.jersey().register(new HtmlPageResource());

    environment.jersey().register(new UserResource(
            new UserService(dao)
    ));

    environment.healthChecks().register("checks",
            new DatabaseHealthCheck(jdbi, configuration.getDataSourceFactory().getValidationQuery()));

    tokenProvider = new TokenProvider();
  }

  public static HashMap<String, String> getVersion() {
    HashMap<String, String> version = new HashMap<>();
    version.put("version", API_VERSION);

    return version;
  }

}

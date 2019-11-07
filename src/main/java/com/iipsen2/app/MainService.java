package com.iipsen2.app;

import com.iipsen2.app.checks.DatabaseHealthCheck;
import com.iipsen2.app.daos.DAO;
import com.iipsen2.app.filters.AuthenticationFilter;
import com.iipsen2.app.providers.TokenProvider;
import com.iipsen2.app.resources.*;
import com.iipsen2.app.services.*;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.forms.MultiPartBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.skife.jdbi.v2.DBI;

import java.util.HashMap;

public class MainService extends Application<MainConfiguration> {
  final static String API_VERSION = "V0.0.2";
  public static TokenProvider tokenProvider;

  /**
   * Execute server start
   *
   * @author Mazeyar Rezaei
   * @since 02-10-2019
   * @param args
   * @throws Exception
   */
  public static void main(String[] args) throws Exception {
    new MainService().run(args);
  }

  /**
   * Initialize Bundles
   *
   * @author Mazeyar Rezaei
   * @since 02-10-2019
   * @param bootstrap
   */
  @Override
  public void initialize(Bootstrap<MainConfiguration> bootstrap) {
    bootstrap.addBundle(new MigrationsBundle<MainConfiguration>() {
      @Override
      public PooledDataSourceFactory getDataSourceFactory(final MainConfiguration configuration) {
        return configuration.getDataSourceFactory();
      }
    });
    bootstrap.addBundle(new MultiPartBundle());
  }

  /**
   * Run server with main configurations
   *
   * @author Mazeyar Rezaei
   * @since 02-10-2019
   * @param configuration
   * @param environment
   * @throws Exception
   */
  @Override
  public void run(MainConfiguration configuration, Environment environment) throws Exception {
    final DBIFactory factory = new DBIFactory();
    final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
    final DAO dao = jdbi.onDemand(DAO.class);

    /**
     * Filters
     *
     */
    environment.jersey().register(new AuthenticationFilter());

    /**
     * Resources
     */
    environment.jersey().register(
            new HtmlPageResource()
    );

    environment.jersey().register(new UserResource(
            new UserService(dao)
    ));

    environment.jersey().register(new InstituteResource(
            new InstituteService(dao)
    ));

    environment.jersey().register(new EducationResource(
            new EducationService(dao)
    ));

    environment.jersey().register(new ProjectResource(
            new ProjectService(dao)
    ));

    environment.jersey().register(new UploadResource(
            new UploadService(dao)
    ));

    /**
     * Health Checks
     */
    environment.healthChecks().register("checks",
            new DatabaseHealthCheck(jdbi, configuration.getDataSourceFactory().getValidationQuery()));

    /**
     * Initializables
     */
    tokenProvider = new TokenProvider();
  }

  /**
   * Returns fictive version of the app
   *
   * @author Mazeyar Rezaei
   * @since 02-10-2019
   * @return String
   */
  public static HashMap<String, String> getVersion() {
    HashMap<String, String> version = new HashMap<>();
    version.put("version", API_VERSION);

    return version;
  }

}

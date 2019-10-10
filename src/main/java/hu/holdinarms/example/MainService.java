package hu.holdinarms.example;

import hu.holdinarms.example.resources.HtmlPageResource;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.util.ArrayList;
import java.util.HashMap;

public class MainService extends Application<MainConfiguration> {

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

    environment.jersey().register(new HtmlPageResource());
  }

  public static HashMap<String, String> getVersion() {
    HashMap<String, String> version = new HashMap<>();
    version.put("version", "V0.0.1");

    return version;
  }

}

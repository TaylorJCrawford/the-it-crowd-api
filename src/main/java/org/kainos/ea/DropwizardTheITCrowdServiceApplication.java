package org.kainos.ea;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.kainos.ea.api.JobCapabilityService;
import org.kainos.ea.controller.JobCapabilityController;
import org.kainos.ea.api.BandService;
import org.kainos.ea.api.JobService;
import org.kainos.ea.controller.BandController;
import org.kainos.ea.controller.JobController;
import org.kainos.ea.db.BandDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobCapabilityDao;
import org.kainos.ea.db.JobDao;

public class DropwizardTheITCrowdServiceApplication extends Application<DropwizardTheITCrowdServiceConfiguration> {
  private final JobService jobService;
  private final BandService bandService;
  private final JobCapabilityService jobCapabilityService;

  public DropwizardTheITCrowdServiceApplication() {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    this.jobService = new JobService(new JobDao(), databaseConnector);
    this.bandService = new BandService(new BandDao(), databaseConnector);
    this.jobCapabilityService = new JobCapabilityService(new JobCapabilityDao(), databaseConnector);
  }

  public static void main(final String[] args) throws Exception {
    new DropwizardTheITCrowdServiceApplication().run(args);
  }

  @Override
  public String getName() {
    return "DropwizardTheITCrowdService";
  }

  @Override
  public void initialize(final Bootstrap<DropwizardTheITCrowdServiceConfiguration> bootstrap) {
    bootstrap.addBundle(new SwaggerBundle<DropwizardTheITCrowdServiceConfiguration>() {

      @Override
      protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(DropwizardTheITCrowdServiceConfiguration configuration) {
        return configuration.getSwagger();
      }
    });
  }

  @Override
  public void run(final DropwizardTheITCrowdServiceConfiguration configuration, final Environment environment) {

    environment.jersey().register(new JobController(jobService));
    environment.jersey().register(new JobCapabilityController(jobCapabilityService));
    environment.jersey().register(new BandController(bandService));
  }
}
package org.kainos.ea;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.kainos.ea.resources.JobSpecController;
import org.kainos.ea.controller.JobController;

public class DropwizardTheITCrowdServiceApplication extends Application<DropwizardTheITCrowdServiceConfiguration> {

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
  public void run(final DropwizardTheITCrowdServiceConfiguration configuration,
                  final Environment environment) {
    environment.jersey().register(new JobSpecController());
    environment.jersey().register(new JobController());
  }
}
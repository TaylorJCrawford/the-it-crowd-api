package org.kainos.ea;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.kainos.ea.client.CouldNotGenerateKeyPairException;
import org.kainos.ea.resources.AuthController;
import org.kainos.ea.resources.ConnectionController;
import org.kainos.ea.util.KeyGeneratorUtil;

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

    try { // To Speed Up Processing We Generate RSA Keys On Startup.
      KeyGeneratorUtil.setInstance(new KeyGeneratorUtil());
    } catch (CouldNotGenerateKeyPairException e) {
      throw new RuntimeException(e);
    }

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
    environment.jersey().register(new AuthController());
    environment.jersey().register(new ConnectionController());
  }
}

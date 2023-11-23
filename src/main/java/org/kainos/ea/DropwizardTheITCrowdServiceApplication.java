package org.kainos.ea;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.federecio.dropwizard.swagger.SwaggerBundle;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;
import org.kainos.ea.api.BandService;
import org.kainos.ea.api.JobService;
import org.kainos.ea.controller.BandController;
import org.kainos.ea.db.BandDao;
import org.kainos.ea.db.DatabaseConnector;
import org.kainos.ea.db.JobDao;
import org.kainos.ea.api.AuthService;
import org.kainos.ea.client.CouldNotGenerateKeyPairException;
import org.kainos.ea.db.AuthDao;
import org.kainos.ea.resources.AuthController;
import org.kainos.ea.resources.JobController;
import org.kainos.ea.util.KeyGeneratorUtil;
import org.kainos.ea.validator.AuthValidator;

public class DropwizardTheITCrowdServiceApplication extends Application<DropwizardTheITCrowdServiceConfiguration> {
  private final JobService jobService;
  private final BandService bandService;
  private final AuthDao authDAO = new AuthDao();
  private final AuthService authService = new AuthService();
  private final AuthValidator authValidator = new AuthValidator();


  public DropwizardTheITCrowdServiceApplication() {
    DatabaseConnector databaseConnector = new DatabaseConnector();
    this.jobService = new JobService(new JobDao(), databaseConnector);
    this.bandService = new BandService(new BandDao(), databaseConnector);
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

    try { // To Speed Up Processing We Generate RSA Keys On Startup.
      KeyGeneratorUtil.setInstance(new KeyGeneratorUtil());
    } catch (CouldNotGenerateKeyPairException e) {
      e.printStackTrace();
    }

    bootstrap.addBundle(new SwaggerBundle<DropwizardTheITCrowdServiceConfiguration>() {

      @Override
      protected SwaggerBundleConfiguration getSwaggerBundleConfiguration(DropwizardTheITCrowdServiceConfiguration configuration) {
        return configuration.getSwagger();
      }
    });
  }

  @Override
  public void run(final DropwizardTheITCrowdServiceConfiguration configuration, final Environment environment) {
    environment.jersey().register(new AuthController(authService, authDAO, authValidator));
    environment.jersey().register(new JobController(jobService));
    environment.jersey().register(new BandController(bandService));
  }
}
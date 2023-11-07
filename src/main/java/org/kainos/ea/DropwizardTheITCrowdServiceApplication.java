package org.kainos.ea;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        // TODO: application initialization
    }

    @Override
    public void run(final DropwizardTheITCrowdServiceConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}

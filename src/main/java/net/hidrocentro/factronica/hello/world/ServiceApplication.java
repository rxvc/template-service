package net.hidrocentro.factronica.hello.world;

import net.hidrocentro.factronica.hello.world.bundle.ServiceHibernateBundle;
import net.hidrocentro.factronica.hello.world.health.TemplateHealthCheck;
import net.hidrocentro.factronica.hello.world.resources.HelloWorldResource;
import com.google.common.annotations.VisibleForTesting;
import io.dropwizard.Application;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.hibernate.SessionFactory;

public class ServiceApplication extends Application<ServiceConfiguration> {
    private final ServiceHibernateBundle hibernateBundle = new ServiceHibernateBundle();

    public static void main(String[] args) throws Exception {
        new ServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<ServiceConfiguration> bootstrap) {
        bootstrap.addBundle(getHibernate());

    }

    @Override
    public void run(ServiceConfiguration configuration, Environment environment) throws Exception {
        final HelloWorldResource resource = new HelloWorldResource("hello","hello");
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck("hello");
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }


    private HibernateBundle<ServiceConfiguration> getHibernate() {
        return hibernateBundle;
    }

    @VisibleForTesting
    public SessionFactory getSessionFactory(){
        return hibernateBundle.getSessionFactory();
    }

}

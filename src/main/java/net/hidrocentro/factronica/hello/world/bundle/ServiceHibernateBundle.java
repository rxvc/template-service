package net.hidrocentro.factronica.hello.world.bundle;


import net.hidrocentro.factronica.hello.world.ServiceConfiguration;
import net.hidrocentro.factronica.hello.world.core.Saying;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;

public class ServiceHibernateBundle extends HibernateBundle<ServiceConfiguration>{

    private ServiceConfiguration configuration;

    public ServiceHibernateBundle() {
        super(Saying.class);
    }

    @Override
    public DataSourceFactory getDataSourceFactory(ServiceConfiguration configuration) {
        this.configuration = configuration;
        return configuration.getDataSourceFactory();
    }

    public ServiceConfiguration getConfiguration(){
        return configuration;
    }
}

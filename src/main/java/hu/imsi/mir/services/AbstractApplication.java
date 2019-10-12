package hu.imsi.mir.services;


import org.apache.log4j.Logger;

import javax.ws.rs.core.Application;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractApplication extends Application {

    final static Logger logger = Logger.getLogger(AbstractApplication.class.getName());

    @Override
    public Set<Class<?>> getClasses() {
        logger.trace("Getting default classes");
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        //classes.add(ProviderLoggingListener.class);
        return classes;
    }

    @Override
    public Map<String, Object> getProperties() {
        logger.trace("Getting default properties");
        // A Moxy Glassfish 4.1.2 alatt átveszi az application/json media type message body-k kezelését kiiktatva az általunk kívánt providereket.
        final Map<String, Object> properties = new HashMap<String, Object>(super.getProperties());
        properties.put("jersey.config.disableMoxyJson", true);
        properties.put("jersey.config.client.disableMoxyJson", true);
        properties.put("jersey.config.server.disableMoxyJson", true);

        return properties;
    }
}

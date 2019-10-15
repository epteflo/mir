package hu.imsi.mir;

import com.sun.jersey.api.container.filter.LoggingFilter;
import com.sun.jersey.spi.spring.container.servlet.SpringServlet;
import hu.imsi.mir.services.MirJsonApplication;
import hu.imsi.mir.util.ArgumentHelper;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.servlet.FilterRegistration;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.util.Log4jConfigListener;

import javax.ws.rs.core.Application;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Server {

    public static void main(String[] args)  throws IOException {

        Map<String,String> argsMap = ArgumentHelper.createArgsMap(args);
        if(argsMap== null || !ArgumentHelper.checkArgumentKeys(argsMap.keySet())){
            ArgumentHelper.printHelp();
            return;
        }

        //App context indítása
        startAppContext();

        //HTTP Server inditása
        //startHTTPServer();
    }

    private static void startAppContext(){
        ApplicationContext appContext =
                new ClassPathXmlApplicationContext(
                        "app-context.xml");
    }

    private static void startHTTPServer()  throws IOException {
        final HttpServer server = createHttpServer(new HashMap<String, String>());
        addRestServlets(server);

        server.start();
        System.out.println("Press enter to stop server...");
        System.in.read();
        server.stop();
    }

    private static HttpServer createHttpServer(HashMap<String, String> maps) {
        final HttpServer server = new HttpServer();
        final String host = maps.containsKey("host") ? maps.get("host") : "localhost";
        final Integer port = maps.containsKey("port") ? Integer.valueOf(maps.get("port")) : 18980;
        final NetworkListener listener = new NetworkListener("grizzly2", host, port);
        server.addListener(listener);
        System.out.println("Server starting at "+host+":"+port.toString());
        return server;
    }

    private static void addRestServlets(HttpServer server) {
        WebappContext context = new WebappContext("MIR standalone context","/mir");
        context.addContextInitParameter("log4jConfigLocation", "classpath:log4j.properties");
        context.addContextInitParameter("webAppRootKey","mir.root");
        context.addContextInitParameter("com.sun.jersey.config.property.packages", "jersey.multipart.demo.resources");
        System.out.println("Starting grizzly...");

        context.addListener(Log4jConfigListener.class.getName());


        final FilterRegistration corsFilter = createCorsFilter(context);
        createRestServlet(context, corsFilter, "/json/*", MirJsonApplication.class);
        context.deploy(server);
    }

    private static void createRestServlet(WebappContext context, FilterRegistration corsFilter,
                                          String servletUrl, Class<? extends Application> restServletApplicationClass) {
        final ServletRegistration jerseyServlet = createServletRegistration(context, servletUrl, restServletApplicationClass);
        corsFilter.addMappingForServletNames(null,jerseyServlet.getName());
    }

    private static ServletRegistration createServletRegistration(WebappContext context, String servletUrl, Class<? extends Application> restServletApplicationClass) {
        final ServletRegistration jerseyServlet = context.addServlet("jersey-serlvet" + restServletApplicationClass.getSimpleName() , SpringServlet.class.getName());
        jerseyServlet.setInitParameter("javax.ws.rs.Application", restServletApplicationClass.getName());
        jerseyServlet.setInitParameter("com.sun.jersey.spi.container.ContainerRequestFilters", LoggingFilter.class.getName());
        jerseyServlet.setInitParameter("com.sun.jersey.spi.container.ContainerResponseFilters", LoggingFilter.class.getName());
        jerseyServlet.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");

        jerseyServlet.setLoadOnStartup(1);
        jerseyServlet.addMapping(servletUrl);

        return jerseyServlet;
    }

    private static FilterRegistration createCorsFilter(WebappContext context) {
        final FilterRegistration corsFilter = context.addFilter("CORSFilter", org.ebaysf.web.cors.CORSFilter.class.getName());
        corsFilter.setInitParameter("cors.allowed.methods","GET,POST,PUT,OPTIONS,DELETE");
        corsFilter.setInitParameter("cors.logging.enabled","true");
        corsFilter.setInitParameter("cors.preflight.maxage","300");
        corsFilter.setInitParameter("cors.allowed.origins","*");
        corsFilter.setInitParameter("cors.allowed.headers","Origin,Accept,X-Requested-With,Accept-Language,Content-Type," +
                "Access-Control-Request-Method,Access-Control-Request-Headers,X-csrf-token," +
                "X-User-Name,X-Client-Hash-Key,X-Auth-Token");
        corsFilter.setInitParameter("cors.exposed.headers","X-csrf-token");
        return corsFilter;
    }





}

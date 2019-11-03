package hu.imsi.mir;

import hu.imsi.mir.services.MirJsonApplication;
import hu.imsi.mir.util.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.NetworkListener;
import org.glassfish.grizzly.servlet.FilterRegistration;
import org.glassfish.grizzly.servlet.ServletRegistration;
import org.glassfish.grizzly.servlet.WebappContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.ws.rs.core.Application;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Server {

    final static Logger logger = Logger.getLogger(Server.class.getName());

    public static DBUtils dbUtils;

    public static void main(String[] args)  throws IOException {

        String log4jConfPath = "/home/developer/src/dbx/mir/src/test/resources/log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);

        logger.info("\n@@@@@@@@@@@@@@@@@@@@@@@@@\n MIR starting! \n@@@@@@@@@@@@@@@@@@@@@@@@@\n");


        Map<String,String> argsMap = ArgumentHelper.createArgsMap(args);
        if(argsMap== null || !ArgumentHelper.checkArgumentKeys(argsMap.keySet())){
            ArgumentHelper.printHelp();
            return;
        }

        if(!argsMap.containsKey(Constants.PORT) || !argsMap.containsKey(Constants.CONFIG)){
            System.out.println("Missing arguments! -port and -config is mandatory!");
            logger.error("Missing arguments! -port and -config is mandatory!");
            return;
        }

        //Config property fájl felolvasása
        checkConfig(argsMap);

        System.out.println("Check arguments done!");
        logger.info("Check arguments done!");

        //App context indítása
        startAppContext();
        logger.info("Application context started!");

        dbUtils = BeanHelper.getDBUtils();
        dbUtils.testHibernateModels();
        logger.info("DB connection and utils established!");

        checkDBClear(argsMap);

        initDBproperties(argsMap);

        final HttpServer server=null;

        //HTTP Server inditása
        //final HttpServer server = startHTTPServer(argsMap);
        logger.info("Server started!");

        System.out.println("Press enter to stop server...");
        System.in.read();
        server.stop();
        logger.info("Server stopped!");
    }

    private static void checkDBClear(Map<String,String> argsMap){
        if(argsMap.containsKey(Constants.DB_CLEAR)){
            logger.info("DB clearing in progress...");
            if(argsMap.get(Constants.DB_CLEAR).equals(Constants.TRUE)){
                dbUtils.clearDb(false);
            } else if(argsMap.get(Constants.DB_CLEAR).equals(Constants.ONLY_LOGS)){
                dbUtils.clearDb(true);
            }
            logger.info("  Done!");
        }
    }

    private static void initDBproperties(Map<String,String> argsMap){
        if(argsMap.containsKey(Constants.DB_INIT_PROPERTIES)){
            logger.info("DB data loading from file in progress...");
            File file = new File(argsMap.get(Constants.DB_INIT_PROPERTIES));
            if(file.exists()){
                System.out.println("DbInitProperty File Added:"+file.getAbsolutePath());
                PropertyHelper.readProperties(file);
            } else {
                System.out.println("DBInitProperty File Added, but it is not exists!");
                return;
            }
            logger.info("  Done!");
        }
    }

    private static void checkConfig(Map<String,String> argsMap) throws IOException {
        final String configFile = argsMap.containsKey(Constants.CONFIG) ? argsMap.get(Constants.CONFIG) : "mir.properties";
        final File config = new File(configFile);
        if (!config.isFile()) {
            logger.error(configFile + " does not exist or is not a file");
            throw new RuntimeException(configFile + " does not exist or is not a file");
        }
        System.setProperty("mir.properties", config.getCanonicalPath());

    }

    private static void startAppContext(){
        ApplicationContext appContext =
                new ClassPathXmlApplicationContext(
                        "app-context.xml");
    }

    private static HttpServer startHTTPServer(HashMap<String, String> args) throws IOException {
        final HttpServer server = createHttpServer(args);
        addRestServlets(server);
        server.start();
        return server;
    }

    private static HttpServer createHttpServer(HashMap<String, String> maps) {
        final HttpServer server = new HttpServer();
        final String host = maps.containsKey("host") ? maps.get("host") : "localhost";
        final Integer port = maps.containsKey("port") ? Integer.valueOf(maps.get("port")) : 18980;
        final NetworkListener listener = new NetworkListener("grizzly2", host, port);
        server.addListener(listener);
        logger.info("Server starting at "+host+":"+port.toString());
        return server;
    }

    private static void addRestServlets(HttpServer server) {
        WebappContext context = new WebappContext("MIR standalone context","/mir");
        /*context.addContextInitParameter("log4jConfigLocation", "classpath:log4j.properties");
        context.addContextInitParameter("webAppRootKey","mir.root");
        context.addContextInitParameter("com.sun.jersey.config.property.packages", "jersey.multipart.demo.resources");*/
        logger.info("Starting grizzly...");

        /*context.addListener(Log4jConfigListener.class.getName());*/


        final FilterRegistration corsFilter = createCorsFilter(context);
        createRestServlet(context, corsFilter, "/json/*", MirJsonApplication.class);
        /*context.deploy(server);*/
    }

    private static void createRestServlet(WebappContext context, FilterRegistration corsFilter,
                                          String servletUrl, Class<? extends Application> restServletApplicationClass) {
        final ServletRegistration jerseyServlet = createServletRegistration(context, servletUrl, restServletApplicationClass);
        corsFilter.addMappingForServletNames(null,jerseyServlet.getName());
    }

    private static ServletRegistration createServletRegistration(WebappContext context, String servletUrl, Class<? extends Application> restServletApplicationClass) {
        final ServletRegistration jerseyServlet = null;
        /*final ServletRegistration jerseyServlet = context.addServlet("jersey-serlvet" + restServletApplicationClass.getSimpleName() , SpringServlet.class.getName());
        jerseyServlet.setInitParameter("javax.ws.rs.Application", restServletApplicationClass.getName());
        jerseyServlet.setInitParameter("com.sun.jersey.spi.container.ContainerRequestFilters", LoggingFilter.class.getName());
        jerseyServlet.setInitParameter("com.sun.jersey.spi.container.ContainerResponseFilters", LoggingFilter.class.getName());
        jerseyServlet.setInitParameter("com.sun.jersey.api.json.POJOMappingFeature", "true");

        jerseyServlet.setLoadOnStartup(1);
        jerseyServlet.addMapping(servletUrl);*/

        return jerseyServlet;
    }

    private static FilterRegistration createCorsFilter(WebappContext context) {
        final FilterRegistration corsFilter = null;
        /*final FilterRegistration corsFilter = context.addFilter("CORSFilter", org.ebaysf.web.cors.CORSFilter.class.getName());
        corsFilter.setInitParameter("cors.allowed.methods","GET,POST,PUT,OPTIONS,DELETE");
        corsFilter.setInitParameter("cors.logging.enabled","true");
        corsFilter.setInitParameter("cors.preflight.maxage","300");
        corsFilter.setInitParameter("cors.allowed.origins","*");
        corsFilter.setInitParameter("cors.allowed.headers","Origin,Accept,X-Requested-With,Accept-Language,Content-Type," +
                "Access-Control-Request-Method,Access-Control-Request-Headers,X-csrf-token," +
                "X-User-Name,X-Client-Hash-Key,X-Auth-Token");
        corsFilter.setInitParameter("cors.exposed.headers","X-csrf-token");*/
        return corsFilter;
    }





}

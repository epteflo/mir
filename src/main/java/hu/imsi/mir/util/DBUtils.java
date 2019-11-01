package hu.imsi.mir.util;

import hu.imsi.mir.spring.hibernate.model.HMuseum;
import hu.imsi.mir.spring.hibernate.service.MirService;
import hu.imsi.mir.spring.hibernate.service.MirServiceHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.System.exit;

@Component
public class DBUtils {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void initialize(){
        Connection connection = null;
        Statement statement = null;
        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            System.out.println("\nChecking if DB exists...");
            ResultSet r = statement.executeQuery("Select * from beacons");
            System.out.println("OK\n");
            statement.close();
            connection.close();
        }
        catch (SQLException e) {
            System.out.println("\nChecking failed! DB is missing! Error message: "+e.getMessage());
            initalizeDB(statement);
        }
    }

    public void clearDb(boolean clearOnlyLogs){
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            if(!clearOnlyLogs) truncateTables(statement);
            truncateLogTables(statement);
        } catch (Exception e){
            System.out.println("Error at init connection! Exit! ");
            e.printStackTrace();
            exit(-1);
        }

    }

    private void truncateTables(Statement statement){
        try {
            System.out.println("Truncate Data Tables...");
            statement.executeUpdate("DELETE FROM beacons");
            statement.executeUpdate("DELETE FROM content_objects");
            statement.executeUpdate("DELETE FROM contents");
            statement.executeUpdate("DELETE FROM doors");
            statement.executeUpdate("DELETE FROM exhibition_tour_layouts");
            statement.executeUpdate("DELETE FROM exhibition_tours");
            statement.executeUpdate("DELETE FROM exhibitions");
            statement.executeUpdate("DELETE FROM layouts");
            statement.executeUpdate("DELETE FROM museums");
            statement.executeUpdate("DELETE FROM pois");
            statement.executeUpdate("DELETE FROM rooms");
            statement.executeUpdate("DELETE FROM users");
            statement.executeUpdate("DELETE FROM logs");
            statement.executeUpdate("DELETE FROM service_logs");
            System.out.println("Done");
        } catch (Exception e){
            System.out.println("Error at truncate Data Tables! Exit! ");
            e.printStackTrace();
            exit(-1);
        }
    }

    private void truncateLogTables(Statement statement){
        try {
            System.out.println("Truncate Log Tables...");
            statement.executeUpdate("DELETE FROM logs");
            statement.executeUpdate("DELETE FROM service_logs");
            System.out.println("Done");
        } catch (Exception e){
            System.out.println("Error at truncate Log Tables! Exit! ");
            e.printStackTrace();
            exit(-1);
        }
    }

    private void initalizeDB(Statement statement){
        try{
            System.out.println("Initalizing DB...");
            statement.executeUpdate("CREATE TABLE beacons (id INTEGER PRIMARY KEY AUTOINCREMENT, uuid VARCHAR(40) NULL, type VARCHAR(50) NULL, color VARCHAR(50) NULL)");
            statement.executeUpdate("CREATE INDEX idx_beacon_uuid on beacons(uuid)");

            statement.executeUpdate("CREATE TABLE content_objects (id INTEGER PRIMARY KEY AUTOINCREMENT, content_id INTEGER NULL, museum_id INTEGER NULL, poi_id INTEGER NULL, room_id INTEGER NULL, type VARCHAR(50) NULL, desc VARCHAR(2000) NULL)");
            statement.executeUpdate("CREATE INDEX fk_content_id_idx on content_objects(content_id)");
            statement.executeUpdate("CREATE INDEX fk_museum_id_idx  on content_objects(museum_id)");
            statement.executeUpdate("CREATE INDEX fk_poi_id_idx on content_objects(poi_id)");
            statement.executeUpdate("CREATE INDEX fk_room_id_idx on content_objects(room_id)");

            statement.executeUpdate("CREATE TABLE contents (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100) NULL, uuid VARCHAR(40) NULL, type VARCHAR(50) NULL, desc VARCHAR(2000) NULL, content_url VARCHAR(100) NULL)");

            System.out.println("Done");
        }
        catch (SQLException e) {
            System.out.println("Error at initializing DB! Exit! ");
            e.printStackTrace();
            exit(-1);
        }

    }

    public static void testHibernateModels(){
        MirService mirService = MirServiceHelper.getMirService();
        Integer i;

        System.out.println("---------------------------------------------");
        System.out.println("@ Teszt HMuseum @");


        HMuseum museum = BeanHelper.getDbHelper().createMuseum("M1","M1 múzeum", "Budapest", 3, null, null, null, null, null);

        i=mirService.saveMuseum(museum);
        System.out.println(" Saved Museum id:"+i);
        OutHelper.printHMuseum(mirService.getMuseum(i));
        System.out.println(" All Museum:");
        OutHelper.printHMuseums(mirService.getAllMuseum());

        System.out.println("---------------------------------------------");
    }
}
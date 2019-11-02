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
            statement.executeUpdate("CREATE INDEX fk_museum_id_idx on content_objects(museum_id)");
            statement.executeUpdate("CREATE INDEX fk_poi_id_idx on content_objects(poi_id)");
            statement.executeUpdate("CREATE INDEX fk_room_id_idx on content_objects(room_id)");

            statement.executeUpdate("CREATE TABLE contents (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100) NULL, uuid VARCHAR(40) NULL, type VARCHAR(50) NULL, desc VARCHAR(2000) NULL, content_url VARCHAR(100) NULL)");

            statement.executeUpdate("CREATE TABLE doors (id INTEGER PRIMARY KEY AUTOINCREMENT, room_id_a INTEGER NULL, room_id_b INTEGER NULL, room_a_x INTEGER NULL, room_a_y INTEGER NULL, room_b_x INTEGER NULL,room_b_y INTEGER NULL,desc VARCHAR(2000) NULL)");
            statement.executeUpdate("CREATE INDEX fk_room_a_id_idx on doors(room_id_a)");
            statement.executeUpdate("CREATE INDEX fk_room_b_id_idx on doors(room_id_b)");

            statement.executeUpdate("CREATE TABLE exhibition_tour_layouts (id INTEGER PRIMARY KEY AUTOINCREMENT, exhibition_tour_id INTEGER NULL, layout_id INTEGER NULL, tour_order INTEGER NULL)");
            statement.executeUpdate("CREATE INDEX fk_exhibition_tour_id_idx on exhibition_tour_layouts(exhibition_tour_id)");
            statement.executeUpdate("CREATE INDEX fk_layout_id_idx on exhibition_tour_layouts(layout_id)");

            statement.executeUpdate("CREATE TABLE exhibition_tours (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100) NULL, desc VARCHAR(2000) NULL, museum_id INTEGER NULL, exhibition_id INTEGER NULL)");
            statement.executeUpdate("CREATE INDEX fk_museum_id_ext_idx on exhibition_tours(museum_id)");
            statement.executeUpdate("CREATE INDEX fk_exhibition_id_idx on exhibition_tours(exhibition_id)");

            statement.executeUpdate("CREATE TABLE exhibitions (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(200) NULL, desc VARCHAR(1000) NULL, type VARCHAR(40) NULL, museum_id INTEGER NULL)");
            statement.executeUpdate("CREATE INDEX fk_museum_id_ex_idx on exhibitions(museum_id)");

            statement.executeUpdate("CREATE TABLE layouts (id INTEGER PRIMARY KEY AUTOINCREMENT, room_id INTEGER NULL, beacon_id INTEGER NULL, exhibition_id INTEGER NULL, poi_id INTEGER NULL, room_x INTEGER NULL, room_y INTEGER NULL)");
            statement.executeUpdate("CREATE INDEX fk_room_id_layot_idx on layouts(room_id)");
            statement.executeUpdate("CREATE INDEX fk_beacon_id_idx on layouts(beacon_id)");
            statement.executeUpdate("CREATE INDEX fk_amuse_id_idx on layouts(poi_id)");
            statement.executeUpdate("CREATE INDEX fk_exhibition_id_layout_idx on layouts(exhibition_id)");

            statement.executeUpdate("CREATE TABLE logs (id INTEGER PRIMARY KEY AUTOINCREMENT, user_id INTEGER NULL, room_name INTEGER NULL, poi_id INTEGER NULL)");
            statement.executeUpdate("CREATE INDEX fk_user_id_idx on logs(user_id)");
            statement.executeUpdate("CREATE INDEX fk_poi_id_logs_idx on logs(poi_id)");

            statement.executeUpdate("CREATE TABLE museums (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(200) NULL, desc VARCHAR(2000) NULL, address VARCHAR(200) NULL, num_of_rooms INTEGER NULL, history BLOB NULL, curiosity BLOB NULL,open_hours VARCHAR(500) NULL, other_services BLOB NULL, prices VARCHAR(1000) NULL)");

            statement.executeUpdate("CREATE TABLE pois (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(200) NULL, type VARCHAR(50) NULL, short_desc VARCHAR(2000) NULL, desc BLOB NULL, category VARCHAR(50) NULL, style VARCHAR(50) NULL)");

            statement.executeUpdate("CREATE TABLE rooms (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100) NULL, museum_id INTEGER NULL, desc VARCHAR(2000) NULL, size_x INTEGER NULL, size_y INTEGER NULL)");
            statement.executeUpdate(" CREATE INDEX fk_museum_id on rooms(museum_id)");

            statement.executeUpdate("CREATE TABLE service_logs (id INTEGER PRIMARY KEY AUTOINCREMENT, message VARCHAR(2000) NULL, user_id INTEGER NULL, source_module VARCHAR(100) NULL, source_method VARCHAR(100) NULL, severity VARCHAR(20) NULL, trace BLOB NULL)");
            statement.executeUpdate("CREATE INDEX fk_user_id_service_idx on service_logs(user_id)");

            statement.executeUpdate("CREATE TABLE users (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100) NULL, uuid VARCHAR(40) NULL);");

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
        System.out.println(OutHelper.printHMuseum(mirService.getMuseum(i)));
        System.out.println(" All Museum:");
        System.out.println(OutHelper.printHMuseums(mirService.getAllMuseum()));


        System.out.println("---------------------------------------------");
    }
}
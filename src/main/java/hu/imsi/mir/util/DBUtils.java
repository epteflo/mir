package hu.imsi.mir.util;

import hu.positech.positionlogger.spring.hibernate.service.LoggerService;
import hu.positech.positionlogger.spring.hibernate.service.LoggerServiceHelper;
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
            ResultSet r = statement.executeQuery("Select * from rfid_tags");
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
            statement.executeUpdate("DELETE FROM rfid_tags");
            statement.executeUpdate("DELETE FROM bl_nodes");
            statement.executeUpdate("DELETE FROM workers");
            statement.executeUpdate("DELETE FROM rfid_antennas");
            statement.executeUpdate("DELETE FROM permissions");
            statement.executeUpdate("DELETE FROM marks");
            statement.executeUpdate("DELETE FROM rfid_logs");
            statement.executeUpdate("DELETE FROM bl_mark_logs");
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
            statement.executeUpdate("DELETE FROM rfid_logs");
            statement.executeUpdate("DELETE FROM bl_mark_logs");
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
            statement.executeUpdate("CREATE TABLE rfid_tags (id INTEGER PRIMARY KEY AUTOINCREMENT, tag_id VARCHAR(100) NULL, name VARCHAR(100) NULL, type VARCHAR(100) NULL)");
            statement.executeUpdate("CREATE TABLE bl_nodes (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100) NULL, hw_id VARCHAR(100) NULL)");
            statement.executeUpdate("CREATE INDEX idx_bl_nodes_name on bl_nodes(name)");
            statement.executeUpdate("CREATE TABLE workers (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(200) NULL, status VARCHAR(50) NULL, job VARCHAR(100) NULL, company VARCHAR(100) NULL, rfid_tag_id INTEGER NULL, bl_node_id INTEGER NULL, mark_id INTEGER NULL)");
            statement.executeUpdate("CREATE INDEX idx_worker_bl_node_id on workers(bl_node_id)");
            statement.executeUpdate("CREATE TABLE rfid_antennas (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100) NULL, hw_id VARCHAR(100) NULL)");
            statement.executeUpdate("CREATE TABLE permissions ( id INTEGER PRIMARY KEY AUTOINCREMENT, worker_id INTEGER NULL, rfid_tag_id INTEGER NULL, rfid_antenna_id INTEGER NULL, permission VARCHAR(100) NULL, limit_in_s INTEGER NULL, corr_id VARCHAR(100) NULL)");
            statement.executeUpdate("CREATE INDEX idx_permissions_corr_id on permissions(corr_id)");
            statement.executeUpdate("CREATE INDEX idx_permissions_rfids on permissions(rfid_tag_id, rfid_antenna_id)");
            statement.executeUpdate("CREATE TABLE marks (id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(100) NULL, hw_id VARCHAR(100) NULL, coord_x VARCHAR(100) NULL, coord_y VARCHAR(100) NULL)");
            statement.executeUpdate("CREATE INDEX idx_marks_hwid on marks(hw_id)");
            statement.executeUpdate("CREATE TABLE rfid_logs (id INTEGER PRIMARY KEY AUTOINCREMENT, rfid_antenna_id INTEGER NULL, rfid_tag_id INTEGER NULL, worker_id INTEGER NULL, created_at DATETIME NULL, desc VARCHAR(500) NULL)");
            statement.executeUpdate("CREATE INDEX idx_rfid_logs_rfidtag on rfid_logs(rfid_tag_id)");
            statement.executeUpdate("CREATE INDEX idx_rfid_logs_rfidant on rfid_logs(rfid_antenna_id)");
            statement.executeUpdate("CREATE INDEX idx_rfid_logs_worker on rfid_logs(worker_id)");
            statement.executeUpdate("CREATE TABLE bl_mark_logs (id INTEGER PRIMARY KEY AUTOINCREMENT, bl_node_id INTEGER NULL, mark_id INTEGER NULL, created_at DATETIME NULL, desc VARCHAR(500) NULL)");
            statement.executeUpdate("CREATE INDEX idx_bl_mark_logs_nodeid on bl_mark_logs(bl_node_id)");
            statement.executeUpdate("CREATE INDEX idx_bl_mark_logs_markid on bl_mark_logs(mark_id)");
            System.out.println("Done");
        }
        catch (SQLException e) {
            System.out.println("Error at initializing DB! Exit! ");
            e.printStackTrace();
            exit(-1);
        }

    }

    public static void testHibernateModels(){
        LoggerService loggerService = LoggerServiceHelper.getLoggerService();
        Integer i;

        System.out.println("---------------------------------------------");
        System.out.println("@ Teszt HRFIDTag @");
        HRFIDTag hrfidTag = new HRFIDTag();
        hrfidTag.setName("Bakancs");
        hrfidTag.setType("Tool");
        hrfidTag.setTagId("AAAA1111");

        i=loggerService.saveRFIDTag(hrfidTag);
        System.out.println(" Saved id:"+i);
        OutHelper.printHREFIDTag(loggerService.getRFIDTag(i));
        System.out.println(" All RFIDTags:");
        OutHelper.printHREFIDTags(loggerService.getAllRFIDTags());


        System.out.println("---------------------------------------------");
        System.out.println("@ Teszt HRFIDAntenna @");
        HRFIDAntenna antenna = new HRFIDAntenna();
        antenna.setName("Felső antenna");
        antenna.setHwId("AAAA4444");

        i=loggerService.saveRFIDAntenna(antenna);
        System.out.println(" Saved id:"+i);
        OutHelper.printHREFIDTag(loggerService.getRFIDTag(i));
        System.out.println(" All RFIDAntennas:");
        OutHelper.printHREFIDAntennas(loggerService.getAllRFIDAntennas());


        System.out.println("---------------------------------------------");
        System.out.println("@ Teszt HBLNodes @");
        HBLNode node = new HBLNode();
        node.setName("1BLAllomás");
        node.setHwId("DDDD3333");

        i=loggerService.saveBLNode(node);
        System.out.println(" Saved id:"+i);
        OutHelper.printHBLNode(loggerService.getBLNode(i));
        System.out.println(" All RFIDAntennas:");
        OutHelper.printHBLNodes(loggerService.getAllBLNodes());


        System.out.println("---------------------------------------------");
        System.out.println("@ Teszt HMarks @");
        HMark mark = new HMark();
        mark.setName("MARK1");
        mark.setHwId("CCCCC3331");

        i=loggerService.saveMark(mark);
        System.out.println(" Saved id:"+i);
        OutHelper.printHMark(loggerService.getMark(i));
        System.out.println(" All Marks:");
        OutHelper.printHMarks(loggerService.getAllMarks());


    }
}
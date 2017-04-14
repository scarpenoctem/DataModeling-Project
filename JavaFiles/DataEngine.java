import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;


/**
 * DataEngine - Main Class for the application, Use this to generate our objects in the global scope
 *
 * @author Sean Domingo, Michael Frederick, Megan Molumby, Mai Huong Nguyen,
 * Richard Pratt
 */

public class DataEngine {

    private SQLParser sqlParser;
    private FileReader fileReader;
    private UserInterface userInterface;
    private XMLToSQLGenerator xmlToSqlGenerator;
    private XMLToSQLParser xmlToSqlParser;
    private XSDParser xsdParser;
    
    private String currentDatabase;
    private String currentTime;

    public DataEngine() {
        this.sqlParser = new SQLParser();
        this.fileReader = new FileReader();
        this.userInterface = new UserInterface();
        this.xmlToSqlGenerator = new XMLToSQLGenerator();
        this.xmlToSqlParser = new XMLToSQLParser();
        this.xsdParser = new XSDParser();
        checkFilesystem();
        
        // Get time somewhere here.
        this.currentTime = new SimpleDateFormat("MM/dd/yyyy kk:mm:ss").format(new Timestamp(System.currentTimeMillis()));
    }

    /**
     * Actual entry point
     */
    public void runEngine() {
        this.currentDatabase = "";
        boolean running = true;
        String userInput = "";
        String firstToken;
        StringTokenizer tokenGenerator;
        
        // Show a program header?
        while (running) {
            userInput = this.userInterface.getInput();
            
            /*
            First: tokenGenerator.nextToken throws exception if no other token exists
            Second: you need to javadoc your methods.
            */
            
            if (userInput.equalsIgnoreCase("exit")) {
                this.userInterface.showUser("Goodbye");
                running = false;
            }
            else if (userInput.equalsIgnoreCase("help")) {
                if (this.currentDatabase == "") {
                    this.userInterface.showDatabaseHelp();
                }
                else {
                    this.userInterface.showTableHelp(currentDatabase);
                }
            }
            else {
                if (this.currentDatabase == "") {
                    // Pass data to Parser
                }
                else {
                    // Database Selected
                    // Pass data to Parser
                }
            }
        }
    }

    public void xsdParser() {
        this.fileReader.setInputFile();
        //modify to xml and xsd doc
        this.xsdParser.parseXSD(this.fileReader.getInputFile(), xmlToSqlParser);
    }

    /**
     * Checks if database and tables folders exist, creates them if necessary
     */
    private void checkFilesystem() {
        File temp;
        Path path = Paths.get("tables\\");
        if (Files.notExists(path)) {
            temp = new File("tables");
            temp.mkdir();
        }
        
        path = Paths.get("databases\\");
        if (Files.notExists(path)) {
            temp = new File("databases");
            temp.mkdir();
        }
    }
    
    
}

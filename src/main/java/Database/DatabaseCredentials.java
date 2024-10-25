package Database;

import javafx.scene.control.TextField;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * The DatabaseCredentials class provides methods to manage and retrieve database credentials.
 * It allows reading credentials from a file, setting them directly from user input,
 * and creating a credentials file.
 */
public class DatabaseCredentials{
        public static String DB_NAME;
        public static String DB_USER;
        public static String DB_PASS;

        /**
         * The getCredentialsFromFile() Method reads database credentials from a file and sets the values to the provided TextFields.
         * If the credentials file exists, it reads and assigns the database name, user, and password.
         *
         * @param dbname      the TextField that takes the database name.
         * @param dbuser      the TextField that takes the database user.
         * @param dbpassword  the TextField that takes the database password.
         */
        public static void getCredentialsFromFile(TextField dbname, TextField dbuser, TextField dbpassword){
                File credentials = new File("credentials.txt");

                if (credentials.exists()){
                        String[] allCredentials = new String[3];
                        int counter = 0;

                        try{
                                Scanner scanner = new Scanner(credentials);

                                while(scanner.hasNext()){
                                        allCredentials[counter] = scanner.next();
                                        counter++;
                                }

                        }catch (Exception e){
                                e.printStackTrace();
                        }

                        DB_NAME = allCredentials[0];
                        DB_USER = allCredentials[1];
                        DB_PASS = allCredentials[2];

                        System.out.println("All Credentials Read From File Successfully!");

                        dbname.setText(allCredentials[0]);
                        dbuser.setText(allCredentials[1]);
                        dbpassword.setText(allCredentials[2]);
                }
        }

        /**
         * The getCredentialsFromSignIn() Method sets database credentials from given input values.
         *
         * @param dataBaseName     the database name.
         * @param dataBaseUser     the database user.
         * @param databasePassword the database password.
         */
        public static void getCredentialsFromSignIn(String dataBaseName, String dataBaseUser, String databasePassword){
                DB_NAME = dataBaseName;
                DB_USER = dataBaseUser;
                DB_PASS = databasePassword;
        }

        /**
         * The createCredentials() Method creates a credentials file with the provided database name, user, and password.
         * Writes the credentials into a file named "credentials.txt".
         *
         * @param dataBaseName     the database name.
         * @param dataBaseUser     the database user.
         * @param databasePassword the database password.
         */
        public void createCredentials(String dataBaseName, String dataBaseUser, String databasePassword){
                File credentials = new File("credentials.txt");

                try{
                        credentials.createNewFile();

                        FileWriter writeCredentials = new FileWriter(credentials);
                        writeCredentials.write(dataBaseName + " " + dataBaseUser + " " + databasePassword);
                        writeCredentials.close();

                        System.out.println("Credential File Created With Credentials!");

                }catch(Exception e){
                        e.printStackTrace();
                }
        }
}

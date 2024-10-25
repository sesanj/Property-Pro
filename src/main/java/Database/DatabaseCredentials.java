package Database;

import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class DatabaseCredentials{
        public static String DB_NAME;
        public static String DB_USER;
        public static String DB_PASS;

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

        public static void getCredentialsFromSignIn(String dataBaseName, String dataBaseUser, String databasePassword){

                DB_NAME = dataBaseName;
                DB_USER = dataBaseUser;
                DB_PASS = databasePassword;
        }

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

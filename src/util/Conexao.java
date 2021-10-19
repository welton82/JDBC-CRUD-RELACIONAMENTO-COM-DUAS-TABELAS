package util;

import exception.Excessao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;

public class Conexao {
    private static Connection cnn = null;

    private static Properties loadProperties(){
        try(FileInputStream fs = new FileInputStream("coursejdbc.properties")){
            Properties prop = new Properties();
            prop.load(fs);
            return prop;
        } catch (FileNotFoundException e) {
            throw new Excessao(e.getMessage());
        } catch (IOException e) {
            throw new Excessao(e.getMessage());
        }
    }
    public static  Connection getConnection(){
        if(cnn == null){
            try {
                Properties prop = loadProperties();
                String url = prop.getProperty("url");
                cnn = DriverManager.getConnection(url, prop);
                if(cnn != null)
                    System.out.println("Connected!!");
                else
                    System.out.println("Connection Error!!!");
            } catch (SQLException e) {
                throw new Excessao(e.getMessage());
            }
        }
        return cnn;
    }

    public static  void closeRs(){
    }
    public static void closeConnection(){
        if(cnn != null){
            try {
                cnn.close();
                System.out.println("CONEXÃO COM BANCO DE DADOS FECHADO!!!");
                System.out.println("VOCÊ DEVE SAIR DO MENU");
            } catch (SQLException e) {
                throw new Excessao(e.getMessage());
            }
        }
    }
}

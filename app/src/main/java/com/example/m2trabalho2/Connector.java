package com.example.m2trabalho2;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;

public class Connector {

    Connection connection;
    String user, password, ip, port, database;
    @SuppressLint("API")

    public Connection connectionClass(){
        ip = "127.0.0.1";
        database = "cadastro";
        user = "root";
        password = "fernando";
        port = "3306";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection= null;

        String ConnectionURL = null;

        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL= "jdbc:jtds:sqlserver://"+ ip + ":"+ port+";"+ "databasename="+ database+";user="+user+";password="+password+";";
        }
        catch (Exception ex){
            Log.e("Error ", ex.getMessage());
        }
        return connection;
    }
}

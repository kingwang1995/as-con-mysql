package com.jw.mysqltext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    java.sql.Connection connection = DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/agentcommunity",
                            "root","jingwang1995");
                    String sql = "select userName from locationuserinfo";
                    Statement statement = (Statement)connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql);
                    while (resultSet.next()) {
                        String username = resultSet.getString("userName");
                        Log.i("Mainactivity",username);
                    }
                    connection.close();
                    statement.close();
                    resultSet.close();
                }catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

package com.infopulse.dao;

import com.infopulse.dao.transaction.TransactionManager;
import com.infopulse.dao.transaction.WrapConnection;
import com.infopulse.dto.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDao {
    private final String CLIENT_BY_CLIENT_ID = "select * from clients where client_id = ?";
    private final String SAVE_CLIENT = "insert into clients(client_id,name)values(?,?)";

    public Client getClientByClientId(String clientId) throws SQLException {
        WrapConnection connection = TransactionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CLIENT_BY_CLIENT_ID);
            preparedStatement.setString(1, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Client client = new Client();
                client.setId(resultSet.getLong("id"));
                client.setClientId(resultSet.getString("client_id"));
                client.setName(resultSet.getString("name"));
                return client;
            }
            return null;
        }finally{
            connection.close();
        }
    }

    public Client save(String clientId, String name) throws SQLException {

        WrapConnection connection = TransactionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_CLIENT);
            preparedStatement.setString(1, clientId);
            preparedStatement.setString(2, name);
            preparedStatement.execute();
            return getClientByClientId(clientId);
        } finally{
            connection.close();
        }
    }
}

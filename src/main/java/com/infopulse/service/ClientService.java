package com.infopulse.service;

import com.infopulse.dao.ClientDao;
import com.infopulse.dao.DaoFactory;
import com.infopulse.dao.transaction.TransactionManager;
import com.infopulse.dto.Client;

public class ClientService {
    private ClientDao clientDao = DaoFactory.getClientDao();
    private AddressDao addressDao = DaoFactory.getAddressDao();

    public Client save(Client client) throws Exception{
        try {
            TransactionManager.beginTransaction();
            Client currentClient = clientDao.getClientByClientId(client.getClientId());
            if (client != null) {
                throw new ClientAlreadyExists("Client already exists");
            }
            Client savedClient = clientDao.save(client.getClientId(), client.getName());
            client.getAddresses()
                    .stream()
                    .forEach(address -> addressDao.save(savedClient.getId(), address.getCity(), address.getStreet()));

        }catch(Exception ex){
            TransactionManager.rollBackTransaction();
            throw ex;
        }finally{
            TransactionManager.commitTransaction();
        }

    }
}

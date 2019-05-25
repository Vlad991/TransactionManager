package com.infopulse.dao;

public class DaoFactory {
    private static ClientDao clientDao = new ClientDao();
    private static AddressDao addressDao = new AddressDao();

    public static ClientDao getClientDao(){
        return clientDao;
    }

    public static AddressDao getAddressDao(){
        return addressDao;
    }
}

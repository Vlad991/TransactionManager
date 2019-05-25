package com.infopulse.controller;

import com.infopulse.dto.Address;
import com.infopulse.dto.Client;
import com.infopulse.service.ClientService;
import com.infopulse.service.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class ClientController extends HttpServlet {
    private ClientService clientService = ServiceFactory.getClientService();

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String clientId = request.getParameter("client_id");
        String name = request.getParameter("name");
        String workAddressCity = request.getParameter("work_address_city");
        String workAddressStreet = request.getParameter("work_address_street");
        String homeAddressCity = request.getParameter("home_address_city");
        String homeAddressStreet = request.getParameter("home_address_street");
        //validation
        Client client = new Client();
        client.setClientId(clientId);
        client.setName(name);

        Address homeAddress = new Address();
        homeAddress.setCity(homeAddressCity);
        homeAddress.setStreet(homeAddressStreet);

        Address workAddress =  new Address();
        workAddress.setCity(workAddressCity);
        workAddress.setStreet(workAddressStreet);

        client.setAddresses(Arrays.asList(homeAddress, workAddress));

        Client savedClient = clientService.save(client);

        request.setAttribute("client_id", savedClient.getClientId());
        request.setAttribute("name", savedClient.getName());
        // add addresses
        RequestDispatcher rd = request.getRequestDispatcher("/view_profile.jsp");
        rd.forward(request, response);
    }
}

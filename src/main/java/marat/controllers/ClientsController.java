package marat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import marat.DAO.AccountDAO;
import marat.DAO.ClientDAO;
import marat.DAO.ClientOfficeDAO;
import marat.DAO.OfficeDAO;
import marat.models.Account;
import marat.models.Client;
import marat.models.ClientOffice;
import marat.models.Office;
import marat.DAO.impl.AccountDAOImpl;
import marat.DAO.impl.ClientDAOImpl;
import marat.DAO.impl.ClientOfficeDAOImpl;
import marat.DAO.impl.OfficeDAOImpl;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientsController {
    @Autowired
    private final ClientDAO clientDAO = new ClientDAOImpl();

    @Autowired
    private final AccountDAO accountDAO = new AccountDAOImpl();

    @Autowired
    private final ClientOfficeDAO clientOfficeDAO = new ClientOfficeDAOImpl();

    @Autowired
    private final OfficeDAO officeDAO = new OfficeDAOImpl();

    @GetMapping("/clients")
    public String clients(Model model) {
        List<Client> clients = (List<Client>)clientDAO.getAll();

        model.addAttribute("clients", clients);
        model.addAttribute("clientService", clientDAO);

        return "clients";
    }

    @GetMapping("/client")
    public String client(@RequestParam(name = "clientId") Long clientId, Model model) {
        Client client = clientDAO.getById(clientId);

        if (client == null) {
            model.addAttribute("error_msg", "В базе нет такого человека: " + clientId);
            return "errorPage";
        }

        List<Account> accounts = new ArrayList<>();
        for (Account acc : accountDAO.getAll())
            if (acc.getClient_id().getId() == clientId)
                accounts.add(acc);

        List<Office> offices = clientOfficeDAO.GetOffice(clientId.intValue());

        model.addAttribute("client", client);
        model.addAttribute("accounts", accounts);
        model.addAttribute("offices", offices);
        model.addAttribute("clientService", clientDAO);
        model.addAttribute("accountService", accountDAO);

        return "client";
    }

    @GetMapping("/editClient")
    public String editClient(@RequestParam(name = "clientId") Long clientId, Model model) {
        Client client = clientDAO.getById(clientId);

        if (client == null) {
            model.addAttribute("error_msg", "В базе нет такого клиента");
            return "errorPage";
        }

        model.addAttribute("client", client);
        model.addAttribute("clientService", clientDAO);

        return "editClient";
    }

    @PostMapping("/saveClient")
    public String saveClient(@RequestParam(name = "clientId", required = false) Long clientId,
                               @RequestParam(name = "clientName") String name,
                               @RequestParam(name = "clientAddress") String address,
                               @RequestParam(name = "clientEmail") String email,
                               @RequestParam(name = "clientTel1") String telnumber1,
                               @RequestParam(name = "clientTel2") String telnumber2,
                               @RequestParam(name = "officeName", required = false) String officeName,
                               Model model) {
        Client client;
        if (clientId == null) {
            client = new Client(name, telnumber1, telnumber2, email, address);

            if (officeName != "") {
                boolean added = false;
                for (Office office : officeDAO.getAll()) {
                    System.out.println("debug " + officeName + " " + office.getName());
                    if (office.getName().trim().startsWith(officeName.trim())) {
                        ClientOffice clientOffice = new ClientOffice(office, client);
                        clientOfficeDAO.save(clientOffice);
                        added = true;
                        break;
                    }
                }
                if (!added) {
                    model.addAttribute("error_msg", "В базе нет такого отделения");
                    return "errorPage";
                }
            }

            clientDAO.save(client);
            return "redirect:/clients";
        } else {
            client = clientDAO.getById(clientId);

            if (client == null) {
                model.addAttribute("error_msg", "В базе нет такого клиента");
                return "errorPage";
            }

            client.setName(name);
            client.setAddress(address);
            client.setEmail(email);
            client.setTelnumber1(telnumber1);
            client.setTelnumber2(telnumber2);
            clientDAO.update(client);

            return String.format("redirect:/client?clientId=%d", client.getId());
        }
    }

    @PostMapping("/removeClient")
    public String removeClient(@RequestParam(name = "clientId") Long clientId) {
        clientDAO.deleteById(clientId);
        return "redirect:/clients";
    }
}

package marat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import marat.DAO.AccountDAO;
import marat.DAO.BankAccTypeDAO;
import marat.DAO.ClientDAO;
import marat.DAO.ClientOfficeDAO;
import marat.DAO.OfficeDAO;
import marat.DAO.impl.AccountDAOImpl;
import marat.DAO.impl.BankAccTypeDAOImpl;
import marat.DAO.impl.ClientDAOImpl;
import marat.DAO.impl.ClientOfficeDAOImpl;
import marat.DAO.impl.OfficeDAOImpl;
import marat.models.Account;
import marat.models.BankAccType;
import marat.models.Client;
import marat.models.ClientOffice;
import marat.models.Office;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OfficesController {
    @Autowired
    private final ClientOfficeDAO clientOfficeDAO = new ClientOfficeDAOImpl();

    @Autowired
    private final ClientDAO clientDAO = new ClientDAOImpl();

    @Autowired
    private final OfficeDAO officeDAO = new OfficeDAOImpl();

    @Autowired
    private final AccountDAO accountDAO = new AccountDAOImpl();

    @Autowired
    private final BankAccTypeDAO bankAccTypeDAO = new BankAccTypeDAOImpl();

    @GetMapping("/offices")
    public String offices(Model model) {
        /*List<Client> clients = new ArrayList<>();

        for (int i = 1; i <= 3; ++i) {
            clients.add(new Client("name" + i, "890987324" + i, "890987371" + i, "ajsdfhlkasdfh@gmaol.com", "Moscow d" + i));
        }

        clientDAO.saveCollection(clients);
        
        List<Office> officess = new ArrayList<>();

        for (int i = 1; i <= 3; ++i) {
            officess.add(new Office("name" + i, "890987324" + i, "890987371" + i, "ajsdfhlkasdfh@gmaol.com", "Moscow d" + i));
        }

        officeDAO.saveCollection(officess);

        Client cfix_1 = clientDAO.getById(1L);
        Client cfix_2 = clientDAO.getById(2L);
        Client cfix_3 = clientDAO.getById(3L);

        Office ofix_1 = officeDAO.getById(1L);
        Office ofix_2 = officeDAO.getById(2L);
        Office ofix_3 = officeDAO.getById(3L);

        List<ClientOffice> cliof = new ArrayList<>();
        cliof.add(new ClientOffice(ofix_1, cfix_1));
        cliof.add(new ClientOffice(ofix_2, cfix_1));
        cliof.add(new ClientOffice(ofix_3, cfix_1));
        cliof.add(new ClientOffice(ofix_1, cfix_2));
        cliof.add(new ClientOffice(ofix_2, cfix_3));
        cliof.add(new ClientOffice(ofix_3, cfix_3));

        clientOfficeDAO.saveCollection(cliof);

        List<Account> accs = new ArrayList<>();
        BankAccType bankAccType = new BankAccType("abc", 1.0, 1L, 1L, 1L, 1L, 1L);
        bankAccTypeDAO.save(bankAccType);
        accs.add(new Account(cfix_1,ofix_1, bankAccType, 0L, 0L, 10000L));
        accs.add(new Account(cfix_1,ofix_1, bankAccType, 0L, 0L, 10000L));
        accs.add(new Account(cfix_2,ofix_3, bankAccType, 0L, 0L, 18000L));
        accs.add(new Account(cfix_3,ofix_2, bankAccType, 0L, 0L, 200000L));

        accountDAO.saveCollection(accs);*/
        
        List<Office> offices = (List<Office>)officeDAO.getAll();

        model.addAttribute("offices", offices);
        model.addAttribute("officeService", officeDAO);

        return "offices";
    }

    @GetMapping("/office")
    public String office(@RequestParam(name = "officeId") Long officeId, Model model) {
        Office office = officeDAO.getById(officeId);

        if (office == null) {
            model.addAttribute("error_msg", "В базе нет такого офиса");
            return "errorPage";
        }

        List<Client> clients = new ArrayList<>();
        for (ClientOffice co : clientOfficeDAO.getAll())
            if (co.getOffice().getId() == officeId)
                clients.add(co.getClient());

        model.addAttribute("office", office);
        model.addAttribute("clients", clients);
        model.addAttribute("accountService", clientOfficeDAO);
        model.addAttribute("officeService", officeDAO);
        model.addAttribute("clientService", clientDAO);

        return "office";
    }

    @GetMapping("/editOffice")
    public String editOffice(@RequestParam(name = "officeId") Long officeId, Model model) {
        Office office = officeDAO.getById(officeId);

        if (office == null) {
            model.addAttribute("error_msg", "В базе нет такого офиса");
            return "errorPage";
        }

        List<Client> clients = new ArrayList<>();
        for (ClientOffice co : clientOfficeDAO.getAll())
            if (co.getOffice().getId() == officeId)
                clients.add(co.getClient());

        model.addAttribute("office", office);
        model.addAttribute("clients", clients);
        model.addAttribute("accountService", clientOfficeDAO);
        model.addAttribute("officeService", officeDAO);
        model.addAttribute("clientService", clientDAO);

        return "editOffice";
    }

    @PostMapping("/saveOffice")
    public String saveOffice(@RequestParam(name = "officeId", required = false) Long officeId,
                             @RequestParam(name = "officeName") String name,
                             @RequestParam(name = "officeAddress") String address,
                             @RequestParam(name = "officeEmail") String email,
                             @RequestParam(name = "officeTel1") String telnumber1,
                             @RequestParam(name = "officeTel2") String telnumber2,
                             Model model) {
        Office office;
        if (officeId == null) {
            office = new Office(name, telnumber1, telnumber2, email, address);
            officeDAO.save(office);
            return "redirect:/offices";
        } else {
            office = officeDAO.getById(officeId);

            if (office == null) {
                model.addAttribute("error_msg", "В базе нет такого клиента");
                return "errorPage";
            }

            office.setName(name);
            office.setAddress(address);
            office.setEmail(email);
            office.setTelnumber1(telnumber1);
            office.setTelnumber2(telnumber2);
            officeDAO.update(office);

            return String.format("redirect:/office?officeId=%d", office.getId());
        }
    }

    @PostMapping("/removeOffice")
    public String removeOffice(@RequestParam(name = "officeId") Long officeId) {
        officeDAO.deleteById(officeId);
        return "redirect:/offices";
    }
}

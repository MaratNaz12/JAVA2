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
import marat.DAO.OfficeDAO;
import marat.DAO.impl.AccountDAOImpl;
import marat.DAO.impl.BankAccTypeDAOImpl;
import marat.DAO.impl.ClientDAOImpl;
import marat.DAO.impl.OfficeDAOImpl;
import marat.models.Account;

import java.util.List;

@Controller
public class AccountsController {
    @Autowired
    private final AccountDAO accountDAO = new AccountDAOImpl();

    @Autowired
    private final ClientDAO clientDAO = new ClientDAOImpl();

    @Autowired
    private final OfficeDAO officeDAO = new OfficeDAOImpl();

    @Autowired
    private final BankAccTypeDAO bankAccTypeDAO = new BankAccTypeDAOImpl();

    @GetMapping("/accounts")
    public String accounts(Model model) {
        List<Account> accounts = (List<Account>)accountDAO.getAll();

        model.addAttribute("accounts", accounts);
        model.addAttribute("accountService", accountDAO);
        model.addAttribute("officeService", officeDAO);
        model.addAttribute("clientService", clientDAO);

        return "accounts";
    }

    @GetMapping("/account")
    public String account(@RequestParam(name = "accountId") Long accountId, Model model) {
        Account account = accountDAO.getById(accountId);

        if (account == null) {
            model.addAttribute("error_msg", "В базе нет такого счета");
            return "errorPage";
        }

        model.addAttribute("account", account);
        model.addAttribute("accountService", accountDAO);
        model.addAttribute("officeService", officeDAO);
        model.addAttribute("clientService", clientDAO);

        return "account";
    }

    @GetMapping("/editAccount")
    public String editAccount(@RequestParam(name = "accountId") Long accountId, Model model) {
        Account account = accountDAO.getById(accountId);

        if (account == null) {
            model.addAttribute("error_msg", "В базе нет такого счета");
            return "errorPage";
        }

        model.addAttribute("account", account);
        model.addAttribute("accountService", accountDAO);
        model.addAttribute("officeService", officeDAO);
        model.addAttribute("clientService", clientDAO);

        return "editAccount";
    }

    @PostMapping("/saveAccount")
    public String saveAccount(@RequestParam(name = "accountId", required = false) Long accountId,
                               @RequestParam(name = "accountCuraccum") Long curaccum,
                               @RequestParam(name = "accountCurbalance") Long curbalance,
                               @RequestParam(name = "accountClientId", required = false) Long clientId,
                               @RequestParam(name = "accountOfficeId", required = false) Long officeId,
                               Model model) {
        Account account;
        if (accountId == null) {
            account = new Account(clientDAO.getById(clientId), officeDAO.getById(officeId), bankAccTypeDAO.getById(1L), 0L, curaccum, curbalance);
            accountDAO.save(account);
            return "redirect:/accounts";
        } else {
            account = accountDAO.getById(accountId);

            if (account == null) {
                model.addAttribute("error_msg", "В базе нет такого клиента");
                return "errorPage";
            }

            account.setCuraccum(curaccum);
            account.setCurbalance(curbalance);
            accountDAO.update(account);

            return String.format("redirect:/account?accountId=%d", account.getId());
        }
    }

    @PostMapping("/removeAccount")
    public String removeAccount(@RequestParam(name = "accountId") Long accountId) {
        accountDAO.deleteById(accountId);
        return "redirect:/accounts";
    }
}

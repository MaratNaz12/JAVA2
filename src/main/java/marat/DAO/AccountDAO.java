package marat.DAO;

import marat.models.Account;

import java.util.List;

public interface AccountDAO extends CommonDAO<Account, Long> {


    public List<Account> GetWithFilter(String fieldName, int value);

    public List<Account> GetWithFilterBalanceRange(int lo, int hi);

    public List<Account> GetWithFilterBalanceFree(String sign, int value);

}
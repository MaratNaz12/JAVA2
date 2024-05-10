package marat.DAO;


import marat.models.Account;
import marat.models.Operation;

import java.sql.Timestamp;
import java.util.List;

public interface OperationDAO extends CommonDAO<Operation> {

    public List<Operation> GetByTo(Account client);

    public List<Operation> GetByFrom(Account client);


    public List<Operation> GetWithFilterRangeSum(int lo, int hi);

    public List<Operation> GetWithFilterFree(String sign, int value);


    public List<Operation> GetWithFilterRangeTime(Timestamp t1, Timestamp t2);

    public List<Operation> GetWithFilterFreeTime(String sign, Timestamp t2);

}

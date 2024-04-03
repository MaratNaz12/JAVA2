package marat.DAO;


import marat.models.Operation;

import java.util.Collection;
import java.util.List;

public interface OperationDAO {

    public List<Operation> GetWithFilterRange(String fieldName, int lo, int hi);

    public List<Operation> GetWithFilterFree(String fieldName, String sign, int value);

}

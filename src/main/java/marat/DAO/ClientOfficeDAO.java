package marat.DAO;

import marat.models.Client;
import marat.models.ClientOffice;
import marat.models.Office;

import java.util.List;

public interface ClientOfficeDAO extends CommonDAO<ClientOffice> {


    public List<Client> GetClient(int id);

    public List<Office> GetOffice(int id);

}

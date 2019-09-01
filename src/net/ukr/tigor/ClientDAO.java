package net.ukr.tigor;

import java.util.List;

public interface ClientDAO {
    public void initBD();
    public void insertClient(Client client);
    public void deleteClient(Client client);
    public List<Client> selectClients();
}

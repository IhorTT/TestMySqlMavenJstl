package net.ukr.tigor;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ServiceSqlImpl implements ClientDAO {

    private final Connection conn;

    public ServiceSqlImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void initBD() {
        try {

            try (Statement st = conn.createStatement()){
                st.execute("DROP TABLE IF EXISTS Clients");
                st.execute("CREATE TABLE Clients (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, fullName VARCHAR(100) NOT NULL, phone VARCHAR(20))");
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void insertClient(Client client) {
        try {
            try (PreparedStatement st = conn.prepareStatement("INSERT INTO Clients (fullName, phone) VALUES(?, ?)")) {
                st.setString(1, client.getFullName());
                st.setString(2, client.getPhone());
                st.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteClient(Client client) {
        try {
            try (PreparedStatement st = conn.prepareStatement("DELETE FROM Clients WHERE fullName = ? AND phone = ?")) {
                st.setString(1, client.getFullName());
                st.setString(2, client.getPhone());
                st.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Client> selectClients() {
        List<Client> result = new ArrayList<Client>();

        try {
            try (Statement st = conn.createStatement()) {
                try (ResultSet rs = st.executeQuery("SELECT fullName, phone FROM Clients")) {
                    while (rs.next()) {
                        Client client = new Client();
                        client.setFullName(rs.getString(1));
                        client.setPhone(rs.getString(2));
                        result.add(client);
                    }
                }
            }
            return result;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}

package net.ukr.tigor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ClietnsServlet extends HttpServlet {

    private static final String URL = "jdbc:mysql://localhost:3306/shop?serverTimezone=Europe/Kiev";
    private final String USER = "root";
    private final String PASSWORD = "root";
    private ServiceSqlImpl service;

    @Override
    public void init() throws ServletException {
        super.init();
        service = new ServiceSqlImpl(new ConnectionFactory(URL, USER, PASSWORD).getConnection());
        service.initBD();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String command = req.getParameter("command");
        String fullName = req.getParameter("fullName");
        String phone = req.getParameter("phone");
        String auto = req.getParameter("auto");

        switch (command) {
            case "Add": {
                service.insertClient(new Client(fullName, phone));
                if (auto!=null) {
                    sendLis(req, resp, auto);
                } else {
                    resp.sendRedirect("index.jsp");
                }

                break;
            }
            case "Delete": {
                service.deleteClient(new Client(fullName, phone));
                if (auto!=null) {
                    sendLis(req, resp, auto);
                } else {
                    resp.sendRedirect("index.jsp");
                }
                break;
            }
            case "List": {
                List<Client> cl = service.selectClients();
                req.setAttribute("clients", cl);
                req.setAttribute("auto", auto);
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
                rd.forward(req, resp);
                break;
            }
        }

    }

    private void sendLis(HttpServletRequest req, HttpServletResponse resp, String auto) {
        List<Client> cl = service.selectClients();
        req.setAttribute("clients", cl);
        req.setAttribute("auto", auto);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
        try {
            rd.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

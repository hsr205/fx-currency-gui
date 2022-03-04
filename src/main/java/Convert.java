import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.StringTokenizer;

/**
 * @author pakallis
 */
class Recv {
    private String lhs;
    private String rhs;
    private String error;
    private String icc;

    public Recv() {
    }

    public String getLhs() {
        return lhs;
    }

    public String getRhs() {
        return rhs;
    }
}

public class Convert extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = "";
        String amount = "";
        String curTo = "";
        String curFrom = "";
        String submit = "";
        String res = "";
        HttpSession session;
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        /*Read request parameters*/
        amount = req.getParameter("amount");
        curTo = req.getParameter("to");
        curFrom = req.getParameter("from");
        /*Open a connection to google and read the result*/

        try {
            query = "http://www.google.com/ig/calculator?hl=en&q=" + amount + curFrom + "=?" + curTo;
            URL url = new URL(query);
            InputStreamReader stream = new InputStreamReader(url.openStream());
            BufferedReader in = new BufferedReader(stream);
            String str = "";
            String temp = "";
            while ((temp = in.readLine()) != null) {
                str = str + temp;
            }

            /*Parse the result which is in json format*/
            Gson gson = new Gson();
            Recv st = gson.fromJson(str, Recv.class);
            String rhs = st.getRhs();
            rhs = rhs.replaceAll("�", "");
            /*we do the check in order to print the additional word(millions,billions etc)*/
            StringTokenizer strto = new StringTokenizer(rhs);
            String nextToken;

            out.write(strto.nextToken());
            nextToken = strto.nextToken();

            if (nextToken.equals("million") || nextToken.equals("billion") || nextToken.equals("trillion")) {
                out.println(" " + nextToken);
            }
        } catch (NumberFormatException e) {
            out.println("The given amount is not a valid number");
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

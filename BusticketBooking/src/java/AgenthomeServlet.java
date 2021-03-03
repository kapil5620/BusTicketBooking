
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/AgenthomeServlet"})
public class AgenthomeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        PrintWriter pw= response.getWriter();
            
            HttpSession session = request.getSession();
            String adminid=(String)session.getAttribute("Adminid");
            
            pw.print("<html><body>"
                    +"1.<a href=\"ListBusServlet\">List the Bus Details</a><br>"
                    +"2.<a href=\"EnterBusidServlet\">Book Ticket</a><br>"
                    +"3.<a href=\"showhistoryServlet\">Show My Booking</a><br>"
                    +"4.<a href=\"AgentLogoutServlet\">Logout</a><br>"
                    + "</body></html>");
            
    }

}

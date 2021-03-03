
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/AdminhomeServlet"})
public class AdminhomeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            PrintWriter pw= response.getWriter();
            
            HttpSession session = request.getSession();
            String adminid=(String)session.getAttribute("Adminid");
            
            pw.print("<html><body>"
                    +"1.<a href=\"BusServlet\">Add Bus</a><br>"
                    +"2.<a href=\"AgentServlet\">Add Agent</a><br>"
                    +"3.<a href=\"AdminLogoutServlet\">Logout</a><br>"
                    + "</body></html>");
    }


}

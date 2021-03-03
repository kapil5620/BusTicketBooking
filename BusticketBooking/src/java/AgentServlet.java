
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/AgentServlet"})
public class AgentServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            PrintWriter pw = response.getWriter();
                    response.setContentType("text/html");
                   pw.print("<html><body>"
                           +"<form method='get' action='\\BusTicketBooking\\AgententryServlet'>"
                           +"Agent name : <input type='text' name='agentname' required><br>"
                           +"Agent mobile number : <input type='text' name='mobile' required><br>"
                           +"Agent password : <input type='password' name='pass' required><br>"
                           +"<input type='submit' value='submit'>" 
                           +"</form>"
                           + "</body></html>");
    }


}

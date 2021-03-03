
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/BusServlet"})
public class BusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
                      PrintWriter pw = response.getWriter();
                    response.setContentType("text/html");
                   pw.print("<html><body>"
                           +"<form method='get' action='\\BusTicketBooking\\BusentryServlet'>"
                           +"Bus name : <input type='text' name='busname' required><br>"
                           +"Bus From point : <input type='text' name='from' required><br>"
                           +"Bus To point : <input type='text' name='to' required><br>"
                           +"Total number of seats: <input type='number' name='seats' required><br>"
                           +"Price per seat: <input type='number' name='price' required><br>"
                           +"<input type='submit' value='submit'>" 
                           +"</form>"
                           + "</body></html>");
    }

}

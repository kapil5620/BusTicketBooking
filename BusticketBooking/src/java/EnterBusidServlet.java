
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/EnterBusidServlet"})
public class EnterBusidServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        PrintWriter pw = response.getWriter();
                    response.setContentType("text/html");
                   pw.print("<html><body>"
                           +"<form method='get' action='\\BusTicketBooking\\ShowBusidServlet'>"
                           +"Enter Bus id : <input type='number' name='busid' required><br>"
                           +"<input type='submit' value='submit'>" 
                           +"</form>"
                           + "</body></html>");
        
    }

}

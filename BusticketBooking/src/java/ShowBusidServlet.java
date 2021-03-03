
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/ShowBusidServlet"})
public class ShowBusidServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             PrintWriter pw=response.getWriter();
        HttpSession session = request.getSession();
            String busid=request.getParameter("busid");
            String Drivername = "com.mysql.jdbc.Driver";
            String url="jdbc:mysql://localhost:3306/busticketbooking";
            String dbuser="root";
            String dbpass="12345";
            String sql="Select * from bus where bus_id="+busid+";";
        try{
                Class.forName(Drivername);
                Connection con=DriverManager.getConnection(url,dbuser,dbpass);
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery(sql);
                if(rs.next()){
                    pw.print("<html><body><table border='2' cellpadding=10>"
                   +"<tr><td>Bus id</td><td> Bus Nam</td><td> From </td><td> To </td><td> Seats Available</td><td> Price per ticket</td></tr>");
                    pw.print("<tr><td><b>"+rs.getString("bus_id")+"</b></td><td>"+rs.getString("bus_name")+"</td><td>"+rs.getString("start")+"</td><td>"+rs.getString("end")+"</td><td>"+rs.getString("total_seat")+"</td><td>"+rs.getString("price")+"</td></tr>");
                    pw.print("</table><br>");
                    
                    pw.print("<form method='get' action='\\BusTicketBooking\\BookTicketServlet'>"
                            +"Bus id : <input type='number' name='busid' value="+busid+" readonly><br>"
                            +"Enter the number of Tickets to book : <input type='number' name='tickets' required><br>"
                            + "<input type='submit' value='Check Availability'></form>");
                    
                    pw.print("</body></html>");
                }
                else{
                    RequestDispatcher rd = request.getRequestDispatcher("EnterBusidServlet");
                    rd.include(request, response);
                    pw.print("<html><body><script>alert(\"Invalid Busid\")</script></body></html>");
                }
        }
        catch(Exception e){
            pw.print(e);
        }
    }

}

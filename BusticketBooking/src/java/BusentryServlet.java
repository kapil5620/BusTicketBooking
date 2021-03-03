
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/BusentryServlet"})
public class BusentryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            PrintWriter pw = response.getWriter();
            response.setContentType("text/html");
           HttpSession session = request.getSession();
            
            String Bus_name=request.getParameter("busname");
            String start=request.getParameter("from");
            String end=request.getParameter("to");
            String seats=request.getParameter("seats");
            String price=request.getParameter("price");
            String Drivername = "com.mysql.jdbc.Driver";
            String url="jdbc:mysql://localhost:3306/busticketbooking";
            String dbuser="root";
            String dbpass="12345";
            String sql="Insert into bus(bus_name,start,end,total_seat,price) values('"
                    +Bus_name+"','"+start+"','"+end+"',"+seats+","+price+");";
        try{
                Class.forName(Drivername);
                Connection con=DriverManager.getConnection(url,dbuser,dbpass);
                Statement st=con.createStatement();
                st.executeUpdate(sql);
                RequestDispatcher rd = request.getRequestDispatcher("BusServlet");
                rd.include(request, response);
                pw.print("<html><body><script>alert(\"Successfully added the bus\")</script></body></html>");
        }
        catch(Exception e){
            pw.print(e);
        }
    }

}

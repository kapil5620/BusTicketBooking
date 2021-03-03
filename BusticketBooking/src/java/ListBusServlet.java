
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

@WebServlet(urlPatterns = {"/ListBusServlet"})
public class ListBusServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            PrintWriter pw=response.getWriter();
        HttpSession session = request.getSession();
            
            String Drivername = "com.mysql.jdbc.Driver";
            String url="jdbc:mysql://localhost:3306/busticketbooking";
            String dbuser="root";
            String dbpass="12345";
            String sql="Select * from bus";
        try{
                Class.forName(Drivername);
                Connection con=DriverManager.getConnection(url,dbuser,dbpass);
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery(sql);
                pw.print("<html><body><table border='2' cellpadding=10>"
                +"<tr><td>Bus id</td><td> Bus Nam</td><td> From </td><td> To </td><td> Seats Available</td><td> Price per ticket</td></tr>");
                while(rs.next()){
                    pw.print("<tr><td><b>"+rs.getString("bus_id")+"</b></td><td>"+rs.getString("bus_name")+"</td><td>"+rs.getString("start")+"</td><td>"+rs.getString("end")+"</td><td>"+rs.getString("total_seat")+"</td><td>"+rs.getString("price")+"</td></tr>");
                }
                pw.print("</table></body></html>");
        }
        catch(Exception e){
            pw.print(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
    }


}


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/showhistoryServlet"})
public class showhistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            PrintWriter pw=response.getWriter();
        HttpSession session = request.getSession();
        String agentid=(String)session.getAttribute("Agentid");
            String Drivername = "com.mysql.jdbc.Driver";
            String url="jdbc:mysql://localhost:3306/busticketbooking";
            String dbuser="root";
            String dbpass="12345";
            String sql="Select * from "+"T"+agentid;
        try{
                Class.forName(Drivername);
                Connection con=DriverManager.getConnection(url,dbuser,dbpass);
                Statement st=con.createStatement();
                ResultSet rs=st.executeQuery(sql);
                pw.print("<html><body><table border='2' cellpadding=10>"
                +"<tr><td> Bus Name</td><td> From </td><td> To </td><td>No of ticket booked</td><td> Total fare</td></tr>");
                while(rs.next()){
                    pw.print("<tr><td><b>"+rs.getString(1)+"</b></td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td></tr>");
                }
                pw.print("</table></body></html>");
        }
        catch(Exception e){
            pw.print(e);
        }
    }

}

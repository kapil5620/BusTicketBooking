
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

@WebServlet(urlPatterns = {"/Confirmbookservlet"})
public class Confirmbookservlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            PrintWriter pw=response.getWriter();
        HttpSession session = request.getSession();
        String agentid=(String)session.getAttribute("Agentid");
            response.setContentType("text/html");
            int busid=Integer.valueOf(request.getParameter("busid"));
            int tickets=Integer.valueOf(request.getParameter("tickets"));
            String Drivername = "com.mysql.jdbc.Driver";
            String url="jdbc:mysql://localhost:3306/busticketbooking";
            String dbuser="root";
            String dbpass="12345";
            String query="Select * from bus where bus_id="+busid+";";
        try{
                Class.forName(Drivername);
                Connection con=DriverManager.getConnection(url,dbuser,dbpass);
                Statement st=con.createStatement(),st2=con.createStatement(),st3=con.createStatement();
                ResultSet rs=st.executeQuery(query);
                if(rs.next()){
                    String Bus=rs.getString(1);
                         String From=rs.getString(2);
                          String To=rs.getString(3);
                          int total_seat=Integer.valueOf(rs.getString(4));
                          int price=Integer.valueOf(rs.getString(5));
                          int total_fare=tickets*price;
                          st2.executeUpdate("Insert into "+"T"+agentid+" values('"+Bus+"','"+From+"','"+To+"',"+tickets+","+total_fare+");");
                          st3.executeUpdate("Update bus set total_seat = "+(total_seat - tickets)+" where bus_id ="+busid+";");
                          
                      RequestDispatcher rd = request.getRequestDispatcher("AgenthomeServlet");
                    rd.include(request, response);
                    pw.print("<html><body><script>alert(\"Sucessfully booked\")</script></body></html>");
                        
                }
        }
        catch(Exception e){
            pw.print(e);
        } 
    }

}


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

@WebServlet(urlPatterns = {"/BookTicketServlet"})
public class BookTicketServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            PrintWriter pw=response.getWriter();
        HttpSession session = request.getSession();
            String busid=request.getParameter("busid");
            int tickets=Integer.valueOf(request.getParameter("tickets"));
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
                    int seats=Integer.valueOf(rs.getString("total_seat"));
                    if(seats>=tickets){
                        String Bus=rs.getString(1);
                         String From=rs.getString(2);
                          String To=rs.getString(3);
                          int price=Integer.valueOf(rs.getString(5));
                          int total_fare=tickets*price;
                        pw.print("<html><body>"
                                +"Bus Name : "+Bus+"<br>"
                                        +"From : "+From+"<br>"
                                                +"To : "+To+"<br>"
                                                                +"Total far : "+total_fare+"<br>"
                                 +"<form method='post' action='\\BusTicketBooking\\Confirmbookservlet'>"
                                                           +"Bus id : <input type='number' name='busid' value="+busid+" readonly><br>"
                                                            +"Total number of seats to book : <input type='number' name='tickets' value="+tickets+" readonly><br>"
                                                                        + "<input type='submit' value='click here to confirm the tickets'>"
                                + "</body><html>");
                    }
                }
            }
        catch(Exception e){ pw.println(e);}
    }


}


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

@WebServlet(urlPatterns = {"/AgententryServlet"})
public class AgententryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter pw=response.getWriter();
        HttpSession session = request.getSession();
            
            String Agent_name=request.getParameter("agentname");
            String mobile=request.getParameter("mobile");
            String pass=request.getParameter("pass");
            String Drivername = "com.mysql.jdbc.Driver";
            String url="jdbc:mysql://localhost:3306/busticketbooking";
            String dbuser="root";
            String dbpass="12345";
            String sql="Insert into agent(agent_name,mobilenumber,password) values('"
                    +Agent_name+"','"+mobile+"','"+pass+"');";
            String getid="Select * from agent where agent_name='"+Agent_name+"' and mobilenumber='"+mobile+"' and password='"+pass+"';";
            String id="";
            try{
                Class.forName(Drivername);
                Connection con=DriverManager.getConnection(url,dbuser,dbpass);
                Statement st=con.createStatement(),st2=con.createStatement(),st3=con.createStatement();
                
                st.executeUpdate(sql);
                
                ResultSet rs=st2.executeQuery(getid);
                if(rs.next()){
                    id=rs.getString("agent_id");
                }
                st3.executeUpdate("create table "+"T"+id+" (Bus_name varchar(50), start varchar(50), end varchar(50), No_of_tickects int, total_fare int);");
                RequestDispatcher rd = request.getRequestDispatcher("AgentServlet");
                rd.include(request, response);
                pw.print("<html><body><script>alert(\"Successfully added the agent\")</script></body></html>");
        }
        catch(Exception e){
            pw.print(e);
        }
            
    }


}

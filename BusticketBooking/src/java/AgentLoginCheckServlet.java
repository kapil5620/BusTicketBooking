
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

@WebServlet(urlPatterns = {"/AgentLoginCheckServlet"})
public class AgentLoginCheckServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        PrintWriter pw= response.getWriter();
        
        String agentid=request.getParameter("Agentid");
        String agentpass=request.getParameter("Agentpass");
        
        HttpSession session = request.getSession();
        session.setAttribute("Agentid",agentid);
        
       response.setContentType("text/html");
          String Drivername = "com.mysql.jdbc.Driver";
            String url="jdbc:mysql://localhost:3306/busticketbooking";
            String dbuser="root";
            String dbpass="12345";
            String sql="Select * from agent where agent_id='"+agentid+"' and password='"+agentpass+"';";
        try{
                Class.forName(Drivername);
                Connection con=DriverManager.getConnection(url,dbuser,dbpass);
                Statement st=con.createStatement();
                ResultSet rs= st.executeQuery(sql);
                if(rs.next()){
                    session.setAttribute("Agentid",agentid);
                    RequestDispatcher rd = request.getRequestDispatcher("/AgenthomeServlet");
                    rd.forward(request, response);
                }
                else{
                    RequestDispatcher rd = request.getRequestDispatcher("AgentLogin.html");
                    rd.include(request, response);
                    pw.print("<html><body><script>alert(\"Invalid Agentid or Password\")</script></body></html>");
                }
                
        }
        catch(Exception e){ pw.print(e);}
       
    }

}

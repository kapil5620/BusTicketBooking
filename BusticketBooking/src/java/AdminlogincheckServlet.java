
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

@WebServlet(urlPatterns = {"/AdminlogincheckServlet"})
public class AdminlogincheckServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        PrintWriter pw= response.getWriter();
        
        String adminid=request.getParameter("Adminid");
        String adminpass=request.getParameter("Adminpass");
        
        HttpSession session = request.getSession();
        session.setAttribute("Adminid",adminid);
        
       response.setContentType("text/html");
          String Drivername = "com.mysql.jdbc.Driver";
            String url="jdbc:mysql://localhost:3306/busticketbooking";
            String dbuser="root";
            String dbpass="12345";
            String sql="Select * from admin where adminid='"+adminid+"' and password='"+adminpass+"';";
        try{
                Class.forName(Drivername);
                Connection con=DriverManager.getConnection(url,dbuser,dbpass);
                Statement st=con.createStatement();
                ResultSet rs= st.executeQuery(sql);
                if(rs.next()){
//                    pw.print("success");
                    RequestDispatcher rd = request.getRequestDispatcher("/AdminhomeServlet");
                    rd.forward(request, response);
                }
                else{
                    RequestDispatcher rd = request.getRequestDispatcher("Adminlogin.html");
                    rd.include(request, response);
                    pw.print("<html><body><script>alert(\"Invalid Adminid or Password\")</script></body></html>");
//                    pw.print("No");
                }
                
        }
        catch(Exception e){ pw.print(e);}
       
        
    }

}

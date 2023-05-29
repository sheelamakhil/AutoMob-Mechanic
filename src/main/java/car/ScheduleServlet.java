package car;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ScheduleServlet
 */
public class ScheduleServlet extends HttpServlet {
	Connection con;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ScheduleServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
  public void init(ServletConfig config) throws ServletException 
  {
	  try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		  con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","AKHIL");
	} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  public void destroy()
  {
	  try {
		con.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String b1 = request.getParameter("adate");
			String b2 = request.getParameter("address");
			String b3 = request.getParameter("city");
			String b4 = request.getParameter("state");
			String b5 = request.getParameter("notify");
			PreparedStatement pstmt = con.prepareStatement("insert into schedule values(?,?,?,?,?)");
			pstmt.setString(1, b1);
			pstmt.setString(2, b2);
			pstmt.setString(3, b3);
			pstmt.setString(4, b4);
			pstmt.setString(5, b5);
			pstmt.executeUpdate();
   
			PrintWriter pw = response.getWriter();
			pw.println("<html> <body>");
			pw.println("<a href=home.html><h2><u>AutoMob-Mechanic</u></h2></a> <br>");
			pw.println("<a href=\"service.html\">Home</a> | <a href=service.html>Services</a> | <a href=booking.html>Booking</a> | <a href=mail>contact@automod=b.co.in</a> |   999 000 9999" );
			
			pw.println("  <hr> <br> <br> <br> <br> <center> <img src=\"D:\\Auto Mob-Mechanic\\images\\thanku.jpg\"  </center> <br> <br> <br> <br>");
			
			pw.println("<hr> <center> <footer> Copyright &copy; 2022 AutoMob-Mechanic. All Rights Reserved. </footer> </center>");
			pw.println("</body> </html>");
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

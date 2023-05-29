package car;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BookingServlet
 */
public class BookingServlet extends HttpServlet {
	Connection con;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			  con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","AKHIL");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
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
			String s1 = request.getParameter("name");
			String s2 = request.getParameter("email");
			String s3 = request.getParameter("pnum");
			String s4 = request.getParameter("cmake");
			String s5 = request.getParameter("cmodel");
			String s6 = request.getParameter("fuel");
			String s7 = request.getParameter("km");
			PreparedStatement pstmt = con.prepareStatement("insert into booking values(?,?,?,?,?,?,?)");
			pstmt.setString(1, s1);
			pstmt.setString(2, s2);
			pstmt.setString(3, s3);
			pstmt.setString(4, s4);
			pstmt.setString(5, s5);
			pstmt.setString(6, s6);
			pstmt.setString(7, s7);
			pstmt.executeUpdate();
			PrintWriter pw = response.getWriter();
			pw.println("<html> <body>");
			pw.println("<a href=home.html><h2><u>AutoMob-Mechanic</u></h2></a>");
			pw.println("<a href=\"service.html\">Home</a> | <a href=service.html>Services</a> | <a href=Booking.html>Booking</a> | <a href=mail>contact@automod=b.co.in</a> |   999 000 9999");
			
			pw.println("<hr> <form action=schedule method=post>");
			pw.println("<h3>Schedule Your Appointment</h3><br>");
			pw.println(" Appointment Date: <input type=adate name=adate> <br> ");
			pw.println("Address: <input type=text name=address> <br>");
			pw.println("City: <input type=text name=city placeholder=Karimnagar> <br>");
			pw.println("State: <input type=text name=state placeholder=Telangana>  <br>");
			pw.println("Send Notification: <input type=checkbox> Email<input type=checkbox> SMS <br>");
			pw.println("<input type=submit value=Book> <br> </form>");
			
			pw.println("<hr> <footer> Copyright &copy; 2022 AutoMob-Mechanic. All Rights Reserved. </footer>");
			pw.println("</body> </html>");
			
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		
	}

}

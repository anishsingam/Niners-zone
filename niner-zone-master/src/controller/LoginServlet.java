package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.CookieManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.HTTP;

import db.DbConnection;
import model.UserBean;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userid = request.getParameter("userName");
		String passcode = request.getParameter("userPassword").trim();

		String username = "";
		String password = "";
		String usertype = "";
		String admin = "";
		int approvalflag = 0;
		DbConnection conn = null;
		Statement stmt = null;
		try {
			conn = new DbConnection();
			String sql = "SELECT login_credential.username,login_credential.user_password,login_credential.approval_flag,user.user_type from login_credential inner join user on login_credential.username=user.username where login_credential.username = '"
					+ userid + "'";
			stmt = conn.DbConnectionForPreparedStatement(sql);
			PrintWriter writer = response.getWriter();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				username = rs.getString("username");
				password = rs.getString("user_password");
				usertype = rs.getString("user_type");
				approvalflag = rs.getInt("approval_flag");
			}
			if (!passcode.equals(password)) {
				request.setAttribute("error","Invalid Username or Password"); 
				RequestDispatcher rd=request.getRequestDispatcher("/login.jsp"); 
				rd.include(request, response);
			} else {
				if (approvalflag == 0) {
					response.sendRedirect("confirmation_page.jsp");
					// Since a object is used to compare the if changes a bit to
					// the below format
				} else if (approvalflag == 1 && usertype.equals("student")) {
					HttpSession user_session = request.getSession(true);
					user_session.setMaxInactiveInterval(30 * 60);
					Cookie userName = new Cookie("userNAME", username);
					response.addCookie(userName);
					String encodedURL = response
							.encodeRedirectURL("student_home.jsp");
					response.sendRedirect(encodedURL);
					user_session.setAttribute("userName", username);
					user_session.setAttribute("userType","student");
				} else if (approvalflag == 1 && "faculty".equals(usertype)) {
					HttpSession user_session = request.getSession(true);
					user_session.setMaxInactiveInterval(30 * 60);
					Cookie userName = new Cookie("userNAME", username);
					response.addCookie(userName);
					String encodedURL = response
							.encodeRedirectURL("faculty_home.jsp");
					response.sendRedirect(encodedURL);
					user_session.setAttribute("userName", username);
					user_session.setAttribute("userType","faculty");
				} else if (approvalflag == 1 && "admin".equals(usertype)) {
					HttpSession user_session = request.getSession(true);
					user_session.setMaxInactiveInterval(30 * 60);
					Cookie userName = new Cookie("userNAME", username);
					response.addCookie(userName);
					String encodedURL = response
							.encodeRedirectURL("admin_home.jsp");
					response.sendRedirect(encodedURL);
					user_session.setAttribute("userName", username);
					user_session.setAttribute("userType","admin");
				}
			}
		} catch (SQLException e) {
			System.out.println("issue with connectivity" + e);
		}
	}
}

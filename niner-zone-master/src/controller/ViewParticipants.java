package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserApprovalDAO;
import model.UserBean;

import com.google.gson.Gson;

import db.DbConnection;

/**
 * Servlet implementation class ViewParticipants
 */
@WebServlet(name = "view_participants", urlPatterns = { "/view_participants" })
public class ViewParticipants extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewParticipants() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserApprovalDAO u = new UserApprovalDAO();
		String userName = (String) request.getSession().getAttribute("userName");
		if(request.getParameter("currAcBdId") != null){
	    	//Method to get the courses for Faculty
		    String boardId = request.getParameter("currAcBdId");
		    
		    List<UserBean> participants = u.getParticipants(userName, boardId);
		   
		    String json2 = new Gson().toJson(participants);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json2);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	
	
}

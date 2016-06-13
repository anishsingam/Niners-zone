package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import model.ForumCommentsDAO;
import model.ForumCommentsDTO;
import model.ForumPostDAO;
import model.ForumPostDTO;

import com.google.gson.Gson;



/**
 * Servlet implementation class ForumPostServlet
 */
@WebServlet("/discussion_forum")
public class ForumPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForumPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int currentAcademicBoardId = (Integer) request.getSession().getAttribute("currentAcademicBoardId");
		String userName = (String) request.getSession().getAttribute("userName");
		if(request.getParameterMap().containsKey("type") && request.getParameter("type").equals("post")){
			ForumPostDAO forumPostDAO = new ForumPostDAO();
			List<ForumPostDTO> postsOfClass = forumPostDAO.getPostList(currentAcademicBoardId);
		    String json2 = new Gson().toJson(postsOfClass);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json2);
		}
		if(request.getParameterMap().containsKey("type") && request.getParameter("type").equals("clickedPost")){
			String postId = request.getParameter("clickedPostId");
			ForumPostDAO forumPostDAO = new ForumPostDAO();
			ForumPostDTO post = forumPostDAO.getPost(Integer.parseInt(postId));
		    String json2 = new Gson().toJson(post);
		    response.setContentType("application/json");
		    response.setCharacterEncoding("UTF-8");
		    response.getWriter().write(json2);
		}
		if(request.getParameterMap().containsKey("type") && request.getParameter("type").equals("comments")){
			String postId = request.getParameter("clickedPostId");
			ForumCommentsDAO forumCommentsDAO = new ForumCommentsDAO();
			List<ForumCommentsDTO> comments = forumCommentsDAO.getCommentsList(Integer.parseInt(postId));
		    String json2 = new Gson().toJson(comments);
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
		ForumPostDAO forumPostDAO = new ForumPostDAO();
		ForumCommentsDAO forumCommentDAO = new ForumCommentsDAO();
		String userName = (String) request.getSession().getAttribute("userName");
		String userType = (String) request.getSession().getAttribute("userType");
		String postId = null;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
	    String strDate = sdf.format(now);
	    
	    String actionToBePerformed = request.getParameter("actionPerformed");
	    
	    //Session To Pass the PostId
	    HttpSession edit_session = request.getSession(true);
	    edit_session.setMaxInactiveInterval(30*60);
	    edit_session.setAttribute("postId",postId );
	    
	    //Couple of if conditions for various actions Add, Delete, Edit
	    if (actionToBePerformed.equals("add")){
			String DisplayType = request.getParameter("UserType");
			String titleForumPost = request.getParameter("postAssignmentTitle");
			String DescriptionForumPost = request.getParameter("descriptionForumPost");
			int sessionBoard = (Integer)request.getSession().getAttribute("currentAcademicBoardId");
			forumPostDAO.addPost(sessionBoard, userType, userName, DisplayType, titleForumPost, DescriptionForumPost, strDate);
	    	response.sendRedirect("discussion_forum.jsp");
	    }
	    
	    else if(actionToBePerformed.equals("toAdd")){
	    	if (userType.equals("faculty")){
	    		response.sendRedirect("discussion_forum_faculty_add.jsp");
	    	}else if(userType.equals("student")){
	    		response.sendRedirect("discussion_forum_add.jsp");
	    	}
	    }
	    
	    else if(actionToBePerformed.equals("toEdit")){
	    	
	    	postId = request.getParameter("postIdForEdit");
	    	ForumPostDTO post = forumPostDAO.getPost(Integer.parseInt(postId));	
	    	request.setAttribute("postId", postId);
	    	request.setAttribute("displayType", post.getDisplay_type());
			request.setAttribute("title", post.getTitle());
			request.setAttribute("contentForum", post.getContent());
		    request.setAttribute("dateOfChange", post.getPostDate());		    
	    	if (userType.equals("student")){
	    		RequestDispatcher rd=request.getRequestDispatcher("discussion_forum_edit.jsp");  
		        rd.forward(request, response);
	    	}
	    	
	    	else if (userType.equals("faculty")){
	    		RequestDispatcher rd=request.getRequestDispatcher("discussion_forum_fac_edit.jsp");  
		        rd.forward(request, response);
	    	}
	    	 
	        return;	
	    }
	    
	    else if (actionToBePerformed.equals("edit")){
	    	
	    	String DisplayType = request.getParameter("UserType");
	    	String titleForumPost = request.getParameter("postAssignmentTitle");
	    	String DescriptionForumPost = request.getParameter("descriptionForumPost");
	    	postId = request.getParameter("postId");
	    	
	    	forumPostDAO.updatePost(Integer.parseInt(postId), DisplayType, titleForumPost, DescriptionForumPost, strDate);
	    	response.sendRedirect("discussion_forum.jsp");
	    	return;	
	    }
	    
	    else if (actionToBePerformed.equals("viewPost")){
	    	postId = request.getParameter("postIdForView");
	    	request.setAttribute("postId", postId);
    	    RequestDispatcher rd=request.getRequestDispatcher("discussion_forum_view.jsp");  
	        rd.forward(request, response); 
	        return;	
	    }
	    
	    else if (actionToBePerformed.equals("addComment")){
	    	postId = request.getParameter("postId");
	    	String commentMade = request.getParameter("addCommentArea");
	    	String displayType = request.getParameter("UserType");
	    	forumCommentDAO.addComments(Integer.parseInt(postId), commentMade, userType, userName, displayType, strDate);
	    	request.setAttribute("postId", postId);		    
		    RequestDispatcher rd=request.getRequestDispatcher("discussion_forum_view.jsp");  
	        rd.forward(request, response); 
	        return;	
	    }
	    
	    
	    else if(actionToBePerformed.equals("delete")){
	    	int postIdForDelete = Integer.parseInt((String) request.getParameter("postIdForDelete"));
	    	forumPostDAO.deletePost(postIdForDelete);
	    	response.sendRedirect("discussion_forum.jsp");
	    }
	}

}

package controller;

//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.websocket.Session;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.FileItem;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.mysql.jdbc.Statement;
import com.oreilly.servlet.MultipartRequest;

import db.DbConnection;
/**
 * Servlet implementation class AmazonUpload
 */
@WebServlet("/AmazonUpload")
public class AmazonUploadServlet extends HttpServlet {

	// private static final Logger LOGGER = Logger.getLogger(Upload2S3.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AmazonUploadServlet() {
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

	// material //academic material
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Need to check the presence of a folder on aws
		String access_key = "AKIAJAQMBKKRDVJVQFQA";
		String secret_key = "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";
		AWSCredentials credentials = new BasicAWSCredentials(access_key,
				secret_key);
		int sessionBoard = (Integer)request.getSession().getAttribute("currentAcademicBoardId");
	
		
		
		
		//Bucket name by appending contents
		String bucketName = "uncccsboard" + Integer.toString(sessionBoard);
		// creating a connection
		AmazonS3 conn = new AmazonS3Client(credentials);
		// Create buckets
		try {
			if (!(conn.doesBucketExist(bucketName))) {
				conn.createBucket(new CreateBucketRequest(bucketName));
				System.out.println("Created");
			} else {
				System.out.println("Bucket exsists");
			}
			// In case of failures in connections
		} catch (AmazonServiceException ase) {
			System.out.println("Caught an AmazonServiceException, which "
					+ "means your request made it "
					+ "to Amazon S3, but was rejected with an error response"
					+ " for some reason.");
			System.out.println("Error Message:    " + ase.getMessage());
			System.out.println("HTTP Status Code: " + ase.getStatusCode());
			System.out.println("AWS Error Code:   " + ase.getErrorCode());
			System.out.println("Error Type:       " + ase.getErrorType());
			System.out.println("Request ID:       " + ase.getRequestId());
		} catch (AmazonClientException ace) {
			System.out.println("Caught an AmazonClientException, which "
					+ "means the client encountered "
					+ "an internal error while trying to "
					+ "communicate with S3, "
					+ "such as not being able to access the network.");
			System.out.println("Error Message: " + ace.getMessage());
		}
		response.setContentType("text/html");
		MultipartRequest m = new MultipartRequest(request,
				"/Users/rohan/Documents/Test/");
		// Get the file Name
		String fileName = m.getOriginalFileName("fileName");
		String titleMaterial = m.getParameter("titleMaterial");
		String descriptionMaterial = m.getParameter("descriptionMaterial");
		String materialType = m.getParameter("materialType");
		String filePath = "/Users/rohan/Documents/Test/" + fileName;
		// Uploading the file to AWS
		File file = new File(filePath);
		try {
			conn.putObject(new PutObjectRequest(bucketName, fileName, file));
			conn.setObjectAcl(bucketName, fileName,
					CannedAccessControlList.PublicRead);
			//Creating the bucket url by appends
			String s = fileName.replaceAll(" ", "+");
			String url = "https://s3.amazonaws.com/"+bucketName+"/"+s;
			//DB Statement to update the material and academic material entities
			String sql2 ="";
			DbConnection dbconn = new DbConnection();
			String sql1 = "INSERT INTO material(academic_board_id,title, description,link,type,file_key) values ('"+ sessionBoard+ "','" +titleMaterial +"','" +descriptionMaterial+"','" +url+"','"+materialType+ "','"+fileName+"');";
			if (materialType.equals("academic")){
				sql2 = "INSERT INTO academic_material(material_id) SELECT material_id from material WHERE link = '" +url+"';";
			}else {
				sql2 = "INSERT INTO assignment_material(material_id) SELECT material_id from material WHERE link = '" +url+"';";
			}
			Statement stmt = (Statement) dbconn.DbConnectionForStatement();
			stmt.execute(sql1);
			stmt.execute(sql2);
			
		} catch (Exception e) {
		} finally {
			try {

				File fileDelete = new File(filePath);
				if (fileDelete.delete()) {
					System.out.println(file.getName() + " is deleted!");
				} else {
					System.out.println("Delete operation is failed.");
				}
			} catch (Exception e) {
				e.printStackTrace();

			}
		}
		response.sendRedirect("academic_board.jsp");
	}
}

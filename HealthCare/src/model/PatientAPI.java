package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * Servlet implementation class PatientAPI
 */
@WebServlet("/PatientAPI")
public class PatientAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Patient patientObj = new Patient();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String output = patientObj.insertUser(request.getParameter("fname"),
	 			request.getParameter("lname"),
				request.getParameter("address"),
				request.getParameter("email"),
				request.getParameter("phone"),
				request.getParameter("nic"),
				request.getParameter("dob"),
				request.getParameter("username"),
				request.getParameter("password"));
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		
		String output = patientObj.updateUser(paras.get("hiddenSave").toString(),
					 			paras.get("fname").toString(),
					 			paras.get("lname").toString(),
					 			paras.get("address").toString(),
					 			paras.get("email").toString(),
					 			paras.get("phone").toString(),
					 			paras.get("nic").toString(),
					 			paras.get("dob").toString(),
					 			paras.get("username").toString(),
					 			paras.get("password").toString());
		
		response.getWriter().write(output);
	}


	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Map paras = getParasMap(request);
		
		String output = patientObj.deletePatient(paras.get("pid").toString());
		
		response.getWriter().write(output);
	}
	
	 private Map getParasMap(HttpServletRequest request) {
			// TODO Auto-generated method stub
			Map<String, String> map = new HashMap<String, String>();
			
			try {
				Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
				String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() :"";
				scanner.close();
				
				String[] params = queryString.split("&");
				for (String param : params)
				{
					String[] p = param.split("=");
					map.put(p[0], p[1]);
				}
			}
			catch (Exception e)
			{}
			return map;
		}

}

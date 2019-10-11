package jbcodeforce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Example of servlet life cycle
 * @author jerome boyer
 *
 */
@WebServlet(urlPatterns="/servlet")
public class HelloServlet extends HttpServlet {
	
	private String message;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		System.out.println("init servlet called");
		this.message = "Bonjour! How are you today?\n";
	}
	
	/**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.getWriter().append(message);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        doGet(request, response);
    }
    
    public void destroy() {
        // do nothing.
    	System.out.println("destroy servlet called");
     }
}

package ExamplePackage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
		
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			UserBean user = new UserBean();
			user.setUserName(request.getParameter("strUsername"));
			user.setPassword(request.getParameter("strPwd"));
		
			user = UserDAO.login(user);
			
			if(user.isValid()) {
				HttpSession session = request.getSession(true);
				session.setAttribute("currentSessionUser", user);
				response.sendRedirect("userLogged.jsp");
			}
			else {
				response.sendRedirect("invalidLogin.jsp");
			}
		}
		catch (Throwable e) {
			System.out.println(e);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}

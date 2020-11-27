package servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.HibernateSessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;

import common.CommonResponse;




public class LoginServlet extends HttpServlet {

	public LoginServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");		

		BufferedReader read = request.getReader();
		String userId = (String) request.getParameter("userId");
		String userPsd = (String) request.getParameter("userPsd");
		
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = read.readLine()) != null) {
            sb.append(line);
        }

        String req = sb.toString();

        System.out.println("req------------------------------"+req);
        
        String sql = String.format("SELECT userPsd FROM User WHERE userId='%s'", userId);
        System.out.println(sql);

        
        CommonResponse res = new CommonResponse();
        Session session = null;
        session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(sql);
	    if (query.uniqueResult().equals(userPsd)) {
	        res.setResult("0", "µÇÂ½³É¹¦");
	        request.setAttribute("result", "success");
	        res.getProperty().put("userId",userId);
	    } else {
	        res.setResult("100", "µÇÂ¼Ê§°Ü£¬µÇÂ¼ÃÜÂë´íÎó");
	        request.setAttribute("result", "failure");
	    }
		
        String resStr = JSONObject.fromObject(res).toString();
        System.out.println(resStr);
        response.getWriter().append(resStr).flush();

	}

}

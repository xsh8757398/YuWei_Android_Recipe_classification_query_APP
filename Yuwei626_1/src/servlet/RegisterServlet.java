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

import user.User;
import common.CommonResponse;
import dao.UserDao;
import dao.UserDaoImpl;




public class RegisterServlet extends HttpServlet {

	public RegisterServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

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
  
        String sql_1 = String.format("SELECT userId FROM User WHERE userId='%s'", userId);
        String sql_2 = String.format("INSERT INTO User (userId,userPsd) values ('%s','%s')", userId,userPsd);
        System.out.println(sql_1);
        System.out.println(sql_2);

        CommonResponse res = new CommonResponse();
        Session session = null;
        session = HibernateSessionFactory.getSession();
        Query query_1 = session.createQuery(sql_1);
        System.out.println("------------------------------" + query_1);

        
        System.out.println("====================================");
        
        if(query_1.uniqueResult()==null){                  //查询之后不存在   	
        	UserDao userDao = new UserDaoImpl();

	        User user2 = new User();
	        user2.setUserId(userId);
	        user2.setUserPsd(userPsd);
        	
        	userDao.save(user2);
		    res.setResult("0", "注册成功");
		    request.setAttribute("result", "success");
		    res.getProperty().put("userId",userId);
		    
        }else{     	
        	res.setResult("100", "账号存在");
	        request.setAttribute("result", "failure");
        }


        String resStr = JSONObject.fromObject(res).toString();
        System.out.println(resStr);
        response.getWriter().append(resStr).flush();
   

	}

}

package Controller;

import dao.PersistenceDAO;
import dao.UserDAO;
import org.hibernate.SessionFactory;
import vo.UserVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "HomeController",urlPatterns = {"/home"})
public class HomeController extends HttpServlet {
    private UserVO User(int size){
        UserVO userVO = new UserVO();
        StringBuilder sb = new StringBuilder();
        Random random = new Random(26);
        while (size-->0){
            sb.append((char)random.nextInt() + 'a');
        }
        return new UserVO(sb.toString());
    }
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDAO userDAO = new UserDAO();
        for(int i = 0;i<10;i++) userDAO.insert(User(45));
        response.getWriter().println(userDAO.getList());
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcess(request,response);
    }
}

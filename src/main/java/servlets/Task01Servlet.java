package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;


/**
 * @author Alex Bocharoc
 *         <p>
 *         код Albert Code project
 *         <p>
 *         Описание
 */
public class Task01Servlet extends HttpServlet {
    private final AccountService accountService;

    public Task01Servlet(AccountService accountService) {
        this.accountService = accountService;
    }

    //get logged user profile
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        String sessionId = request.getSession().getId();
        UserProfile profile = accountService.getUserBySessionId(sessionId)
        //Gson gson = new Gson();
        //String json = gson.toJson("Helol World");
        //response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Wellcom to Task01Servlet");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    //sign in
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        String AlbertCode = request.getParameter("text");
        ScriptEngineManager m = new ScriptEngineManager();
        //Sets up Nashorn JavaScript Engine
        ScriptEngine e = m.getEngineByExtension("js");
        //Nashorn JavaScript syntax
        e.eval("print ('Hello, ')");
        Gson gson = new Gson();
        String json = gson.toJson("hi");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(json);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    //sign out
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String sessionId = request.getSession().getId();
        UserProfile profile = accountService.getUserBySessionId(sessionId);
        if (profile == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            accountService.deleteSession(sessionId);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Goodbye!");
            response.setStatus(HttpServletResponse.SC_OK);
        }

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.andrej.gadgets.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import rs.adnrej.gadgets.service.UserService;
import rs.andrej.gadgets.data.User;
import rs.andrej.gadgets.exceptions.ShopException;




@WebServlet(name = "UserServlet", urlPatterns = {"/UserServlet"})
public class UserServlet extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ShopException {
        
        String action = request.getParameter("action");
        Actions actions = Actions.getForAction(action);
        switch (actions) {
            case ADD: {
                
                String name = request.getParameter("name");
                String surrname = request.getParameter("surrname");
                
                String username = request.getParameter("username");
                String password=request.getParameter("password");
                
                User user=new User(name, surrname, username, password);
                
                UserService.getInstance().addnewUser(user);
                
                request.setAttribute("message", "Clan "+user.getName()+" "+user.getSurrname()+" "+user.getUsername() + "uspesno dodat.");
                
                break;
            }
            case EDIT: {
                String name = request.getParameter("name");
                String surrname = request.getParameter("surrname");
                String username = request.getParameter("username");
                Integer idUser = Integer.parseInt(request.getParameter("user_id"));
                User user=new User(idUser, name, surrname, username);
                
                UserService.getInstance().updateUser(user);
                
                request.setAttribute("message", "Uspešno ste izmenili podatke o clanu "+user.getName()+" "+user.getSurrname()+" "+user.getUsername()+".");
                
                break;
            }
            case DELETE: {
                int idUser = Integer.parseInt(request.getParameter("user_id"));
                User user=new User(idUser);
                
                UserService.getInstance().deleteUser(action);
                
                request.setAttribute("message", "Uspešno ste izbrisali clana.");
                
                break;
            }
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ShopException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ShopException ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

}

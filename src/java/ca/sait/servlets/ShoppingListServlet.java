package ca.sait.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marco
 */
public class ShoppingListServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = (String) request.getSession().getAttribute("name");
        
        String action = request.getParameter("action");
        System.out.println("Entre 0.0");
        if (action != null && action.equals("logout")) {
            System.out.println("Entre 0");
            request.getSession().invalidate();
            System.out.println("Entre 1");
            //response.sendRedirect("register");
            //return;
            this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
        
        if ( name != null ) {
            this.getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if (action.equals("register")) {
            String name = request.getParameter("name");

            if (name != null) {
                request.getSession().setAttribute("name",name);
                ArrayList<String> items = new ArrayList();
                request.getSession().setAttribute("items",items);
            }
            //this.getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        
        } else if (action.equals("add")) {
            String item = request.getParameter("item");
            //get items array list from session
            ArrayList<String> items = (ArrayList<String>) request.getSession().getAttribute("items");
            //add item into the local array list
            items.add(item);
            //send it back to session
            request.getSession().setAttribute("items", items);
            
            
        } else if (action.equals("delete")) {
            String item = request.getParameter("item");
            //get items array list from session
            ArrayList<String> items = (ArrayList<String>) request.getSession().getAttribute("items");
            //remove item from the local array list
            items.remove(item);
            //send it back to session
            request.getSession().setAttribute("items", items);
            
        } 
        this.getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
    }

}

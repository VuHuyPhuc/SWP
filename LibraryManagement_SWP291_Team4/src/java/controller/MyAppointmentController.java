// This is a personal academic project. Dear PVS-Studio, please check it.
// PVS-Studio Static Code Analyzer for C, C++, C#, and Java: https://pvs-studio.com
package controller;

import dao.AppointmentDAO;
import dao.impl.AppointmentDAOImpl;
import dao.impl.CategoryDAOImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import entity.Appointment;
import entity.Category;
import entity.User;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class MyAppointmentController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) { //case: cannot get user when end of login session
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            // user logined and session did not end
            try {
                userId = user.getId();

                AppointmentDAO appointmentDAO = new AppointmentDAOImpl();

                //get appoinment list for user and book by userId and status
                List<Appointment> lsAppointment = appointmentDAO.getAllAppointmentByUserIdAndStatus(userId, "pending");

                request.setAttribute("lsAppointment", lsAppointment);
            } catch (Exception e) {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }
        CategoryDAOImpl c = new CategoryDAOImpl();
        List<Category> categorylist = c.getAllCategory();
        request.setAttribute("categorylist", categorylist);

        request.getRequestDispatcher("myAppointment.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        String sort = request.getParameter("sort");
        if (sort == null) { //did not sort
            String action = request.getParameter("action");
            if ((action!=null) && (action.equalsIgnoreCase("delete"))) { // case update appointment status to rejected
                try {
                    int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
                    
                    AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
                    
                    Appointment appointment = appointmentDAO.get(appointmentId);
                    appointment.setStatus("rejected");
                    
                    appointmentDAO.update(appointment);
                } catch (NumberFormatException e) {
                    Logger.getLogger(AppointmentListController.class.getName()).log(Level.SEVERE, null, e);
                }
            }
            processRequest(request, response);
        }
        int userId;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) { //case: cannot get user when end of login session
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            // user logined and session did not end
            try {
                userId = user.getId();

                AppointmentDAO appointmentDAO = new AppointmentDAOImpl();

                //get appoinment list for user and book by userId and status
                List<Appointment> lsAppointment = appointmentDAO.getAllAppointmentByUserIdAndStatus(userId, "pending");
                if (sort.equalsIgnoreCase("lastest")) {
                    Collections.sort(lsAppointment);
                } else {
                    Collections.sort(lsAppointment, Collections.reverseOrder());
                }

                request.setAttribute("lsAppointment", lsAppointment);
            } catch (Exception e) {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }
        CategoryDAOImpl c = new CategoryDAOImpl();
        List<Category> categorylist = c.getAllCategory();
        request.setAttribute("categorylist", categorylist);

        request.getRequestDispatcher("myAppointment.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] lsCategoryName = request.getParameterValues("category"); //List name of category use to filter appointment list

        CategoryDAOImpl c = new CategoryDAOImpl();
        List<Category> categorylist = c.getAllCategory();
        request.setAttribute("categorylist", categorylist);

        int userId;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) { //case: cannot get user when end of login session
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            // user logined and session did not end
            try {
                userId = user.getId();

                AppointmentDAO appointmentDAO = new AppointmentDAOImpl();

                //get appoinment list for user and book by userId and status
                List<Appointment> lsAppointment = appointmentDAO.getAllAppointmentByUserIdAndStatus(userId, "pending");

                List<Appointment> filteredListAppointmentByCategory = filterListAppointmentByCategory(lsCategoryName, lsAppointment);
                request.setAttribute("lsAppointment", filteredListAppointmentByCategory);
            } catch (Exception e) {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }
        request.getRequestDispatcher("myAppointment.jsp").forward(request, response);
    }

    private List<Appointment> filterListAppointmentByCategory(String[] lsCategoryName, List<Appointment> lsAppointment) {
        List<Appointment> listFilterAppointment = new ArrayList<>();

        if (lsCategoryName[0].equalsIgnoreCase("allBook")) { // case user get all type of category
            return lsAppointment;
        }
        //loop all lsAppointment to find appointment have category match with category in lsCategoryName
        for (Appointment appointment : lsAppointment) {
            for (String categoryName : lsCategoryName) {
                //get categoryId to filter in lsCategoryName
                if (appointment.getBook().getCategory_id().getName().equalsIgnoreCase(categoryName)) {
                    //add appointment have category name match with category name in lsCategoryName
                    listFilterAppointment.add(appointment);
                }
            }
        }
//        }

        return listFilterAppointment;
    }

}

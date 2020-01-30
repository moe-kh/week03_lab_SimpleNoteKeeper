/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import domain.Note;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lpeters
 */
public class NoteServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("GET Request:");
        String edit = request.getParameter("edit");
               String path = getServletContext().getRealPath("/WEB-INF/note.txt");
               String title=null;
             String content=null;
          //     Note n=new Note(title,content);
               
// to read files
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        for(int i=0;i<2;i++){
            if(i==0){
             title=br.readLine();
             
            }else{
            content=br.readLine();
            }
        }System.out.println("this is title"+title +"content is "+ content);
        br.close();
        Note n=new Note(title,content);
        n.setContent(content);
        n.setTitle(title);
        request.setAttribute("n", n);
        if (edit != null) {
            System.out.println("Edit Mode");
            getServletContext().getRequestDispatcher("/WEB-INF/editnote.jsp").forward(request,response);
        } else {
            System.out.println("View Mode");
            getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request,response);
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
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("POST Request:");
        //String edit = request.getParameter("edit");
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");
        String skipper= title+"\n"+contents;
        //if (edit != null) { System.out.println("Edit is set!"); }
        //System.out.println("Title: "+title);
        //System.out.println("Contents: "+contents);
         String path = getServletContext().getRealPath("/WEB-INF/note.txt");
        // to write to a file
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(path, false)));
        
       // pw.write(title);
        pw.write(skipper);
       
        pw.close();
        Note note= new Note(title,contents);
       
        request.setAttribute("note", note);
        getServletContext().getRequestDispatcher("/WEB-INF/viewnote.jsp").forward(request,response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

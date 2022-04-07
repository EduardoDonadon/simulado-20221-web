package br.com.mariojp.condominio.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import br.com.mariojp.condominio.model.Usuario;
import br.com.mariojp.condominio.dao.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class LoginController extends HttpServlet{
	
	private UsuarioDAO dao = new UsuarioDAO();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		req.getRequestDispatcher("/login.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");

		Usuario user = dao.findByLogin(login);
		
		if (user != null) {
			if(user.getSenha().equals(senha)) {
				resp.sendRedirect("/lista");
			} else {
				resp.sendRedirect("/login.jsp");
			}
		} else {
			resp.sendRedirect("/login.jsp");
		}
	}

}

package br.com.mariojp.condominio.controllers;

import java.io.IOException;

import br.com.mariojp.condominio.dao.UsuarioDAO;
import br.com.mariojp.condominio.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/novo")
public class CreateUserController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private UsuarioDAO dao = new UsuarioDAO();	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		req.getRequestDispatcher("/novo.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");
		
		if(login.equals("") || senha.equals("")) {
			resp.sendRedirect("/novo");
		} else {			
			Usuario user = new Usuario(login, senha);
			
			dao.save(user);
			
			resp.sendRedirect("/login.jsp");
		}
	}

}

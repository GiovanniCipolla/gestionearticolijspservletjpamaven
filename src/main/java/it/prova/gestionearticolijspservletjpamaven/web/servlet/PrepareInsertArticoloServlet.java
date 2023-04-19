package it.prova.gestionearticolijspservletjpamaven.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestionearticolijspservletjpamaven.model.Articolo;

@WebServlet("/PrepareInsertArticoloServlet")
public class PrepareInsertArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// metto un bean 'vuoto' in request perché per la pagina risulta necessario
		request.setAttribute("insert_articolo_attr", new Articolo());
		request.getRequestDispatcher("/articolo/insert.jsp").forward(request, response);

	}

}

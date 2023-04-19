package it.prova.gestionearticolijspservletjpamaven.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.gestionearticolijspservletjpamaven.service.ArticoloService;
import it.prova.gestionearticolijspservletjpamaven.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteDeleteArticoloServlet
 */
@WebServlet("/ExecuteDeleteArticoloServlet")
public class ExecuteDeleteArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ------------------BINDING
		String parametroId = request.getParameter("idDaRimuovere");
		Long idDaRimuovere = Long.parseLong(parametroId);

		// ------------------VALIDAZIONE
		if (!NumberUtils.isCreatable(parametroId)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		// ------------------BUSINESS
		try {
			ArticoloService articoloServiceInstance = MyServiceFactory.getArticoloServiceInstance();
			articoloServiceInstance.rimuovi(idDaRimuovere);
			request.setAttribute("listaArticoliAttribute", articoloServiceInstance.listAll());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		// ------------------FORWARD
		request.getRequestDispatcher("/articolo/results.jsp").forward(request, response);

	}

}

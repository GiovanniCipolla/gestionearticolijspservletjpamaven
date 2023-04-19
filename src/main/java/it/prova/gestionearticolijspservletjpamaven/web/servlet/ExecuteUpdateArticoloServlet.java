package it.prova.gestionearticolijspservletjpamaven.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.gestionearticolijspservletjpamaven.model.Articolo;
import it.prova.gestionearticolijspservletjpamaven.service.MyServiceFactory;
import it.prova.gestionearticolijspservletjpamaven.utility.UtilityArticoloForm;

/**
 * Servlet implementation class ExecuteUpdateArticoloServlet
 */
@WebServlet("/ExecuteUpdateArticoloServlet")
public class ExecuteUpdateArticoloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// ------------------BINDING
		String idDaPagina = request.getParameter("id");
		String codiceDaPagina = request.getParameter("codice");
		String descrizioneDaPagina = request.getParameter("descrizione");
		String prezzoDaPaginaString = request.getParameter("prezzo");
		String dataDiArrivoDaPagina = request.getParameter("dataArrivo");

		Articolo articoloInstance = UtilityArticoloForm.createArticoloFromParamsWithId(idDaPagina, codiceDaPagina,
				descrizioneDaPagina, prezzoDaPaginaString, dataDiArrivoDaPagina);

		// ------------------VALIDAZIONE
		if (!UtilityArticoloForm.validateArticoloBean(articoloInstance)) {
			request.setAttribute("articoloDaAggiornare", articoloInstance);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/articolo/update.jsp").forward(request, response);
			return;
		}

		// ------------------BUSINESS
		try {
			MyServiceFactory.getArticoloServiceInstance().aggiorna(articoloInstance);
			request.setAttribute("listaArticoliAttribute", MyServiceFactory.getArticoloServiceInstance().listAll());
		} catch (Exception e) {
			request.setAttribute("articoloDaAggiornare", articoloInstance);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/articolo/update.jsp").forward(request, response);
			return;
		}

		// ------------------FORWARD
		request.getRequestDispatcher("/articolo/results.jsp").forward(request, response);

	}

}

package it.prova.gestionearticolijspservletjpamaven.service;

import java.util.List;

import it.prova.gestionearticolijspservletjpamaven.dao.ArticoloDAO;
import it.prova.gestionearticolijspservletjpamaven.model.Articolo;

public interface ArticoloService {

	// questo mi serve per injection
	public void setArticoloDao(ArticoloDAO articoloDao);

	public List<Articolo> listAll() throws Exception;

	public Articolo caricaSingoloElemento(Long idInput) throws Exception;

	public void aggiorna(Articolo input) throws Exception;

	public void inserisciNuovo(Articolo input) throws Exception;

	public void rimuovi(Long idArticoloToRemove) throws Exception;

	public List<Articolo> findByExample(Articolo input) throws Exception;

}

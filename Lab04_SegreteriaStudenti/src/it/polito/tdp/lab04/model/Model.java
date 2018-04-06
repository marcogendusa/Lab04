package it.polito.tdp.lab04.model;

import java.util.*;
import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {

	private List<Corso> corsi;
	private List<Studente> studenti;
	private CorsoDAO corsoDAO;
	private StudenteDAO studenteDAO;

	public Model() {
		corsoDAO = new CorsoDAO();
		studenteDAO = new StudenteDAO();
	}
	
	public List<Corso> getTuttiICorsi() {
		List<Corso> l = corsoDAO.getTuttiICorsi();
		return l;
	}


	public Studente getStudenteByMatricola(int matricola) {

		Studente studente = studenteDAO.getStudente(matricola);
		// se è null lo ritorno già nella classe DAO	

		return studente;
	}

	public List<Studente> getStudentiFromCorso(String codins) throws Exception {

		this.corsi = corsoDAO.getTuttiICorsi();

		for(Corso c: this.corsi) {
			if(c.getCodins().equals(codins))
				return c.getListaStudenti();
		}

		throw new Exception("Nessun corso selezionato");
	}
	
	public List<Corso> getCorsiFromMatricola(int matricola) {
		
		Studente studente = studenteDAO.getStudente(matricola);
		
		this.studenti = studenteDAO.getTuttiGliStudenti();
		
		for(Studente s: this.studenti) {
			if(matricola == s.getMatricola())
				return s.getListaCorsi();
		}

		return null;
		
	}



}

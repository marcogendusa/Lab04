package it.polito.tdp.lab04.model;

import java.util.*;

public class Studente {
	
	private int matricola;
	private String nome, cognome, CDS;
	
	private List<Corso> listaCorsi = new ArrayList<Corso>();
	
	public Studente(int matricola, String nome, String cognome, String CDS) {
		this.matricola = matricola;
		this.nome = nome;
		this.cognome = cognome;
		this.CDS = CDS;
	}

	public int getMatricola() {
		return matricola;
	}

	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getCDS() {
		return CDS;
	}

	public void setCDS(String CDS) {
		this.CDS = CDS;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + matricola;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Studente other = (Studente) obj;
		if (matricola != other.matricola)
			return false;
		return true;
	}

	public String toString() {
		return "Studente [matricola=" + matricola + ", nome=" + nome + ", cognome=" + cognome + ", CDS=" + CDS
				+ "]";
	}
	
	public void aggiungiCorso(Corso c) {
		if(!this.listaCorsi.contains(c))
			this.listaCorsi.add(c);
	}

	public List<Corso> getListaCorsi() {
		return listaCorsi;
	}
	
	
}

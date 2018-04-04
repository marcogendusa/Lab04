package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public List<Studente> getTuttiGliStudenti() {

		final String sql = "SELECT * FROM studente";

		List<Studente> studenti = new LinkedList<Studente>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				int matricola = rs.getInt("matricola");
				String nome = rs.getString("nomr");
				String cognome = rs.getString("cognome");
				String CDS = rs.getString("CDS");
				
				Studente s = new Studente(matricola, nome, cognome, CDS);
				studenti.add(s);
			}
			
			conn.close();

			return studenti;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	/*
	 * Data la matricola ottengo studente
	 */
	public Studente getStudente(int matricola) {
		
		final String sql = "SELECT * FROM studente WHERE matricola = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();

			if (rs.next()) { // uso if e non while perchè lo studente con quella matricola è (evnetualmente) unico 
				Studente s = new Studente(matricola, rs.getString("nome"), rs.getString("cognome"), rs.getString("cds"));
				conn.close();
				return s;
			}

			// se non trovo lo studente in quanto non esiste la matricola, la query rs non è eseguita (non trovo la matricola)
			conn.close();
			return null;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	
	
	/*
	 * Data la matricola controllo se lo studente è iscritto a corsi. Se sì: ritorno i corsi. Se no: messaggio errore
	 * Non so se è giusto ma baro come ha barato in CorsoDAO
	 */
	
	public void getCorsiStudente(Studente studente) throws Exception {
		final String sql = "SELECT *" +
				"FROM corso AS c, iscrizione AS i" +
				"WHERE c.codins=i.codins AND i.matricola='?'";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, studente.getMatricola());

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Corso c = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				studente.aggiungiCorso(c);
			}
			
			if(studente.getListaCorsi().size()==0) {
				throw new Exception("Studente non iscritto ad alcun corso");
			}

			conn.close();

		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

}

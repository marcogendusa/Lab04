package it.polito.tdp.lab04.DAO;

public class TestDB {

	public static void main(String[] args) {
		
		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		StudenteDAO sdao = new StudenteDAO();
		
		//cdao.getTuttiICorsi(); //ok
		
		//System.out.println(sdao.getStudente(146101).toString()); //ok
		
		
		try {
			sdao.getCorsiStudente(sdao.getStudente(146101));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}

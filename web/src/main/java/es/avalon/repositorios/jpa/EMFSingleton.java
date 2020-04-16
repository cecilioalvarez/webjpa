package es.avalon.repositorios.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMFSingleton {

	private static EntityManagerFactory emf=null;
	
	public static EntityManagerFactory getInstance() {

		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("UnidadBiblioteca");
		}
		return  emf;
	}
}

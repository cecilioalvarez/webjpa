package es.avalon.repositorios.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import es.avalon.dominio.Libro;
import es.avalon.repositorios.LibroRepository;

public class LibroRepositoryJPA implements LibroRepository {

	EntityManagerFactory emf;
	EntityManager em;

	public LibroRepositoryJPA() {

		emf = EMFSingleton.getInstance();
		em=emf.createEntityManager();
	}
	
	public List<Libro> buscarTodos() {

		TypedQuery<Libro> consulta = em.createQuery("select l from Libro l", Libro.class);
		return consulta.getResultList();		
	}

	public Libro buscarPorISBN(String isbn) {
		 return em.find(Libro.class, isbn);
	}
	
	public Libro buscarPorTitulo(String titulo) {

		TypedQuery<Libro> consulta = em.createQuery("select l from Libro l where l.titulo=:titulo", Libro.class);
		consulta.setParameter("titulo", titulo);
		return consulta.getSingleResult();
	}
	
	public void insertar(Libro libro) {
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.persist(libro);
		et.commit();
	}
	
	public void salvar(Libro libro) {
		
		EntityTransaction et = em.getTransaction();
		et.begin();
		em.merge(libro);
		et.commit();

	}
	
	public void borrar(Libro libro) {

		EntityTransaction et = em.getTransaction();
		et.begin();
		Libro libroBorrar = em.merge(libro);
		em.remove(libroBorrar);
		et.commit();
	}

	@Override
	public List<Libro> OrdenarPorTitulo() {
		
		TypedQuery<Libro> consulta = em.createQuery("select l from Libro l order by l.titulo", Libro.class);
		return consulta.getResultList();
	}
	@Override
	public List<Libro> OrdenarPorCategoria() {
		
		TypedQuery<Libro> consulta = em.createQuery("select l from Libro l order by l.categoria", Libro.class);
		return consulta.getResultList();
	}

	@Override
	public List<Libro> OrdenarPorAutor() {
		TypedQuery<Libro> consulta = em.createQuery("select l from Libro l order by l.autor", Libro.class);
		return consulta.getResultList();
	}

}
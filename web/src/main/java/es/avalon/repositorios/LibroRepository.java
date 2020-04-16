package es.avalon.repositorios;

import java.util.List;

import es.avalon.dominio.Libro;

public interface LibroRepository {

	List<Libro> buscarTodos();

	Libro buscarPorISBN(String isbn);

	Libro buscarPorTitulo(String titulo);
	
	List<Libro> OrdenarPorTitulo();
	
	List<Libro> OrdenarPorCategoria();
	
	List<Libro> OrdenarPorAutor();

	void insertar(Libro libro);

	void salvar(Libro libro);

	void borrar(Libro libro);

}
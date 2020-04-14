package es.avalon.servicios;

import java.util.List;

import es.avalon.dominio.Categoria;
import es.avalon.dominio.Libro;
import es.avalon.repositorios.CategoriaRepository;
import es.avalon.repositorios.LibroRepository;
import es.avalon.repositorios.jpa.CategoriaRepositoryJPA;
import es.avalon.repositorios.jpa.LibroRepositoryJPA;

public class ServicioLibros {
	
	private LibroRepository repositorioLibro = new LibroRepositoryJPA();
	private CategoriaRepository repositorioCategoria = new CategoriaRepositoryJPA();
	
	
	
	public Categoria buscarCategoriaPorNombre(String nombre) {
		return repositorioCategoria.buscarPorNombre(nombre);
	}
	public List<Libro> buscarLibrosTodos() {
		return repositorioLibro.buscarTodos();
	}
	public Libro buscarLibrosPorISBN(String isbn) {
		return repositorioLibro.buscarPorISBN(isbn);
	}
	public Libro buscarLibrosPorTitulo(String titulo) {
		return repositorioLibro.buscarPorTitulo(titulo);
	}
	public void insertarLibro(Libro libro) {
		repositorioLibro.insertar(libro);
	}
	public void salvarLibro(Libro libro) {
		repositorioLibro.salvar(libro);
	}
	public void borrarLibro(Libro libro) {
		repositorioLibro.borrar(libro);
	}
	public List<Libro> ordenarLibrosPorTitulo() {
		return repositorioLibro.ordenarPorTitulo();
	}
	public List<Libro> ordenarLibrosPorAutor() {
		return repositorioLibro.ordenarPorAutor();
	}
	
	
	

}

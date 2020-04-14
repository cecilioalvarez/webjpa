package es.avalon.web.controladores;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.avalon.dominio.Categoria;
import es.avalon.dominio.Libro;
import es.avalon.repositorios.CategoriaRepository;
import es.avalon.repositorios.LibroRepository;
import es.avalon.repositorios.jdbc.LibroRepositoryJDBC;
import es.avalon.repositorios.jpa.CategoriaRepositoryJPA;
import es.avalon.repositorios.jpa.LibroRepositoryJPA;

/**
 * aportan informacion clave al servidor
 */
// http://localhost:8080/avalon2020web1/ServletLibros
@WebServlet("/ServletLibros")
public class ServletLibros extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher despachador = null;
		String accion = request.getParameter("accion");
		LibroRepository repositorio = new LibroRepositoryJPA();
		CategoriaRepository repositorioCategoria = new CategoriaRepositoryJPA();

		// hay algun tipo de accion
		if (accion != null) {

			if (accion.equals("formularioInsertar")) {
				despachador = request.getRequestDispatcher("libros2/formularioInsertar.jsp");
			}

			// Ordenar
			// Accion=ordenar
			else if (accion.equals("ordenar")) {

				String ordenarPor = request.getParameter("ordenarPor");
				if (ordenarPor.contentEquals("titulo")) {

					List<Libro> listaLibros = repositorio.buscarTodosOrdenadosPorTitulo();		
					request.setAttribute("listaLibros", listaLibros);
				} else {
					List<Libro> listaLibros = repositorio.buscarTodosOrdenadosPorAutor();
					request.setAttribute("listaLibros", listaLibros);

				}
				despachador = request.getRequestDispatcher("libros2/listaLibros.jsp");

			}

			// Borrar
			// Accion=borrar
			else if (accion.equals("borrar")) {
				// Recepcionar
				String isbn = request.getParameter("isbn");

				// Borrar
				Libro milibro = new Libro(isbn);
				repositorio.borrar(milibro);

				// Cargar nuevo listado
				List<Libro> listaLibros = repositorio.buscarTodos();
				request.setAttribute("listaLibros", listaLibros);
				// Redirigir
				despachador = request.getRequestDispatcher("libros2/listaLibros.jsp");
			}

			// Ver detalle
			// Accion=detalle
			else if (accion.contentEquals("detalle")) {
				String isbn = request.getParameter("isbn");
				Libro libro = repositorio.buscarPorISBN(isbn);
				request.setAttribute("libro", libro);
				despachador = request.getRequestDispatcher("libros2/detalle.jsp");

			}

			// Editar
			// Accion=editar
			else if (accion.contentEquals("editar")) {
				String isbn = request.getParameter("isbn");
				Libro libro = repositorio.buscarPorISBN(isbn);
				request.setAttribute("libro", libro);
				despachador = request.getRequestDispatcher("libros2/editar.jsp");

			}
			// Salvar
			// Accion=salvar
			else if (accion.equals("salvar")) {
				String isbn = request.getParameter("isbn");
				String titulo = request.getParameter("titulo");
				String autor = request.getParameter("autor");
				int precio = Integer.parseInt(request.getParameter("precio"));
				String categoria = request.getParameter("categoria");

				// Salvar
				Libro milibro = new Libro(isbn, titulo, autor, precio);
				Categoria objetoCategoria = repositorioCategoria.buscarPorNombre(categoria);
				milibro.setCategoria(objetoCategoria);
				// Salvo el libro
				repositorio.salvar(milibro);

				// Cargar nuevo listado
				List<Libro> listaLibros = repositorio.buscarTodos();
				request.setAttribute("listaLibros", listaLibros);
				// Redirigir
				despachador = request.getRequestDispatcher("libros2/listaLibros.jsp");
			}

			// Accion=insertar este else es lo mismo que -->//else if
			// (accion.equals("formularioInsertar")) {
			else {
				// Recepcionar
				String isbn = request.getParameter("isbn");
				String titulo = request.getParameter("titulo");
				String autor = request.getParameter("autor");
				int precio = Integer.parseInt(request.getParameter("precio"));
				String categoria = request.getParameter("categoria");

				// Insertar
				Libro milibro = new Libro(isbn, titulo, autor, precio);
				// Uso el nuevo repositorio para buscar una categoria
				Categoria micategoria = repositorioCategoria.buscarPorNombre(categoria);
				// Asigno al libro la categoria que necesita
				milibro.setCategoria(micategoria);
				// Salvo el libro
				repositorio.insertar(milibro);

				// Cargar nuevo listado
				List<Libro> listaLibros = repositorio.buscarTodos();
				request.setAttribute("listaLibros", listaLibros);
				// Redirigir
				despachador = request.getRequestDispatcher("libros2/listaLibros.jsp");
			}

		} else {

			List<Libro> listaLibros = new ArrayList<Libro>();

			listaLibros = repositorio.buscarTodos();

			despachador = request.getRequestDispatcher("libros2/listaLibros.jsp");
			request.setAttribute("listaLibros", listaLibros);
		}

		// redirige al jsp
		despachador.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

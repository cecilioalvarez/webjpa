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
import es.avalon.repositorios.jpa.CategoriaRepositoryJPA;
import es.avalon.repositorios.jpa.LibroRepositoryJPA;

@WebServlet("/ServletLibros")
public class ServletLibros extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher despachador = null;
		String accion = request.getParameter("accion");
		LibroRepository repositorio = new LibroRepositoryJPA();
		CategoriaRepository repositorioCategoria = new CategoriaRepositoryJPA();

		if (accion != null) {

			if (accion.equals("formularioInsertar")) {

				despachador = request.getRequestDispatcher("libros2/formularioInsertar.jsp");

			} else if (accion.equals("insertar")) {

				String isbn = request.getParameter("isbn");
				String titulo = request.getParameter("titulo");
				String autor = request.getParameter("autor");
				int precio = Integer.parseInt(request.getParameter("precio"));
				String categoria = request.getParameter("categoria");

				Libro milibro = new Libro(isbn, titulo, autor, precio);
				Categoria micategoria = repositorioCategoria.buscarPorNombre(categoria);
				milibro.setCategoria(micategoria);
				repositorio.insertar(milibro);

				List<Libro> listaLibros = repositorio.buscarTodos();
				request.setAttribute("listaLibros", listaLibros);
				despachador = request.getRequestDispatcher("libros2/listaLibros.jsp");

			} else if (accion.equals("borrar")) {

				String isbn = request.getParameter("isbn");
				Libro libro = new Libro(isbn);
				repositorio.borrar(libro);

				List<Libro> listaLibros = repositorio.buscarTodos();
				request.setAttribute("listaLibros", listaLibros);
				despachador = request.getRequestDispatcher("libros2/listaLibros.jsp");

			} else if (accion.equals("detalle")) {

				String isbn = request.getParameter("isbn");
				Libro libro = repositorio.buscarPorISBN(isbn);
				request.setAttribute("libro", libro);
				despachador = request.getRequestDispatcher("libros2/detalle.jsp");

			} else if (accion.equals("editar")) {

				String isbn = request.getParameter("isbn");
				Libro libro = repositorio.buscarPorISBN(isbn);
				request.setAttribute("libro", libro);
				despachador = request.getRequestDispatcher("libros2/editar.jsp");

			} else if (accion.equals("salvar")) {

				String isbn = request.getParameter("isbn");
				String titulo = request.getParameter("titulo");
				String autor = request.getParameter("autor");
				int precio = Integer.parseInt(request.getParameter("precio"));
				String categoria = request.getParameter("categoria");

				Libro milibro = new Libro(isbn, titulo, autor, precio);
				Categoria micategoria = repositorioCategoria.buscarPorNombre(categoria);
				milibro.setCategoria(micategoria);
				repositorio.salvar(milibro);

				List<Libro> listaLibros = repositorio.buscarTodos();
				request.setAttribute("listaLibros", listaLibros);
				despachador = request.getRequestDispatcher("libros2/listaLibros.jsp");

			}

		} else {

			List<Libro> listaLibros = new ArrayList<Libro>();

			listaLibros = repositorio.buscarTodos();

			despachador = request.getRequestDispatcher("libros2/listaLibros.jsp");
			request.setAttribute("listaLibros", listaLibros);
		}

		despachador.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}

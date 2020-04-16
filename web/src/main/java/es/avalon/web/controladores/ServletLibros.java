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
import es.avalon.servicios.ServicioLibros;

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
		//LibroRepository repositorio = new LibroRepositoryJPA();
		//CategoriaRepository repositorioCategoria = new CategoriaRepositoryJPA();

		ServicioLibros servicio=new ServicioLibros();
		// hay algun tipo de accion
		if (accion != null) {

			if (accion.equals("formularioInsertar")) {

				despachador = request.getRequestDispatcher("libros2/formularioInsertar.jsp");

			} else if (accion.equals("borrar")) {

				String isbn = request.getParameter("isbn");
				Libro milibro = new Libro(isbn);

				servicio.borrarLibros(milibro);
				List<Libro> listaLibros = servicio.buscarLibrosTodos();
				request.setAttribute("listaLibros", listaLibros);
				// redirigir
				despachador = request.getRequestDispatcher("libros2/listaLibros.jsp");

			}else if (accion.equals("detalle")) {

				String isbn = request.getParameter("isbn");
				
				Libro libro = servicio.buscarLibrosPorISBN(isbn);
				request.setAttribute("libro", libro);

				despachador = request.getRequestDispatcher("libros2/detalle.jsp");

			} 
			
			else if (accion.equals("editar")) {

				String isbn = request.getParameter("isbn");

				Libro libro = servicio.buscarLibrosPorISBN(isbn);
				request.setAttribute("libro", libro);

				despachador = request.getRequestDispatcher("libros2/editar.jsp");

			} else if (accion.equals("salvar")) {

				String isbn = request.getParameter("isbn");
				String titulo = request.getParameter("titulo");
				String autor = request.getParameter("autor");
				int precio = Integer.parseInt(request.getParameter("precio"));
				String categoria = request.getParameter("categoria");
				Libro milibro = new Libro(isbn, titulo, autor, precio);
				Categoria objetoCategoria= servicio.buscarCategoriaPorNombre(categoria);
				milibro.setCategoria(objetoCategoria);
				
				
				servicio.salvarLibros(milibro);
				
				List<Libro> listaLibros = servicio.buscarLibrosTodos();
				request.setAttribute("listaLibros", listaLibros);
				// redirigir
				despachador = request.getRequestDispatcher("libros2/listaLibros.jsp");

			}
			else if (accion.equals("ordenar")) {

				String ordenarPor=request.getParameter("ordenarPor");
				if(ordenarPor.equals("titulo")) {
					List<Libro> listaLibros=servicio.OrdenarLibrosPorTitulo();
					request.setAttribute("listaLibros",listaLibros);	
				}else {
				List<Libro> listaLibros=servicio.OrdenarLibrosPorAutor();
				request.setAttribute("listaLibros",listaLibros);
				}
				despachador = request.getRequestDispatcher("libros2/listaLibros.jsp");

			}
			else {

				String isbn = request.getParameter("isbn");
				String titulo = request.getParameter("titulo");
				String autor = request.getParameter("autor");
				int precio = Integer.parseInt(request.getParameter("precio"));
				String categoria = request.getParameter("categoria");

				Libro milibro = new Libro(isbn, titulo, autor, precio);

				// uso el nuevo repositorio para buscar una categoria
				Categoria micategoria = servicio.buscarCategoriaPorNombre(categoria);
				// asigno al libro la categoria que necesita
				milibro.setCategoria(micategoria);

				// salvo el libro
				servicio.insertarLibros(milibro);

				List<Libro> listaLibros = servicio.buscarLibrosTodos();
				request.setAttribute("listaLibros", listaLibros);
				// redirigir
				despachador = request.getRequestDispatcher("libros2/listaLibros.jsp");
			}

		} else {

			List<Libro> listaLibros = new ArrayList<Libro>();

			listaLibros = servicio.buscarLibrosTodos();

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
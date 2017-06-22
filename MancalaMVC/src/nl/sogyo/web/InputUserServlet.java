package nl.sogyo.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nl.sogyo.model.Mancala;

/**
 * Servlet implementation class InputUserServlet
 */
@WebServlet("/InputUserServlet")
public class InputUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InputUserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Mancala mancala;
		
		if (session.isNew()) {
			mancala = new Mancala();
		} else {
			mancala = (Mancala) session.getAttribute("mancala");
		}

		if (mancala.getSpelerBeurt() == 0) {
			if (mancala.getWinnaar() == 0) {
				request.setAttribute("boodschapNaarGebruiker", "Spel afgelopen! Het is gelijkspel.");
			} else {
				request.setAttribute("boodschapNaarGebruiker", "Spel afgelopen! Speler " + mancala.getWinnaar() + " is de winnaar.");
			}
		} else {
			request.setAttribute("boodschapNaarGebruiker", "Speler " + mancala.getSpelerBeurt() + " is aan de beurt.");
		}
		
		int[] waardesBakjes = mancala.getWaardesBakjes();
		request.setAttribute("waardesBakjes", waardesBakjes);
		session.setAttribute("mancala", mancala);
		
		RequestDispatcher view = request.getRequestDispatcher("mancala.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Mancala mancala = (Mancala) session.getAttribute("mancala");
		
		if (request.getParameter("nieuwSpel") != null) {
			mancala = new Mancala();
		} else {
			int bakje = Integer.parseInt(request.getParameter("bakje"));
			mancala.speelBakje(bakje);
		}
		
		session.setAttribute("mancala", mancala);
		doGet(request, response);
	}
}

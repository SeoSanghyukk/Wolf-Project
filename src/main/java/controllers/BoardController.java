package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.dao.BoardDAO;
import board.dto.BoardDTO;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		HttpSession session= request.getSession();
		String cmd = request.getRequestURI();
		
		BoardDAO boardDAO= BoardDAO.getInstance();
		List<BoardDTO> list = new ArrayList<>();
		
		try {
			if(cmd.equals("/list.board")) {
				Object boardList[] = boardDAO.selectAll();
				
				request.setAttribute("list", boardList[0]);
				request.setAttribute("nickname", boardList[1]);
				
				request.getRequestDispatcher("/views/board/board_view.jsp").forward(request, response);
				
			} else if(cmd.equals("/detail.board")) {
				int seq= Integer.parseInt(request.getParameter("seq"));
				Object boardList[] =boardDAO.selectBoard(seq);
				request.setAttribute("dto", boardList[0]);
				request.setAttribute("nickname", boardList[1]);
				
				
				request.getRequestDispatcher("/views/board/board_detail.jsp").forward(request, response);
				
			} else if(cmd.equals("/insert.board")) {
				
			} else if(cmd.equals("/delete.board")) {
				
			} else if(cmd.equals("/detail.board")) {
				
			} else if(cmd.equals("/detail.board")) {
				
			}
			
		} catch (Exception e) {
		
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

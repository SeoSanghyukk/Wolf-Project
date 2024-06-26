package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import game.dao.GameDAO;
import game.dto.GameDTO;
import game.dto.ScoreDTO;
import game_score.dao.GameScoreDAO;
import images.dao.ImagesDAO;
import rank.dao.RankDAO;
import rank.dto.RankDTO;


@WebServlet("*.game")
public class GameController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		GameDAO dao = GameDAO.getInstance();
		ImagesDAO imagesDAO = ImagesDAO.getInstance();
		RankDAO rankDAO = RankDAO.getInstance();
		GameScoreDAO gameScoreDAO = GameScoreDAO.getInstance();
		Gson g = new Gson();
		
		String cmd= request.getRequestURI();
		System.out.println("확인중 :" + cmd);
		
		try {
			if(cmd.equals("/list.game")) {
				
				List<GameDTO> list = dao.getList();
				
				// image_cdoe 3 == thumbnail
				int image_code = 3;
				
				// 서버에서 thumbnail 경로 추가
				for(GameDTO game : list) {
					String sysname = imagesDAO.getImageName(game.getSeq(), image_code);
					if(sysname == null) {
						game.setThumbnail(sysname);
					} else {
						game.setThumbnail("thumbnails/" + sysname);
					}
				}
				
				request.setAttribute("list", list);
				request.getRequestDispatcher("/views/game/gameList.jsp").forward(request, response);
				
			}else if(cmd.equals("/detail.game")) {
				
			    int seq = Integer.parseInt(request.getParameter("seq"));
			    GameDTO dto = dao.getDetail(seq);
			    
			    int image_code = 3;
			    
			    String sysname = imagesDAO.getImageName(seq, image_code);
			    if(sysname != null) {
					dto.setThumbnail("thumbnails/" + sysname);
				}	
			    
			    request.setAttribute("dto", dto);
			    request.getRequestDispatcher("/views/game/gameDetail.jsp").forward(request, response);
			    
			}else if(cmd.equals("/gameview.game")) {
				String game_seq = request.getParameter("seq");
				String id = (String)request.getSession().getAttribute("WolfID");

				List<RankDTO> topRank = rankDAO.selectRank(Integer.parseInt(game_seq));

				int score = 0;
				if(id != null) {
					ScoreDTO myScore = gameScoreDAO.getMyRank(Integer.parseInt(game_seq), id);
					if(myScore != null) {
						score = myScore.getScore();
					}
				}
				
				request.setAttribute("seq", Integer.parseInt(game_seq));
				request.setAttribute("topRank", topRank);
				request.setAttribute("myScore", score);
				request.getRequestDispatcher("/views/game/game.jsp").forward(request, response);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

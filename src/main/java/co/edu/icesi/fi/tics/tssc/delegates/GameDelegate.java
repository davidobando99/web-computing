package co.edu.icesi.fi.tics.tssc.delegates;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;

public class GameDelegate implements IGameDelegate{
	
	public final static String SERVER = "http://localhost:8080/";
	RestTemplate rest;
	public GameDelegate(RestTemplate rest) {
		rest = new RestTemplate();
	}
	@Override
	public TsscGame getGame(long id) throws Exception {
		TsscGame game = rest.getForObject(SERVER+ "games/" +id, TsscGame.class);
		if(game==null) {
			throw new Exception("game is null");
		}
		return game;
	}
	@Override
	public Iterable<TsscGame> getGames() {
		TsscGame[] games = rest.getForObject(SERVER+ "games", TsscGame[].class);
		List<TsscGame> at;
		try {
			at = Arrays.asList(games);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	@Override
	public TsscGame addGame(TsscGame newGame) {
		TsscGame game = rest.postForEntity(SERVER + "games", newGame, TsscGame.class).getBody();
		if (game == null) {
			throw new IllegalArgumentException("Game is null");
		}
		return game;
	}
	@Override
	public void deleteGame(TsscGame game) {
		if (game == null) {
			throw new IllegalArgumentException("Game is null");
		}
		rest.delete(SERVER + "games/" +game.getId());
		
		
	}
	
	
	
	
	
	
	
	

}

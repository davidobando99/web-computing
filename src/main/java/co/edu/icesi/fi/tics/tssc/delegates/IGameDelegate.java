package co.edu.icesi.fi.tics.tssc.delegates;

import co.edu.icesi.fi.tics.tssc.model.TsscGame;

public interface IGameDelegate {
	
	public TsscGame getGame(long id)  throws Exception;
	public Iterable<TsscGame> getGames();
	public TsscGame addGame(TsscGame newGame);
	public void deleteGame(TsscGame game);

}

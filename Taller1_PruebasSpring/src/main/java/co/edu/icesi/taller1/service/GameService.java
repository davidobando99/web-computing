package co.edu.icesi.taller1.service;

import co.edu.icesi.taller1.model.TsscGame;
import co.edu.icesi.taller1.model.TsscTopic;

public interface GameService {
	
	public TsscGame saveGame(TsscGame game, TsscTopic topi)throws Exception;
	public TsscGame editGame(TsscGame gameEdit, TsscTopic topic)throws Exception;
	public TsscGame saveGame2(TsscGame game, TsscTopic topic)throws Exception;
	

}

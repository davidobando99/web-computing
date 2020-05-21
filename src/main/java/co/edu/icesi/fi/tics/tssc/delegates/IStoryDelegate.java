package co.edu.icesi.fi.tics.tssc.delegates;


import co.edu.icesi.fi.tics.tssc.model.TsscStory;

public interface IStoryDelegate {
	
	
	public TsscStory getStory(long id)  throws Exception;
	public Iterable<TsscStory> getStories();
	public TsscStory addStory(TsscStory newStory);
	public void deleteStory(TsscStory story);

}

package co.edu.icesi.fi.tics.tssc.delegates;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.fi.tics.tssc.model.TsscStory;

@Component
public class StoryDelegate implements IStoryDelegate{
	
	public final static String SERVER = "http://localhost:8084/";
	RestTemplate rest;
	public StoryDelegate() {
		rest = new RestTemplate();
	}
	@Override
	public TsscStory getStory(long id) throws Exception{
		// TODO Auto-generated method stub
		TsscStory story = rest.getForObject(SERVER+ "stories/" +id, TsscStory.class);
		if(story==null) {
			throw new Exception("Story is null");
		}
		return story;
	}
	@Override
	public Iterable<TsscStory> getStories() {
		TsscStory[] stories = rest.getForObject(SERVER+ "stories", TsscStory[].class);
		List<TsscStory> at;
		try {
			at = Arrays.asList(stories);
			return at;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public TsscStory addStory(TsscStory newStory) {
		TsscStory story = rest.postForEntity(SERVER + "stories", newStory, TsscStory.class).getBody();
		if (story == null) {
			throw new IllegalArgumentException("story is null");
		}
		return story;
	}
	@Override
	public void deleteStory(TsscStory story) {
		if (story == null) {
			throw new IllegalArgumentException("story is null");
		}
		rest.delete(SERVER + "stories/" +story.getId());
		
	}

}

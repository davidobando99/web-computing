package co.edu.icesi.taller1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.taller1.model.TsscStory;
import co.edu.icesi.taller1.model.TsscTopic;
@Repository
public interface StoryRepository extends CrudRepository<TsscStory,Long>{

}

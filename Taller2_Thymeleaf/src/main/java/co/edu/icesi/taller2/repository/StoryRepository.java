package co.edu.icesi.taller2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.edu.icesi.taller2.model.TsscStory;
import co.edu.icesi.taller2.model.TsscTopic;
@Repository
public interface StoryRepository extends CrudRepository<TsscStory,Long>{

}

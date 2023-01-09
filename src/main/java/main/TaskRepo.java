package main;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import main.obj.Task;

public interface TaskRepo extends CrudRepository<Task, Integer> {
	
	Optional<Task> findById(Integer id);
	
	List<Task> findByComplete(boolean complete);
	
	List<Task> findAll();

}

package todoapp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import todoapp.entity.Todo;

@Repository
public interface TodoRespository extends CrudRepository<Todo, Integer> {

	@Query("from Todo t order by t.createdTime desc")
	Iterable<Todo> findAllOrderByCreatedTimeDesc();
}

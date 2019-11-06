package todoapp.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import todoapp.entity.Todo;
import todoapp.repository.TodoRespository;

@Controller
@RequestMapping("/todo")
public class TodoController {

	@Autowired
	private TodoRespository todoRespository;

	@GetMapping
	public ModelAndView list(ModelAndView mav) {
		//List<Todo> todoList = new ArrayList<>();
		//todoList.add(new Todo(1, "テスト1", "作業中", new Date()));
		//todoList.add(new Todo(2, "テスト2", "作業中", new Date()));
		
		//Iterable<Todo> todoList = this.todoRespository.findAll();
		Iterable<Todo> todoList = this.todoRespository.findAllOrderByCreatedTimeDesc();
		mav.setViewName("todo");
		mav.addObject("todoList", todoList);
		return mav;
	}
	
	@PostMapping
	public ModelAndView create(@RequestParam("comment") String comment,  ModelAndView mav) {
		if (comment != null && comment.trim().length() > 0) {
			Todo todo = new Todo();
			todo.setComment(comment);
			todo.setStatus("作業中");
			todo.setCreatedTime(new Date());
			this.todoRespository.save(todo);
		}
		mav.setViewName("redirect:/todo");
		return mav;
	}
}

package main;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import main.obj.Task;
import main.obj.TaskWrap;

@Configuration
@Controller
@RequestMapping(path="/demo")
public class MainController implements WebMvcConfigurer{
	@Autowired
	private TaskRepo taskRepo;
	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	@PostMapping(path="/add")	//curl
	public @ResponseBody String addNewTask (@RequestParam String name, 
			@RequestParam boolean complete) {
		taskRepo.save(new Task(name, complete));
		return "Saved";
	}
	
	@GetMapping(path="/new")
	public String addNewTask (Model model) {
		model.addAttribute("task", new Task());
		return "new";
	}
	
	@PostMapping(path="/new")
	public String addNewTask (@ModelAttribute Task task, Model model) {
		log.info("Saved "+task.getName());
		taskRepo.save(task);
		return "redirect:all";
	}

	@GetMapping(path="/get")
	public @ResponseBody Iterable<Task> getAllTasks() {
		log.info("List all tasks...");
		return taskRepo.findAll();
	}
	
	@GetMapping(path="/all")
	public String showAll(Model model) {
		//model.addAttribute("tasks", taskRepo.findAll());
		List<Task> tasks = new ArrayList<>();
		taskRepo.findAll().iterator().forEachRemaining(tasks::add);
		model.addAttribute("form", new TaskWrap(tasks));
		//model.addAttribute("tasks", taskRepo.findAll());
		log.info("Display tasks...");
		return "all";
	}
	
	@PostMapping("/save")
	public String saveList(@ModelAttribute TaskWrap form, Model model) {
		taskRepo.saveAll(form.getTasks());
		model.addAttribute("tasks", taskRepo.findAll());
		log.info("Save tasks...");
		return "redirect:all";
	}
	
	@PostMapping("/delete")
	public String deleteTask(@RequestParam(name="id") String id, Model model) {
		try {
			taskRepo.deleteById(Integer.valueOf(id));
		} catch (NumberFormatException e) {}
		model.addAttribute("tasks", taskRepo.findAll());
		log.info("Delete task..."+id);
		return "redirect:all";
	}
	
	
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/hello").setViewName("hello");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/demo/all").setViewName("all");
	}
}
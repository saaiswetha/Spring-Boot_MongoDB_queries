package studentMongoApp.Controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import studentMongoApp.Model.student;
import studentMongoApp.Repository.StudentRepository;

@RestController
public class StudentControllers {

	@Autowired
	private StudentRepository repository;
	
	@GetMapping("/findAll") //-
	public List<student> findAll(){
		return repository.findAll();
	}	
	
	@GetMapping("/findByFname") //-
	public List<student> findByFname(@RequestParam String fname){        //http://localhost:8080/findByFname?fname=saai --> GET method
		return repository.findByFname(fname);
	}
	
	@GetMapping("/findfnameregex") //-
	public List<student> getByFnameRegexx(@RequestParam String regexp){
		return repository.getByFnameRegexx(regexp);
	}
	
	//find name using a score
	@GetMapping("/findbyscore") //- 
	public String findByScore(@RequestParam int score){
		return repository.findByScore(score).getFname();
	}
	
	@PostMapping("/addStudent") //-
	public String addStudent(@RequestBody student s) {
		repository.save(s);
		return "added student "+s.getFname();
	}
	
	@GetMapping("/scoremorethan")//-
	public List<student> findStudentWithScoreMoreThan(@RequestParam int score){
		return repository.findStudentWithScoreMoreThan(score);
	}
	
	@GetMapping("/namewithscore")//- 
	public String findNameWithScore(@RequestParam String fname,@RequestParam int score){
		return repository.findNameWithScore(fname,score).getFname()+"\n"+repository.findNameWithScore(fname,score).getScore();
	}
	
	@GetMapping("/bornbeforescoreless")
	public List<student> findStudentBornBeforeAndScoreGreaterThan(@RequestParam int score){
		return repository.findStudentBornBeforeAndScoreGreaterThan(score);
	}
	
	@GetMapping("/studentlistbyscore") //-
	public List<student> findStudentByScoreLessThan(@RequestParam int score){
		return repository.findStudentByScoreLessThan(score);
	}
	
	@GetMapping("/studentbyscore") //-
	public int findStudentCountByScoreLessThan(@RequestParam int score){
		return repository.findStudentCountByScoreLessThan(score);
	}
	
	@GetMapping("/scorebetween") //-
	public List<student> StudentsWithScoreBetween(@RequestParam int end,@RequestParam int start){
		return repository.StudentsWithScoreBetween(end, start);
	}
		
	@GetMapping("/orderbyfname") //-
	public List<student> findByOrderByFnameAsc(){
		return repository.findByOrderByFnameAsc();
	}
	
	@GetMapping("/orderbyscore") //-
	public List<student> findByOrderByScoreAsc(){
		return repository.findByOrderByScoreAsc();
	}
	
	@GetMapping("/orderbyscoreandfnamelike") //-
	public List<student> findByFnameLikeOrderByScoreAsc(@RequestParam String exp){
		return repository.findByFnameLikeOrderByScoreAsc(exp);
	}
	
	@GetMapping("/fnamestaringwith") //-
	public List<student> findByFnameStartingWith(@RequestParam String exp){
		return repository.findByFnameStartingWith(exp);
	}
	
	@GetMapping("/lnameendingwith") //-
	public List<student> findByLnameEndingWith(@RequestParam String exp){
		return repository.findByLnameEndingWith(exp);
	}
	
	@GetMapping("/scorein")
	public List<student> findStudentWithScoreIn(@RequestParam Collection scores){
		return repository.findStudentWithScoreIn(scores);
	}
	
	@GetMapping("/soretedscorelist")
	public List<student> StudentWithSortedScoreLessThan(@RequestParam int score,@RequestParam Sort sort){
		return repository.StudentWithSortedScoreLessThan(score,sort);
	}
	
	@GetMapping("/exists") //-
	public List<student> doesStudentExist(@RequestParam String fname){
		return repository.doesStudentExist(fname);
	}
	
	@GetMapping("/sortascbyscore") //-
	public List<student> SortByScoreASC(){
		return repository.SortByScoreASC();
	}
	
	@GetMapping("/sortdescbyscore") //-
	public List<student> SortByScoreDESC(){
		return repository.SortByScoreDESC();
	}
}

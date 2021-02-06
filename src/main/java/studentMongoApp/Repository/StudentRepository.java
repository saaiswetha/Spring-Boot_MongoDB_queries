package studentMongoApp.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import studentMongoApp.Model.student;

public interface StudentRepository extends MongoRepository<student,Integer> {

//--------------------------------------------------QUERIES-------------------------------------------------------------------
	
	//find with name
	@Query("{'fname':?0}")
	public List<student> findByFname(String fname);
	
	//find details not equal to fname
	public List<student> findByFnameNot(String fname);
	
	//find details like fname
	public List<student> findByFnameLike(String fname);     //in built methods provided by mongo repository
	//or
	public List<student> findByFnameRegex(String fname); 
	
	//regex
    @Query("{'fname' : {$regex : ?0}}")
    public List<student> getByFnameRegexx(String regexp);
    
	//find student name who scored 89
	@Query(value="{'score':?0}",fields= "{'fname':1}")
	public student findByScore(int score);
	
	//find with score more than
	@Query(value="{'score' : {$gt: ?0}}",fields="{'_id':0,'fname':1,'score':1}")
	public List<student> findStudentWithScoreMoreThan(int score); 
	
	@Query(value = "{fname : ?0, score : ?1}", fields = "{ 'fname' : 1,'score' : 1}")
	public student findNameWithScore(String fname, int score); 
	
	//born before the year 2000 and score>=60
	@Query("{$and:[{year:{$lt:'2000-01-01'}},{score:{$gte:?0}}]}")
	public List<student> findStudentBornBeforeAndScoreGreaterThan( int score);
	
	//student details with score <50
	@Query( "{score : {$lt:?0}}")
	public List<student> findStudentByScoreLessThan( int score);
	
	//count no.of students with score <50
	@Query(value = "{score : {$lt:?0}}", count = true)
	public int findStudentCountByScoreLessThan( int score); 
	
	//find student fname starting with 
	//find student with a fname and lname 
	@Query("{$and:[{fname:?0},{lname:?1}]}")
	public List<student> findStudentByFnameAndLname(String fname, String lname);
	
	//is null fnames
	public List<student> findByFnameIsNull();
	
	//not null lnames
	public List<student> findByLnameIsNotNull();
	
	//score between a range
	@Query("{'score':{$lt:?0,$gt:?1}}")
	public List<student> StudentsWithScoreBetween(int end,int start);
	
	//or
	public List<student> findByScoreBetween(int end,int start);
	
	//sort the scores less than
	@Query("{'score':{$lt:?0}}")
	public List<student> StudentWithSortedScoreLessThan(int score,Sort sort);
	
	// fname order-by score descending 
	public List<student> findByOrderByScoreAsc();
	
	//oreder by alphabetic order
	public List<student> findByOrderByFnameAsc();
	
	//find fname like exp(=e,eg,emm --> finds those names which contain exp) and order by ascending score
	public List<student> findByFnameLikeOrderByScoreAsc(String exp);
	
	//find fname staring with 
	public List<student> findByFnameStartingWith(String exp);
	
	//find lname ending with
	public List<student> findByLnameEndingWith(String exp);
	
	//score in
	@Query("{'score':{$in:?0}}")
	public List<student> findStudentWithScoreIn(Collection scores);
	
	//fname exists??
	@Query(value="{'fname':?0}",exists=true)
	public List<student> doesStudentExist(String fname);
	
	//sort all details wrt score in ASC
	@Query(value="{}",sort="{score:1}")                                  //sort , 1 = ASC , -1 = DESC
	public List<student> SortByScoreASC();
	
	//sort details wrt score in DESC
	@Query(value="{}",sort="{score:-1}")                                  //sort , 1 = ASC , -1 = DESC
	public List<student> SortByScoreDESC();
	
	
}


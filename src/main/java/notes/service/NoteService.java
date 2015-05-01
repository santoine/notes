package notes.service;

import java.util.List;
import java.util.Optional;

import notes.domain.Note;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NoteService {

	@Autowired
	private NoteRepository repo;
	
	public List<Note> findAll(){
		return repo.findAll();
	}
	
	public Note add(Note note){
		return repo.save(note);
	}

	public Optional<Note> get(Long id) {
		return Optional.ofNullable(repo.findOne(id));
	}
}

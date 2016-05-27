package notes.service;

import notes.Application;
import notes.domain.Note;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest({ "server.port=0" })
public class NoteRepositoryIntegrationTest {

	@Autowired
	private NoteRepository noteRepository;

	@Before
	public void setUp() {
		noteRepository.deleteAll();
	}

	@Test
	public void saveNote() throws Exception {
		Note note = new Note("title", "content");
		noteRepository.save(note);
		
		Note res = noteRepository.findOne(1L);
		assertNotNull(res);
	}
}

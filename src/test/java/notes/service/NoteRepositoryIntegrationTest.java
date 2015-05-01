package notes.service;

import static org.junit.Assert.*;
import notes.Application;
import notes.domain.Note;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({ "server.port=0" })
public class NoteRepositoryIntegrationTest {

	@Autowired
	private NoteRepository sut;

	@Before
	public void setUp() {
		sut.deleteAll();
	}

	@Test
	public void saveNote() throws Exception {
		Note note = new Note("title", "content");
		sut.save(note);
		
		Note res = sut.findOne(1L);
		assertNotNull(res);
	}
}

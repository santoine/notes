package notes.web;

import notes.domain.Note;
import notes.service.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
@RequestMapping(value = "/notes")
public class NotesController {

	private static Logger LOGGER = LoggerFactory.getLogger(NotesController.class);

	public static final String NOTE_FORM = "noteForm";
	public static final String NOTE_LIST = "noteList";
	public static final String NOTE_ATTRIBUTE = "note";

	@Autowired
	private NoteService noteService;

	@RequestMapping(value = "/new", method = RequestMethod.GET, produces = { "text/html","text/css" } )
	public String form(Model model) {
		LOGGER.info("open the page to create new note");
		model.addAttribute(NOTE_ATTRIBUTE, new Note());
		return NOTE_FORM;
	}

	@RequestMapping(method = RequestMethod.GET,  produces = { "text/html","text/css" } )
	public String getAll(Model model) {
		LOGGER.info("open the page to display all the notes");
		// TODO retrieve all the not without pagination....
		model.addAttribute("notes", noteService.findAll());
		return NOTE_LIST;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String displayNote(@PathVariable Long id, Model model) {
		LOGGER.info("open the page to display note " + id);
		Optional<Note> note = noteService.get(id);

		if (note.isPresent()) {
			model.addAttribute(NOTE_ATTRIBUTE, note.get());
			return  "note";
		} else {
			return "main";
		}
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveNote(@ModelAttribute Note note, Model model) {
		noteService.add(note);
		return new ModelAndView("redirect:new");
	}
}
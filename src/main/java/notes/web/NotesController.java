package notes.web;

import java.util.Optional;

import notes.domain.Note;
import notes.service.NoteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/notes")
public class NotesController {

	@Autowired
	private NoteService noteService;

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String form(Model model) {
		model.addAttribute("note", new Note());
		return "noteForm";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getAll(Model model) {
		model.addAttribute("notes", noteService.findAll());
		return "noteList";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String displayNote(@PathVariable Long id, Model model) {

		Optional<Note> note = noteService.get(id);
		if (note.isPresent()) {
			model.addAttribute("note", note.get());
			return "note";
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
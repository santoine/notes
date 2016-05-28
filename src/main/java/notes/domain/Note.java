package notes.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Note {
	
	@Id
	@GeneratedValue
	private long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")  
	private LocalDate date;
	
	private String title;
	
	private String content;
	
	public Note(){}
	
	public Note(String title, String content){
		this.date = LocalDate.now();
		this.title = title;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	@JsonDeserialize(using = LocalDateDeserializer.class)  
	public LocalDate getDate() {
		return date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("id", id)
				.append("date", date)
				.append("title", title)
				.append("content", content)
				.build();
	}
}

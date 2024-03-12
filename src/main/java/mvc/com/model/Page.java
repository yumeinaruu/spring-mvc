package mvc.com.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity(name = "pages")
@Data
@ToString(exclude = "book")
@EqualsAndHashCode(exclude = "book")
public class Page {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "page_seq")
    @SequenceGenerator(name = "page_seq", sequenceName = "pages_id_seq", allocationSize = 1)
    private int id;

    //@JsonBackReference - чтобы json не отрисовывал Pages после захода в book
    @JoinColumn(name = "book_id")
    @ManyToOne
    private Book book;

    @Column(name = "text")
    private String text;
}

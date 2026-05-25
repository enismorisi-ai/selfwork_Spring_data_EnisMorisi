package it.aulab.selfwork_spring_data;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;

import it.aulab.selfwork_spring_data.models.Author;
import it.aulab.selfwork_spring_data.models.Post;
import it.aulab.selfwork_spring_data.repositories.AuthorRepository;
import it.aulab.selfwork_spring_data.repositories.PostRepository;

// @SpringBootTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class SelfworkSpringDataApplicationTests {

	@Autowired
	AuthorRepository authorRepository;
	@Autowired
	PostRepository postRepository;

	private static String commonEmailDomain;

	@BeforeAll
	static void setupAll(){
		System.out.println("Configurazione delle impostazioni iniziali comuni");
		commonEmailDomain = "@test.it";
	}

	@BeforeEach
	void load(){
		System.out.println("Caricamento dei dati...");

		// Authors 
		Author a1 = new Author();
		a1.setName("Giuseppe");
		a1.setSurname("Verdi");
		a1.setEmail("VerdiG" + commonEmailDomain);
		//per usare authorRepository dobbiamo iniettarlo nella classe con @Autowired
		authorRepository.save(a1);


		Author a2 = new Author();
		a2.setName("Enis");
		a2.setSurname("Morisi");
		a2.setEmail("enmo@" + commonEmailDomain);
		authorRepository.save(a2);

		Author a3 = new Author();
		a3.setName("Luca");
		a3.setSurname("Bozzali");
		a3.setEmail("lubo" + commonEmailDomain);
		authorRepository.save(a3);

		// Posts
		Post p1 = new Post();
		p1.setTitle("Titolo 1");
		p1.setBody("Body 1");
		p1.setAuthor(a1);

		postRepository.save(p1);

		Post p2 = new Post();
		p2.setTitle("Titolo 2");
		p2.setBody("Body 2");
		p2.setAuthor(a2);

		postRepository.save(p2);

		Post p3 = new Post();
		p3.setTitle("Titolo 3");
		p3.setBody("Body 3");
		p3.setAuthor(a1);

		postRepository.save(p3);
	}


	@Test
	void contextLoads() {
	}

	@Test
	void countAuthors(){
		System.out.println("Contando gli autori...");
		assertThat(authorRepository.count()).isEqualTo(3);
	}

	@Test
	void findByName(){
		assertThat(authorRepository.findByName("Giuseppe"))
		.extracting("name")
		.containsOnly("Giuseppe");
	}

	// @Test
	// void findAuthorByEmail(){
	// 	System.out.println("Cercando un autore tramite mail...");
	// 	Author foundAuthor = authorRepository.findByEmail("lubo@test.it");

	// 	assertThat(foundAuthor)
	// 	.isNotNull()
	// 	.extracting(Author::getName, Author::getSurname)
	// 	.containsExactly("Luca","Bozzali");
	// }

	@Test
	void sameNameAuthor(){
		assertThat(authorRepository.authorsWithSameName())
		.extracting("name")
		.containsOnly("Giuseppe");
	}

	@Test
	void sameNameAuthorNotNative(){
		assertThat(authorRepository.authorWithSameNameNotNative())
		.extracting("surname")
		.containsOnly("Verdi");
	}

	@Test
	void checkAuthor(){
		System.out.println("Controllo dell'autore associato al post");
		assertThat(postRepository.findAll())	//mi trova tutti i Post (p1, p2, p3)
		.extracting(Post::getAuthor)	// estrai gli autori associati a questi post (a1 e a2)
		.extracting(Author::getName) 	// dall'oggetto autore estraine il nome ("Giuseppe", "Enis")
		.containsOnly("Giuseppe", "Enis"); // verifica che questi siano i nomi
	}

	@Test
	void deletePost(){
		System.out.println("Cancellando il primo Post...");
		Iterable<Post> posts = postRepository.findAll(); //findAll() restituisce una collection che puo' essere salvata all'interno di un Iterable
		Post p = posts.iterator().next(); //primo elemento della collezione
		postRepository.delete(p);	// lo eliminiamo
		assertThat(postRepository.findAll()).hasSize(2);
	}	

	@Test
	void updatePost(){
		System.out.println("Aggiorniamo un post...");
		Iterable<Post> posts = postRepository.findAll();
		Post p = posts.iterator().next();

		p.setTitle("Titolo 1 modificato");

		postRepository.save(p); //non esiste un vero e proprio update() method, possiamo usare semplicemente save()

		// //!Opzione 1
		assertThat(postRepository.findById(p.getId())).get() //dal postRepository cerca per ID l'elemento che ha come ID l'ID di p e prendilo
		.extracting("title") //estraine il campo "title"
		.isEqualTo("Titolo 1 modificato"); //verifica che sia uguale a "Titolo 1 modificato"

		//!Opzione 2
		// assertThat(postRepository.findAll().element(0))
		// .extracting("title")
		// .isEqualTo("Titolo 1 modificato");

	}
}

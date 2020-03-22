package swd20.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import swd20.Bookstore.domain.User;
import swd20.Bookstore.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void findByUsernameShouldReturnUser() {
		User users = userRepository.findByUsername("user");
		assertThat(users.getUsername()).isEqualTo("user");		
	}
	
	@Test // Luodaan uusi käyttäjä testiä varten tietokantaan lisäämistä varten
	public void createNewUser() {
		User user = new User("user2", "$2a$12$XAONe4DxG1Vi4Co57k8vY.c0TL/jBtDpc4bygDXRNSfxeac4w4pXC", "USER");
		userRepository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
	@Test
	public void deleteByUsername() {
		Long user = userRepository.deleteByUsername("user2");
	}

}




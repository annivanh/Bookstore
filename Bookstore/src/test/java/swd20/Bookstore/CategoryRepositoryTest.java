package swd20.Bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import swd20.Bookstore.domain.Category;
import swd20.Bookstore.domain.CategoryRepository;

	@RunWith(SpringRunner.class)
	@DataJpaTest
	public class CategoryRepositoryTest {
		
		@Autowired
		private CategoryRepository categoryRepository;
		
		@Test
		public void findByCategoryShouldReturnCategory() {
			List<Category> category = categoryRepository.findByName("Fiction");
			assertThat(category.equals("Fiction"));		
		}
		
		@Test // Luodaan uusi kategoria testiä varten tietokantaan lisäämistä varten
		public void createCategory() {
			Category category = new Category("Scifi");
			categoryRepository.save(category);
			assertThat(category.getCategoryid()).isNotNull();
		}
		
		@Test
		public void deleteCategory() {
			Long category = categoryRepository.deleteByName("Scifi");
		}
}

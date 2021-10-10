package com.example.finalreview;

import com.example.finalreview.entity.Delivery;
import com.example.finalreview.entity.Plant;
import com.example.finalreview.repository.PlantRepository;
import com.example.finalreview.service.PlantService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@DataJpaTest
class FinalreviewApplicationTests {

	@Autowired
	TestEntityManager testEntityManager;
	@Autowired
	PlantRepository plantRepository;

	@Autowired
	PlantService plantService;

	@Test
	public void testPriceLessThan(){
		Plant p = testEntityManager.persist(new Plant("Foo Leaf", 4.99));
		testEntityManager.persist(new Plant("Bar Weed", 5.01));

		List<Plant> cheapPlants = plantRepository.findByPriceLessThan(BigDecimal.valueOf(5));
		Assertions.assertEquals(1, cheapPlants.size(), "Size");
		Assertions.assertEquals(p.getId(), cheapPlants.get(0).getId(), "Id");
	}

	@Test
	public void testDeliveryCompleted(){
		Plant p = testEntityManager.persist(new Plant("Baz Root", 9.99));
		Delivery d = testEntityManager.persist(new Delivery("Leonard Bernstein", "234 West Side", LocalDateTime.now()));

		d.setPlants(Lists.newArrayList(p));
		p.setDelivery(d);

		//test both before and after
		Assertions.assertFalse(plantRepository.deliveryCompleted(p.getId()));
		d.setCompleted(true);
		Assertions.assertTrue(plantRepository.deliveryCompleted(p.getId()));
	}

}

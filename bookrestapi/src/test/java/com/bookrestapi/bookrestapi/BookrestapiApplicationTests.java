package com.bookrestapi.bookrestapi;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class BookrestapiApplicationTests {

	private Calculater c = new Calculater();

	@Test
	void contextLoads() {
	}

	@Test
	void testSum() {

		int expectedResult = 22;

		// actual
		int actualResult = c.doSum(12, 3, 7);

		assertThat(actualResult).isEqualTo(expectedResult);
	}

	@Test
	void testProdouct() {
		// expected
		int expected = 8;

		// actual
		int actualResult = c.doMulti(2, 4);

		assertThat(actualResult).isEqualTo(expected);
	}

	@Test
	@Disabled
	void testCompareNums() {

		// actual result
		Boolean actulResult = c.compareTwoNums(3, 3);

		assertThat(actulResult).isTrue();
	}

}

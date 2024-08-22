package com.testing.junit5;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class Junit5ApplicationTests {

	private  Calculator c = new Calculator();

	@Test
	void contextLoads() {
	}

	@Test
	void testSum(){
		//expected
		int expectedResult =17;

		//actual
		int actualResult = c.doSum(12,3,2);
		assertThat(actualResult).isEqualTo(expectedResult);
	}

	@Test
	@Disabled
	void testProduct(){
		//expected
		int expectedResult =12;

		int actualResult = c.doProduct(3,4);
		assertThat(actualResult).isEqualTo(expectedResult);
	}

	@Test
	void testCompareNums()
	{
		//actual
		Boolean actualResult = c.compareTwoNums(2,2);
		assertThat(actualResult).isTrue();
	}

}

package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HeadingTagCountTest {

	@Test
	void test() {
		JunitTesting test = new JunitTesting();
		int count = test.countHeadingTagTotal("http://shakespeare.mit.edu/macbeth/full.html");
		assertEquals(241,count);
	}

}

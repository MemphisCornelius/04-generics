package ohm.softa.a04.tests;

import ohm.softa.a04.CollectionUtility;
import ohm.softa.a04.SimpleFilter;
import ohm.softa.a04.SimpleList;
import ohm.softa.a04.SimpleListImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListTests {

	private final Logger logger = LogManager.getLogger();
	private SimpleList<Integer> testList;

	@BeforeEach
	void setup(){
		testList = new SimpleListImpl<>();

		testList.add(5);
		testList.add(3);
		testList.add(2);
		testList.add(4);
		testList.add(1);
	}

	@Test
	void testAddElements(){
		logger.info("Testing if adding and iterating elements is implemented correctly");
		int counter = 0;
		for(int i : testList){
			counter++;
		}
		assertEquals(5, counter);
	}

	@Test
	void testSize(){
		logger.info("Testing if size() method is implemented correctly");
		assertEquals(5, testList.size());
	}

	@Test
	void testFilterAnonymousClass(){
		logger.info("Testing the filter possibilities by filtering for all elements greater 2");
		SimpleList<Integer> result = testList.filter(new SimpleFilter<>() {
			@Override
			public boolean include(Integer item) {
				int current = item;
				return current > 2;
			}
		});

		for(int i : result){
			assertTrue(i > 2);
		}
	}

	@Test
	void testFilterLambda(){
		logger.info("Testing the filter possibilities by filtering for all elements which are dividable by 2");
		SimpleList<Integer> result = testList.filter(o ->  o % 2 == 0);
		for(int i : result){
			assertTrue(i % 2 == 0);
		}
	}

	@Test
	void testMap() {
		logger.info("Testing default map method by mapping every value to if it is devisible by two");
		SimpleList<Boolean> result = testList.map(i -> i % 2 == 0);
		Iterator<Integer> origIt = testList.iterator();
		Iterator<Boolean> mapIt = result.iterator();
		while (origIt.hasNext() && mapIt.hasNext()) {
			assertEquals(origIt.next() % 2 == 0, mapIt.next());
		}
	}

	@Test
	void testSort() {
		logger.info("Testing sort");
		boolean sorted = true;
		SimpleList<Integer> result = CollectionUtility.sort(testList, Integer::compareTo);

		Iterator<Integer> iterator = result.iterator();
		int current = iterator.next();

		while(iterator.hasNext()) {
			int next = iterator.next();
			if (current > next) {
				sorted = false;
				break;
			}
			current = next;
		}

		assertTrue(sorted);
	}
}
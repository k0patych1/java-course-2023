package edu.hw10.task1;

import edu.hw10.task1.generators.RandomIntGenerator;
import edu.hw10.task1.generators.RandomObjectGenerator;
import edu.hw10.task1.generators.RandomSimpleObjectGenerator;
import edu.hw10.task1.generators.RandomStringGenerator;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.RepeatedTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RandomObjectGeneratorTest {
    @RepeatedTest(1000)
    public void recordGenerationTest()
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Map<String, RandomSimpleObjectGenerator<?>> simpleGenerators = new HashMap<>();

        simpleGenerators.put("int", new RandomIntGenerator());
        simpleGenerators.put("java.lang.String", new RandomStringGenerator());

        RandomObjectGenerator generator = new RandomObjectGenerator(simpleGenerators);

        GlebInItmo gleb = generator.nextObject(GlebInItmo.class);

        assertTrue(gleb.numOfDops() >= 500_000);
    }

    @RepeatedTest(1000)
    public void pojoGenerationTest()
        throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Map<String, RandomSimpleObjectGenerator<?>> simpleGenerators = new HashMap<>();

        simpleGenerators.put("int", new RandomIntGenerator());
        simpleGenerators.put("java.lang.String", new RandomStringGenerator());

        RandomObjectGenerator generator = new RandomObjectGenerator(simpleGenerators);

        ChillingGleb gleb = generator.nextObject(ChillingGleb.class);

        assertTrue(gleb.getSalary() <= 0);
        assertTrue(gleb.getContestTink() <= 0);
        assertTrue(gleb.getNumOfOpravdanias() >= 100_000);
        assertNotNull(gleb.getSurname());
    }
}

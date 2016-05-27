package org.supply.simulator.display.assetengine;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Brandon on 5/26/2016.
 */
public class WeakReferenceEngineTest {

    boolean collected;// = false;

    @Before
    public void setUp() throws Exception {
        collected = false;

    }

    @Test
    public void test() throws InterruptedException {
        DummyReferenceEngine referenceEngine = new DummyReferenceEngine();


        createKeyAndDereference(referenceEngine);

        System.gc();
        Thread.sleep(1l);
        referenceEngine.cleanup();
        assertTrue(collected);
    }

    @Test
    public void testNoCollect() throws InterruptedException {
        DummyReferenceEngine referenceEngine = new DummyReferenceEngine();


        String reference = createKeyAndDereference(referenceEngine);

        System.gc();
        Thread.sleep(1l);
        referenceEngine.cleanup();
        assertFalse(collected);
    }



    private String createKeyAndDereference(DummyReferenceEngine referenceEngine) {
        return referenceEngine.get("hello");
    }


    class DummyReferenceEngine extends WeakReferenceEngine<String, String> {
        @Override
        protected void destroyHandle(String key) {
            System.out.println(key);
            collected = true;
        }

        @Override
        protected String createHandle(String key) {
            //this isn't actually redundant because
            //returning the key would result in the same ref
            //change the lines and see
            return new String(key);
//            return key;
        }
    }
}

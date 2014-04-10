package persistence;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Jon on 4/10/14.
 */
public class PluginUtilTest {


    @Test
    public void plugInTest(){

        PluginUtil pu = new PluginUtil();
        pu.loadPlugin("SQLPersistence");
        PersistenceProvider pp =pu.getPersistenceProvider();

    }
}

package driver;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import java.rmi.ServerException;

/**
 * Created by: film42 on: 3/29/14.
 */
public class TestDriver {
    public static void main(String[] args) throws ServerException {

        JUnitCore runner = new JUnitCore();
        runner.addListener(new TextListener(System.out));
        runner.run(DirectoryTestSuite.class);

    }
}

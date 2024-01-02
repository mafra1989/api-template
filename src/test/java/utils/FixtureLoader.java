package utils;

import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;

public class FixtureLoader {

    private FixtureLoader(){}

    public static void loadAllFixtures() {
        FixtureFactoryLoader.loadTemplates("utils.fixtures");
    }
}
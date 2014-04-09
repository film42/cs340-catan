package persistence;

import model.JsonImpl;
import model.base.GameImpl;
import modelInterfaces.base.Game;
import sun.plugin.dom.exception.PluginNotSupportedException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Adam on 4/7/14.
 */
public class PluginUtil {

    private String pluginName;
    private ConfigFormat configfile;

    private class ConfigFormat{
        String name;
        String persistenceprovider;
        String jar;
    }

    public void loadPlugin(String pluginName){
        this.pluginName = pluginName;
        //load jarPath and classPath from config file config.json
        String configjson = null;
        try {
            configjson = new Scanner(new File(this.pluginName + "/config.json")).useDelimiter("\\Z").next();
            configfile = JsonImpl.fromJson(configjson, ConfigFormat.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new PluginNotSupportedException("Not a valid plugin.");
        }
    }

    public PersistenceProvider getPersistenceProvider(){
        //use URLClassLoader to load PersistenceProvider
        

        return null;
    }
}

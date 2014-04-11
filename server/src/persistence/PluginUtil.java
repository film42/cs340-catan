package persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.JsonImpl;
import model.base.GameImpl;
import modelInterfaces.base.Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import java.util.Scanner;

import java.net.URLClassLoader;
import java.net.URL;

/**
 * Created by Adam on 4/7/14.
 */
public class PluginUtil {

    private String pluginName;
    private ConfigFormat configfile;

    public class ConfigFormat{
        String name;
        String persistenceprovider;
        String jar;
    }

	public void loadPlugin(String pluginName) {
        this.pluginName = pluginName;
        //load jarPath and classPath from config file config.json
        String configjson = null;
        try {
            configjson = new Scanner(new File("../" + this.pluginName + "/config.json")).useDelimiter("\\Z").next();
            GsonBuilder gb = new GsonBuilder();
            Gson gson = gb.create();
            configfile = gson.fromJson(configjson, ConfigFormat.class);
            //configfile = JsonImpl.fromJson(configjson, ConfigFormat.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public PersistenceProvider getPersistenceProvider(){
        //use URLClassLoader to load PersistenceProvider
        try{
            File jarFile = new File("../" + this.pluginName + "/" + configfile.jar);
            URL fileURL = jarFile.toURI().toURL();
            String jarURL = "jar:" + fileURL + "!/";
            URL urls [] = { new URL(jarURL) };
            URLClassLoader ucl = new URLClassLoader(urls);
            ucl.loadClass(configfile.persistenceprovider);
            PersistenceProvider provider = (PersistenceProvider) Class.forName(configfile.persistenceprovider, true,   ucl).newInstance();
            return provider;
        }
        catch (Exception e){
            e.printStackTrace();
        }
		return null;

    }
}

package info.mastera.dependencyInjection;

import info.mastera.util.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class DependencyInjection {

    private static final Logger logger = Logger.getLogger(DependencyInjection.class);

    private static final String ERROR_INVOKED_METHOD = "Exception thrown by an invoked method or constructor.";
    private static final String ERROR_ILLEGAL_ARGUMENT = "Error. Method has been passed an illegal or inappropriate argument.";

    private static final String ERROR_DEPENDENCY_INJECTION_LOADING = "Error dependency injection property file load.";
    private static final String ERROR_DEPENDENCY_INJECTION_NO_FILE = "Error. Dependency injection property file not found at address \"%s\".";
    private static final String ERROR_DEPENDENCY_INJECTION_NO_PAIR = "Dependency pair for %s not found.";
    private static final String ERROR_DEPENDENCY_INJECTION_NEW_INSTANCE = "Error during instance creation using dependency injection.";

    private static final String INSTANCE_PATH = "dependency.properties";
    private static DependencyInjection instance;

    private final Properties dependencies = new Properties();

    private final Map<Class<?>, Object> instances = new HashMap<>();

    private DependencyInjection() {
        loadDependencies();
    }

    public static DependencyInjection getInstance() {
        if (instance == null)
            instance = new DependencyInjection();
        return instance;
    }

    private void loadDependencies() {
        File file = FileUtils.getFileFromResource(INSTANCE_PATH);
        if (file.exists()) {
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                dependencies.load(fileInputStream);
            } catch (IOException e) {
                throw new RuntimeException(ERROR_DEPENDENCY_INJECTION_LOADING, e);
            }
        } else {
            throw new IllegalStateException(String.format(ERROR_DEPENDENCY_INJECTION_NO_FILE, file.getAbsolutePath()));
        }
    }

    public <T> T getSingletonObject(Class<T> type) {
        Class<?> cl;
        try {
            if (dependencies.containsKey(type.getName())) {
                cl = Class.forName(dependencies.getProperty(type.getName()));
            } else {
                if (type.isInterface()) {
                    throw new IllegalStateException("There is no pair for interface " + type.getName());
                }
                cl = Class.forName(type.getName());
            }
            if (!instances.containsKey(cl)) {
                addNewObject(cl);
            }
            //noinspection unchecked
            return (T) instances.get(cl);
        } catch (ClassNotFoundException e) {
            logger.error(ERROR_DEPENDENCY_INJECTION_NO_PAIR, e);
            return null;
        } catch (InstantiationException | IllegalAccessException e) {
            logger.error(ERROR_DEPENDENCY_INJECTION_NEW_INSTANCE, e);
            return null;
        } catch (IllegalArgumentException e) {
            logger.error(ERROR_ILLEGAL_ARGUMENT, e);
            return null;
        } catch (InvocationTargetException e) {
            logger.error(ERROR_INVOKED_METHOD, e);
            return null;
        }
    }

    private void addNewObject(Class<?> cl) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor<?>[] constr = cl.getDeclaredConstructors();
        constr[0].setAccessible(true);
        Object result = constr[0].newInstance();
        constr[0].setAccessible(false);
        instances.put(cl, result);
    }
}

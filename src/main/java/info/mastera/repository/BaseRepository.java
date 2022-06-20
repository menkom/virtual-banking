package info.mastera.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import info.mastera.model.BaseEntity;
import info.mastera.repository.api.IBaseRepository;
import info.mastera.util.ClassUtils;
import info.mastera.util.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class BaseRepository<T extends BaseEntity> implements IBaseRepository<T> {

    private final static Logger logger = Logger.getLogger(BaseRepository.class);

    private Long currentId = 0L;

    @SuppressWarnings("unchecked")
    private Class<T> getClazz() {
        return (Class<T>) ClassUtils.getGenericParameter(getClass());
    }

    private Map<String, T> values = new HashMap<>();

    protected Map<String, T> getValues() {
        return values;
    }

    private String getFilename() {
        return getClass().getSimpleName();
    }

    private String getFilePath() {
        return "/db/";
    }

    private String getFile() {
        return new File("").getAbsolutePath() + getFilePath() + getFilename() + ".json";
    }

    @Override
    public void importData() {
        logger.info("Loading data for " + getClazz());
        try {
            ObjectMapper mapper = new ObjectMapper();
            values = FileUtils.isFileExist(getFile())
                    ? mapper.readerForMapOf(getClazz()).readValue(Paths.get(getFile()).toFile())
                    : new HashMap<>();
//            values.entrySet().forEach(System.out::println);
            currentId = values.keySet()
                    .stream()
                    .map(Long::valueOf)
                    .max(Long::compareTo)
                    .orElse(0L);
        } catch (IOException ex) {
            throw new RuntimeException(getClass().getSimpleName() + " data was not loaded", ex);
        }
    }

    @Override
    public void saveData() {
        logger.info("Saving data for " + getClazz());
        try {
            ObjectMapper mapper = new ObjectMapper();
            FileUtils.createIfNotExists(getFile());
            mapper.writeValue(Paths.get(getFile()).toFile(), values);
        } catch (IOException ex) {
            throw new RuntimeException(getClass().getSimpleName() + " data was not saved");
        }
    }

    @Override
    public Collection<T> getAll() {
        return values.values();
    }

    @Override
    public Optional<T> findById(String id) {
        return Optional.ofNullable(values.get(id));
    }

    @Override
    public boolean save(T entity) {
        logger.info("Saving entity " + getClazz());
        if (entity == null) {
            System.out.println("Nothing to save");
            return false;
        }
        String id;
        if (entity.getId() == null) {
            id = (++currentId).toString();
        } else {
            id = entity.getId();
        }
        //noinspection unchecked
        values.put(id, (T) entity.setId(id));
        return true;
    }

    @Override
    public boolean delete(String id) {
        logger.info("Deleting entity " + getClazz() + " with id=" + id);
        if (id == null || id.isEmpty()) {
            System.out.println("Nothing to delete");
        }
        if (values.containsKey(id)) {
            var value = values.get(id);
            //noinspection unchecked
            values.put(id, (T) value.setActive(false));
            return true;
        } else {
            System.out.println("No entity with id=" + id);
            return false;
        }
    }
}

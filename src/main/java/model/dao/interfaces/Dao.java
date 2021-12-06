package model.dao.interfaces;

import java.util.Optional;

public interface Dao<Entity, Key> {

    boolean create(Entity t);

    Optional<Entity> get(Key id);
}

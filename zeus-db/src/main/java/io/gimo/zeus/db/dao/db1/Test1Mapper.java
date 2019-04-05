package io.gimo.zeus.db.dao.db1;


import io.gimo.zeus.entity.model.db1.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Test1Mapper {
    List<User> test();
}

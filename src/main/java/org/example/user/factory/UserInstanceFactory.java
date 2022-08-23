package org.example.user.factory;

import org.example.Interface.UserService;
import org.example.service.InDatabaseService;
import org.example.service.InMemoryService;

import java.util.HashMap;
import java.util.Map;

public class UserInstanceFactory {
    private static Map<PersistenceType, UserService > instanceMap = new HashMap<>();

    private static final PersistenceType persistenceType = PersistenceType.valueOf(System.getProperty("persistenceImplementation", PersistenceType.DB.name()));

    public static UserService getUserService() {
        UserService userService = instanceMap.get(persistenceType);
        if(userService == null) {
            synchronized (UserInstanceFactory.class) {
                userService = instanceMap.get(persistenceType);
                if(userService == null) {
                    switch (persistenceType) {
                        case IN_MEMORY:
                            userService = new InMemoryService();
                            instanceMap.put(persistenceType, new InMemoryService());
                            break;
                        case DB:
                            userService = new InDatabaseService();
                            instanceMap.put(persistenceType, new InDatabaseService());
                            break;
                    }
                }
            }
        }
        return userService;
    }
}

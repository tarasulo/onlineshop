package internet.shop.lib;

import internet.shop.Main;
import internet.shop.service.impl.BucketServiceImpl;
import internet.shop.service.impl.ItemServiceImpl;
import internet.shop.service.impl.OrderServiceImpl;
import internet.shop.service.impl.UserServiceImpl;

import java.lang.reflect.Field;

public class Injector {
    private static void inject(Field[] fields) throws IllegalAccessException {
        for (Field field : fields) {
            if (field.getDeclaredAnnotation(Inject.class) != null) {
                field.setAccessible(true);
                field.set(null, AnnotatedClassMap.getImplementation(field.getType()));
            }
        }
    }

    public static void injectDependency() throws IllegalAccessException {
        inject(ItemServiceImpl.class.getDeclaredFields());
        inject(BucketServiceImpl.class.getDeclaredFields());
        inject(OrderServiceImpl.class.getDeclaredFields());
        inject(UserServiceImpl.class.getDeclaredFields());
        inject(Main.class.getDeclaredFields());
    }
}

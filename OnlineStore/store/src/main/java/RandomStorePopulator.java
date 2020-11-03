import com.github.javafaker.Faker;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class RandomStorePopulator {

    public ArrayList<Product> populateStore(){
        Faker faker = new Faker();
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < 21; i++){

            Category myCategory = null;
            try{
                Class categoryClass = Class.forName(Category.class.getName());
                Class[] params = {String.class};
                myCategory = (Category) categoryClass.getConstructor(params).newInstance(faker.commerce().department());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
            Product product = new Product.Builder()
                    .withName(faker.commerce().productName())
                    .withCategory(myCategory)
                    //.withCategory(new Category(faker.commerce().department()))
                    .withPrice(Double.valueOf(faker.commerce().price()))
                    .withQuantity(faker.number().numberBetween(0,30))
                    .withRating(faker.number().numberBetween(0, 11))
                    .build();
            products.add(product);
        }
        return products;
    }

}

import com.github.javafaker.Faker;

import java.util.ArrayList;

public class RandomStorePopulator {

    public ArrayList<Product> populateStore(){
        Faker faker = new Faker();
        ArrayList<Product> products = new ArrayList<>();
        for (int i = 0; i < 21; i++){
            Product product = new Product.Builder()
                    .withName(faker.commerce().productName())
                    .withCategory(new Category(faker.commerce().department()))
                    .withPrice(Double.valueOf(faker.commerce().price()))
                    .withQuantity(faker.number().numberBetween(0,30))
                    .withRating(faker.number().numberBetween(0, 11))
                    .build();
            products.add(product);
        }
        return products;
    }


    /*public static void main(String[] args) {
        Faker faker = new Faker();
        String randomPrice = faker.commerce().price();
        String randomProductName = faker.commerce().productName();
        String randomDepartment = faker.commerce().department();
        String randomPromotionCode = faker.commerce().promotionCode();
        int randomRating = faker.number().numberBetween(0, 11);
        int randomQuantity = faker.number().numberBetween(0,30);
        System.out.println("Product Name: " + randomProductName + ", Random Price: " +
                randomPrice + ", Department: " + randomDepartment + ", Promotion Code: " + randomPromotionCode
        + ", Rating of product: " + randomRating + ", Number of Products: " + randomQuantity);
    }*/
}

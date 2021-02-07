import com.github.javafaker.Faker;
import dao.builder.QueryBuilder;
import dao.commonDAO.CommonDAO;
import dao.commonDAO.products.CategoriesDAO;
import org.reflections.Reflections;
import product.Category;
import product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RandomStorePopulator {

    List<String> nameOfCategoryClasses = new ArrayList<>();
    Reflections reflections = new Reflections("categories");
    Set<Class<? extends Category>> subClassesForCategory = reflections.getSubTypesOf(Category.class);
    Faker faker = new Faker();
    int le = subClassesForCategory.size();
    Store store = new Store();

    public List<Category> categories = new ArrayList<>();
    CommonDAO actions = new CommonDAO();
    CategoriesDAO categoriesDAO = new CategoriesDAO();


    public List<Category> getAllCategoriesFromDB(){
        categories = categoriesDAO.loadCategories(QueryBuilder.getAllCategories());
        return categories;
    }


    public List<String> getNameOfCategoryClasses(){
        for (int j = 0; j < le; j++){
            nameOfCategoryClasses.add(subClassesForCategory.parallelStream().collect(Collectors.toList())
                    .get(j).getName());
        }
        return nameOfCategoryClasses;
    }

    public List<Product> populateStore(int quantity){
        List<Product> products = new ArrayList<>();
        /*if (quantity > store.getCapacity()){
            System.out.println("The store capacity is less than quantity of products, which you try to generate");
        } else {*/
            String productName = "";
            String className = "";
            for (int i = 0; i < quantity; i++) {
                int randomInt = (int) (Math.random() * le);
                className = getNameOfCategoryClasses().get(randomInt);
                productName = populateProductNameByCategoryName(className);
                Product product = null;
                try {
                    product = new Product.Builder()
                            .withName(productName)
                            .withCategory((Category) Class.forName(getNameOfCategoryClasses().get(randomInt))
                                    .newInstance())
                            .withPrice(Double.valueOf(faker.commerce().price()))
                            .withQuantity(faker.number().numberBetween(0, 30))
                            .withRating(faker.number().numberBetween(0, 11))
                            .build();
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                products.add(product);
            }
        /*}*/
        return products;
    }

    public List<Product> populateStoreFromDBCategory(int quantity){
        List<Product> products = new ArrayList<>();
        String productName = "";
        String categoryName = "";
        Category category = null;
        int categoriesLength = getAllCategoriesFromDB().size();
        Product product = null;
        for (int i = 0; i < quantity; i++) {
            int randomInt = (int) (Math.random() * categoriesLength);
            category = getAllCategoriesFromDB().get(randomInt);
            categoryName = category.getName();
            productName = populateProductNameByCategoryName(categoryName);
            product = new Product.Builder()
                    .withName(productName)
                    .withCategory(category)
                    .withPrice(Double.valueOf(faker.commerce().price()))
                    .withQuantity(faker.number().numberBetween(0, 30))
                    .withRating(faker.number().numberBetween(0, 11))
                    .build();
            products.add(product);
        }
        return products;
    }

    public String populateProductNameByCategoryName(String categoryName){
        categoryName = categoryName.trim().toLowerCase();
        String productName = "";
        if (categoryName.equals("fruit")){
            productName = faker.food().fruit();
        } else if (categoryName.equals("spice")){
            productName = faker.food().spice();
        } else if (categoryName.equals("vegetable")){
            productName = faker.food().vegetable();
        }
        return productName;
    }
}

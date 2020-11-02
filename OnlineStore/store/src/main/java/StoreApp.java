import java.util.ArrayList;

public class StoreApp {
    public static void main(String[] args) {
        RandomStorePopulator populator = new RandomStorePopulator();
        ArrayList<Product> products = populator.populateStore();
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));
        }

        System.out.println("\n sortByPriceAscending ********************************\n");
        Store store = new Store(products);
        ArrayList<Product> sortingByPrice = store.sortByPriceAscending();
        for (int i = 0; i < sortingByPrice.size(); i++) {
            System.out.println(sortingByPrice.get(i));
        }

        System.out.println("\n sortingByPriceDesc ********************************\n");
        ArrayList<Product> sortingByPriceDesc = store.sortByPriceDescending();
        for (int i = 0; i < sortingByPriceDesc.size(); i++) {
            System.out.println(sortingByPriceDesc.get(i));
        }

        System.out.println("\n Toys category ********************************\n");
        ArrayList<Product> sectCategory = store.checkCategory(new Category("Toys"));
        for (int i = 0; i < sectCategory.size(); i++) {
            System.out.println(sectCategory.get(i));
        }
    }
}

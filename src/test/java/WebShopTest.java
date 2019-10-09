import no.kristiania.jdbc.ProductDao;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class WebShopTest {
    @Test
    void shouldRetrieveStoredProduct(){
        ProductDao dao = new ProductDao();
        String productName = pickOne(new String[]{"Apples","Bananas", "Pelee"});
        dao.insertProduct(productName);
        assertThat(dao.listAll()).contains(productName);
    }

    private String pickOne(String[] strings) {
        return strings[new Random().nextInt(strings.length)];
    }
}

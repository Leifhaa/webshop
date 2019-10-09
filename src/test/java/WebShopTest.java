import no.kristiania.jdbc.ProductDao;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class WebShopTest {
    @Test
    void shouldRetrieveStoredProduct(){
        ProductDao dao = new ProductDao(new JdbcDataSource());
        String productName = pickOne(new String[]{"Apples","Bananas", "Pelee"});
        dao.insertProduct(productName);
        assertThat(dao.listAll()).contains(productName);
    }

    private String pickOne(String[] strings) {
        return strings[new Random().nextInt(strings.length)];
    }
}

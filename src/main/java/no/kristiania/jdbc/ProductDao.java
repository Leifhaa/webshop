package no.kristiania.jdbc;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ProductDao {
    private DataSource dataSource;

    public ProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insertProduct(String productName) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(
                    "insert into products(name) values(?)");
            statement.setString(1, productName);
            statement.executeUpdate();
        }
    }

    public List<String> listAll() throws SQLException {
        try (Connection connection = dataSource.getConnection()){
            try (PreparedStatement statement = connection.prepareStatement (
                    "select * from products"))
            {
                try (ResultSet resultSet = statement.executeQuery()) {
                    List<String> result = new ArrayList<>();
                    while (resultSet.next()){
                        result.add(resultSet.getString("name"));
                    }

                    return result;
                }
            }
        }
    }

    public static void main(String[] args) throws SQLException, IOException {
        System.out.println("Enter a product name:");
        String productName = new Scanner(System.in).nextLine();

        Properties properties = new Properties();
        properties.load(new FileReader("webshop.properties"));


        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/webshop");
        dataSource.setUser("webshop");
        dataSource.setPassword("NWS9Sta;U]9;4r");
        ProductDao productDao = new ProductDao(dataSource);
        productDao.insertProduct(productName);

        System.out.println(productDao.listAll());
    }
}

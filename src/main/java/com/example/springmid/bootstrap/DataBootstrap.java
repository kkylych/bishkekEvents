//package com.example.springmid.bootstrap;
//
//import com.example.springmid.entities.Booking;
//import com.example.springmid.entities.Product;
//import com.example.springmid.entities.User;
//import com.example.springmid.repositories.BookingRepository;
//import com.example.springmid.repositories.ProductRepository;
//import com.example.springmid.repositories.UserRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import javax.xml.transform.sax.SAXResult;
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//
//@Component
//public class DataBootstrap implements CommandLineRunner {
//    @Override
//    public void run(String... args) throws Exception {
//
//    }
//    private final UserRepository userRepository;
//    private final ProductRepository productRepository;
//    private final BookingRepository orderRepository;
//    @Autowired
//    public DataBootstrap(UserRepository userRepository, ProductRepository productRepository, BookingRepository orderRepository) {
//        this.userRepository = userRepository;
//        this.productRepository = productRepository;
//        this.orderRepository = orderRepository;
//    }
//
//    @Transactional
//    public void bootstrapData(){
//        try{
//            loadUsersFromCSV();
//            loadProductsFromCSV();
//            loadOdersFromCSV();
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void loadUsersFromCSV() throws IOException{
//        BufferedReader reader = new BufferedReader(new FileReader("users.csv"));
//        String line;
//        while ((line = reader.readLine()) != null ) {
//            String[] data = line.split(",");
//            User user = new User();
//            user.setUsername(data[0]);
//            user.setEmail(data[1]);
//            userRepository.save(user);
//        }
//        reader.close();
//    }
//    private void loadProductsFromCSV() throws IOException {
//        BufferedReader reader = new BufferedReader(new FileReader("products.csv"));
//        String line;
//        while ((line = reader.readLine()) != null ) {
//            String[] data = line.split(",");
//            Product product = new Product();
//            product.setName(data[0]);
//            product.setPrice(Double.parseDouble(data[1]));
//            product.setDescription(data[2]);
//            productRepository.save(product);
//        }
//        reader.close();
//    }
//
//    private void loadOdersFromCSV() throws IOException{
//        BufferedReader reader = new BufferedReader(new FileReader("products.csv"));
//        String line;
//        while ((line = reader.readLine()) != null ) {
//            String[] data = line.split(",");
//            Booking order = new Booking();
//            order.setOrderId(Long.parseLong(data[0]));
//            order.setQuantity(Integer.parseInt(data[1]));
//
//            User user = userRepository.findById(Long.parseLong(data[2])).orElse(null); // Assuming the third value is the user ID
//            Product product = productRepository.findById(Long.parseLong(data[3])).orElse(null); // Assuming the fourth value is the product ID
//
//            order.setUser(user);
//
//            orderRepository.save(order);
//        }
//        reader.close();
//    }
//
//}

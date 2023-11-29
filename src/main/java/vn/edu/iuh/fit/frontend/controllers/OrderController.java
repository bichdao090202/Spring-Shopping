package vn.edu.iuh.fit.frontend.controllers;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.iuh.fit.backend.models.*;
import vn.edu.iuh.fit.backend.repositories.EmployeeRepository;
import vn.edu.iuh.fit.backend.repositories.OrderDetailRepository;
import vn.edu.iuh.fit.backend.repositories.OrderRepository;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/pay")
    public String pay(HttpSession session, Model model){
        Map<Product,Integer> sellProducts = new HashMap<Product,Integer>();
        sellProducts = (Map<Product, Integer>) session.getAttribute("mapProduct");

        Customer customer = (Customer) session.getAttribute("customer");

        Order order = new Order(employeeRepository.findById(1),customer);
        orderRepository.save(order);

        for (Map.Entry<Product,Integer> entry : sellProducts.entrySet()) {
            OrderDetail orderDetail = new OrderDetail(
                    entry.getValue(), entry.getKey().getProductPrices().get(0).getPrice(),  "",
                    order, entry.getKey());
            orderDetailRepository.save(orderDetail);
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        session.removeAttribute("mapProduct");
        session.removeAttribute("cart");
        return "client/shopping/PaySuccess";
    }

}

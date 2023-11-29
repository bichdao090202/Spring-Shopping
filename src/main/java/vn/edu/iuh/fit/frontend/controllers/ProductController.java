package vn.edu.iuh.fit.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.backend.dtos.CartItem;
import vn.edu.iuh.fit.backend.models.Product;
import vn.edu.iuh.fit.backend.repositories.ProductRepository;
import vn.edu.iuh.fit.backend.services.ProductServices;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("")
public class ProductController {
    @Autowired
    private ProductServices productServices;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public String showProductListPaging(HttpSession session, Model model,
                                        @RequestParam("page") Optional<Integer> page,
                                        @RequestParam("size") Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Product> productPage = productServices.findPaginated(currentPage - 1,
                pageSize, "name", "asc");

        model.addAttribute("productPage", productPage);

        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "client/shopping/listing";
    }

    @GetMapping("/home")
    public String showHome(HttpSession session, Model model){
        int currentPage = 1;
        int pageSize = 10;

        Page<Product> productPage = productServices.findPaginated(currentPage - 1,
                pageSize, "name", "asc");

        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
             model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("productPage", productPage);
        return "client/shopping/listing";
    }



    @GetMapping("/buy/{id}")
    public String buy(Model model, HttpSession session, @PathVariable("id") Long id){
        Object obj = session.getAttribute("cart");
        Map<Long, CartItem> cartItems = null;
        if (obj==null)
            cartItems = new HashMap<>();
        else
            cartItems = (HashMap<Long, CartItem>)obj;
        int quan = cartItems.get(id)==null?1:cartItems.get(id).getQuan()+1;
        CartItem item = new CartItem(id,quan);
        cartItems.put(id,item);
        session.setAttribute("cart",cartItems);

        int currentPage = 1;
        int pageSize = 10;
        Page<Product> productPage = productServices.findPaginated(currentPage - 1,
                pageSize, "name", "asc");
        model.addAttribute("productPage", productPage);
        int totalPages = productPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "client/shopping/listing";
    }


    @GetMapping("/cart")
    public String showCheckOut(Model model, HttpSession session){
        Object obj = session.getAttribute("cart");
        if (obj==null)
            return "client/shopping/EmptyCart";
        Map<Long, CartItem> cart = (HashMap<Long, CartItem>)obj;
        Map<Product,Integer> sellProducts = new HashMap<Product,Integer>();

        double totalPrice = 0;
        for(Map.Entry<Long,CartItem> entry : cart.entrySet()){
            Product p = productRepository.findById(entry.getKey()).get();
            sellProducts.put(p,entry.getValue().getQuan());
            double price = (p.getProductPrices().get(0).getPrice());
            double roundedNumber = Math.round(price * 100.0) / 100.0;
            totalPrice+=(roundedNumber)*entry.getValue().getQuan();
        }
        model.addAttribute("total",totalPrice);
        model.addAttribute("cart",sellProducts);
        session.setAttribute("mapProduct",sellProducts);
        return "client/shopping/cart";
    }


}

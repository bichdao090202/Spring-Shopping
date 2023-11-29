package vn.edu.iuh.fit.frontend.controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.backend.models.Customer;
import vn.edu.iuh.fit.backend.repositories.CustomerRepository;

@Controller
public class CustomerController {
    @Autowired
    private CustomerRepository repository;

    @GetMapping("/chooseCustomer")
    public String chooseCustomerType(){
        return "client/customer/chooseCustomerType";
    }

    @RequestMapping("/findCusByEmail")
    public String findCusByEmail(Model model, HttpSession session, @RequestParam (value = "email", required = false) String email){
        Customer customer = repository.findByEmail(email);
        if (customer!=null) {
            model.addAttribute("notice","This is customer's information");
            session.setAttribute("customer",customer);
            model.addAttribute("customer",customer);
            return "client/customer/customerInfo";
        }
        else
            return "client/customer/NotFount";
    }

    @GetMapping("/formNewCustomer")
    public String formNewCustomer(){
        return "client/customer/customerForm";
    }

    @RequestMapping("/signUp")
    public String signUp(Model model, HttpSession session,
            @RequestParam (value = "name", required = false) String name,
            @RequestParam (value = "email", required = false) String email,
            @RequestParam (value = "phone", required = false) String phone,
            @RequestParam (value = "address", required = false) String address
    ){
        Customer customer = new Customer(name,email,phone,address);
        Customer customerTmp = repository.findByEmail(email);
        if (customerTmp!=null) {
            customer = customerTmp;
            model.addAttribute("notice","User email exist, This is customer's information!");
        } else{
            repository.save(customer);
            model.addAttribute("notice","Sign up success. This is customer's information");
        }
        session.setAttribute("customer",customer);
        model.addAttribute("customer",customer);
        return "client/customer/customerInfo";
    }

}

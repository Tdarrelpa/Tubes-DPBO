package Tubes.DPBO.Proyek.Java.controller;

import Tubes.DPBO.Proyek.Java.Entity.Home;
import Tubes.DPBO.Proyek.Java.Repository.HomeRepository;

@Controller
public class HomeController 
{
    @Autowired
    private HomeRepository homeRepository;
    
    @RequestMapping("/")
    public String Home() {
        return "index";
    }

    @GetMapping("/about")
    public String About() {
        return "about";
    }

    @GetMapping("/contact")
    public String Contact() {
        return "contact";
    }

    @GetMapping("/login")
    public String Login() {
        return "login";
    }

    @GetMapping("/logout")
    public String Logout() {
        // Add code to handle logout process
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String Register() {
        return "register";
    }

    @PostMapping("/register/submit")
    public String RegisterSubmit(@ModelAttribute User user) {
        // Add code to handle registration process
        return "register";
    }
    
    // Add more methods for other routes and functionalities
    // For example:
    @GetMapping("/dashboard")
    public String Dashboard() {
        return "Dashboard";
    }
}

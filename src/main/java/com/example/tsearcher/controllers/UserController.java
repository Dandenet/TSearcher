package com.example.tsearcher.controllers;

import com.example.tsearcher.models.Subject;
import com.example.tsearcher.models.User;
import com.example.tsearcher.repository.SubjectRepository;
import com.example.tsearcher.repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @GetMapping()
    public String index(Model vars) {

        vars.addAttribute("alluserlist", userRepository.findAll());
        vars.addAttribute("allsubjectlist", subjectRepository.findAll());

        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model vars) {

        vars.addAttribute("alluserlist", userRepository.findAll());
        vars.addAttribute("allsubjectlist", subjectRepository.findAll());

        return "admin";
    }

    @RequestMapping(method = RequestMethod.POST, value = "user/createnew")
    public String create(@ModelAttribute("user") User user, Model vars) {


        userRepository.save(user);
        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.GET, value = "user/create")
    public String signup(Model vars) {

        User user = new User();

        vars.addAttribute("user", user);
        return "signup";
    }

    @RequestMapping(method = RequestMethod.GET, value = "user/{id}")
    public String edit(@PathVariable("id") Long id, Model vars) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty())
            return "notfound";

        vars.addAttribute("user", user.get());
        return "edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "user/{id}")
    public ResponseEntity<?> update(@ModelAttribute("user") User user, BindingResult bindingResult,
                                 @PathVariable("id") Long id) {
        if (bindingResult.hasErrors())
            return ResponseEntity.badRequest().body(new String("Error"));

        userRepository.save(user);

        return ResponseEntity.ok(new String("ok"));
    }

    @RequestMapping(method = RequestMethod.POST, value = "user/{id}/delete")
    public String delete(@ModelAttribute("user") User user, @PathVariable("id") Long id)
    {
        userRepository.deleteById(id);
        return "redirect:/";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    public Object getUsersBySubject(@PathVariable("id") Long subjectId)
    {
        List<User> userList = userRepository.findAll();

        List<User> resUserList = new ArrayList<>();

        for (User user: userList) {
            for (Subject subject: user.getSubjects()) {

                if (subject.getId() == subjectId) {
                    resUserList.add(user);
                    break;
                }
            }

        }



        return  resUserList;
    }

//    @GetMapping("/user/{username}")
//	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
//    public Iterable<Image> getUserCart(@PathVariable String username) {
//        return cartRepository.findByUserUsername(username);
//    }
//
////    @PostMapping("/user/{username}")
////	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
////    public Image updateCart(@RequestBody UpdateCartRequest cartRequest, @PathVariable String username) {
////        User user = userRepository.findByUsername(username)
////                .orElseThrow( () -> new RuntimeException( "Not Found User with username " + username ));
////
////        Product product = productRepository.findById(cartRequest.getId())
////                .orElseThrow( () -> new RuntimeException("Not Found Product with id " + cartRequest.getId()));
////
////        return cartRepository.save(new Image(user, product, cartRequest.getQuantity()));
////    }
}
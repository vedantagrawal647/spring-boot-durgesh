package com.durgesh.durgesh7_SmartContactManager.controller;


import com.durgesh.durgesh7_SmartContactManager.dao.ContactRepository;
import com.durgesh.durgesh7_SmartContactManager.dao.MyOrderRepository;
import com.durgesh.durgesh7_SmartContactManager.dao.UserDataRepository;
import com.durgesh.durgesh7_SmartContactManager.entities.Contact;
import com.durgesh.durgesh7_SmartContactManager.entities.MyOrder;
import com.durgesh.durgesh7_SmartContactManager.entities.UserData;
import com.durgesh.durgesh7_SmartContactManager.helper.Message;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.razorpay.*;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.nio.file.*;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class userController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private MyOrderRepository myOrderRepository;

    //it automatically run on each url feching
    //method for adding common data to response
    @ModelAttribute
    public void addCommonData(Model model,Principal principal){
        String username = principal.getName();

        //get the user using username
        UserData userData = userDataRepository.getUserDataByUserDataName(username);

        model.addAttribute("userData",userData);

    }


    //dashboard home
    @RequestMapping("/index")
    public String dashboard(Model model, Principal principal){

        return "normal/user_dashboard";
    }


    //open add form handler
    @GetMapping("/add-contact")
    public String openAddContactForm(Model model){

        model.addAttribute("title","User DashBoard");
        model.addAttribute("contact",new Contact());

        return "normal/add_contact_form";
    }


    //processing add contact form
    @PostMapping("/process-contact")
    public String processContact(@ModelAttribute Contact contact,
                                 @RequestParam("profileImage") MultipartFile file,
                                 Principal principal,
                                 HttpSession session)
    {
        try{
            String name = principal.getName();
            UserData userData = this.userDataRepository.getUserDataByUserDataName(name);

            //processing and uploading file
            if(file.isEmpty())
            {
                //if the file is empty then try our message
                System.out.println("File is not uploaded");
                contact.setImage("default.png");
            }
            else {
                // upload the file to folder and update the name to contact
                contact.setImage(file.getOriginalFilename());

                File saveFile = new ClassPathResource("static/img").getFile();

               Path path =  Paths.get(saveFile.getAbsolutePath()  +  File.separator + file.getOriginalFilename());

                //Files.copy(inputStream,target,options)
                Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("Image is uploading");
            }
            //end of file processing


            contact.setUserData(userData);
            userData.getContacts().add(contact);
            this.userDataRepository.save(userData);

            System.out.println("Added to databases ");

            // message sucess ....
            session.setAttribute("message",new Message("Your Contact is added !! Add more ...","success"));
        }
        catch (Exception e) {
            System.out.println("Error "+e.getMessage());
            e.printStackTrace();

            // error message ..
            session.setAttribute("message",new Message("Some thing went wrong !! Try again ...","danger"));
        }

        return "normal/add_contact_form";

    }

    //show contact handler
    //per Page = 5(n)
    //current page = 0(current)
    @GetMapping("/show-contacts/{page}")
    public String showContacts(@PathVariable("page") int page ,Model model,Principal principal)
    {
        model.addAttribute("title","Show User Contact");

        //contact ki list  bhejni ha

        String userName = principal.getName();
        UserData userData = this.userDataRepository.getUserDataByUserDataName(userName);

        //current page  - page
        //Contact Per page - 5
        Pageable pageable = PageRequest.of(page,5);

        Page<Contact> contacts = this.contactRepository.findContactByUserData(userData.getId(),pageable);

        model.addAttribute("contacts",contacts);
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPages",contacts.getTotalPages());

        return "normal/show_contacts";
    }

    //showing particular contact detail
    @GetMapping("/{cId}/contact")
    public String showContactDetail(@PathVariable("cId") Integer cId,Model model,Principal principal){
        Optional<Contact> contactOptional = this.contactRepository.findById(cId);
        Contact contact = contactOptional.get();

        //
        String userName = principal.getName();
        UserData userData = this.userDataRepository.getUserDataByUserDataName(userName);

        if(userData.getId() == contact.getUserData().getId()){
            model.addAttribute("contact",contact);
            model.addAttribute("title",contact.getName());
        }

        return "normal/contact_detail";
    }


    //delete contact handler
    @GetMapping("/delete/{cid}")
    public String deleteContact(@PathVariable("cid") Integer cId,Model model,Principal principal,HttpSession session){

        Optional<Contact> contactOptional = this.contactRepository.findById(cId);
        Contact contact = contactOptional.get();

        String userName = principal.getName();
        UserData userData = this.userDataRepository.getUserDataByUserDataName(userName);

        if(userData.getId() == contact.getUserData().getId()){

            //delete old photo
            //remove img

            //unlink userData from Contact
            //contact.setUserData(null);
            // this.contactRepository.delete(contact);

            //Userdata ma equal method banana uska bases pa match hoka delete hoga
            userData.getContacts().remove(contact);

            this.userDataRepository.save(userData);


            session.setAttribute("message",new Message("contact deleted successfully","success"));
        }
        else {
            session.setAttribute("message",new Message("some error not valid user or unauthorize user","danger"));
        }

        return "redirect:/user/show-contacts/0";
    }

    //open update form handler
    @PostMapping("/update-contact/{cId}")
    public String updateForm(@PathVariable("cId") int cId, Model model){

        model.addAttribute("title","Update Contact");

        Contact contact = this.contactRepository.findById(cId).get();

        model.addAttribute("contact",contact);


        return "normal/update_form";
    }


    //update  contact handler
    @PostMapping("/process-update")
    public String updateHandler(@ModelAttribute Contact contact,
                                @RequestParam("profileImage") MultipartFile file,
                                Model model,
                                HttpSession session,
                                Principal principal)
    {

        try{

            //old contact details
            Contact oldContactDetail = this.contactRepository.findById(contact.getcId()).get();

            if(!file.isEmpty())
            {

                //delete old photo
                File deleteFile = new ClassPathResource("static/img").getFile();
                File file1 = new File(deleteFile,oldContactDetail.getImage());
                file1.delete();


                //update new photo
                File saveFile = new ClassPathResource("static/img").getFile();
                Path path =  Paths.get(saveFile.getAbsolutePath()  +  File.separator + file.getOriginalFilename());
                Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);

                contact.setImage(file.getOriginalFilename());
            }
            else {
                contact.setImage(oldContactDetail.getImage());
            }

            UserData userData = userDataRepository.getUserDataByUserDataName(principal.getName());

            contact.setUserData(userData);

            this.contactRepository.save(contact);

            session.setAttribute("message",new Message("Your Contact is updated","success"));

        }catch(Exception e)
        {
                e.printStackTrace();
        }

        return "redirect:/user/"+contact.getcId()+"/contact";

    }


    //your profile handler
    @GetMapping("/profile")
    public String yourProfile(Model model){
        model.addAttribute("title","Profile Page");
        return "normal/profile";
    }


    //open settings handler
    @GetMapping("/settings")
    public String openSettings(){
        return "normal/settings";
    }

    //change password
    @PostMapping("/change-password")
    public  String changePassword(@RequestParam("oldPassword") String oldPassword,
                                  @RequestParam("newPassword") String newPassword,
                                  Principal principal,
                                  HttpSession session)
    {
        String userName = principal.getName();
        UserData currentUser = this.userDataRepository.getUserDataByUserDataName(userName);

        if(this.bCryptPasswordEncoder.matches(oldPassword, currentUser.getPassword()))
        {
            //change password
            currentUser.setPassword(this.bCryptPasswordEncoder.encode(newPassword));
            this.userDataRepository.save(currentUser);
            session.setAttribute("message",new Message("Your password is change successfully","success"));
        }
        else {
            //error
            session.setAttribute("message",new Message("Please Enter correct old password","danger"));
        }
        return "redirect:/user/index";
    }

    //  creating order for payment
    @PostMapping("/create_order")
    @ResponseBody
    public String createOrder(@RequestBody Map<String,Object> data,Principal principal) throws Exception {
       // System.out.println("Hey order function executive");
        // System.out.println(data);

        int amt = Integer.parseInt(data.get("amount").toString());

        RazorpayClient client = new RazorpayClient("key_id", "key_secret");
        JSONObject options = new JSONObject();
        options.put("amount", amt*100); //muliply with 100 beacuse all the thing done in paisa
        options.put("currency", "INR");
        options.put("receipt", "txn_123456");

        //creating new order
        Order order = client.orders.create(options);
        //if you want you can save this to your database

        MyOrder myOrder = new MyOrder();
        myOrder.setOrderId(order.get("id"));
        myOrder.setAmount(order.get("amount") +"");
        myOrder.setPaymentId(null);
        myOrder.setStatus("created");
        myOrder.setUserData(this.userDataRepository.getUserDataByUserDataName(principal.getName()));
        myOrder.setReceipt(order.get("receipt"));
        this.myOrderRepository.save(myOrder);

        //save order in database

        return order.toString();
    }

    @PostMapping("/update_order")
    public ResponseEntity<?> updateOrder(@RequestBody Map<String,Object> data){

        MyOrder myOrder = this.myOrderRepository.findByOrderId(data.get("order_id").toString());

        myOrder.setPaymentId(data.get("payment_id").toString());
        myOrder.setStatus(data.get("status").toString());
        this.myOrderRepository.save(myOrder);

        return ResponseEntity.ok(Map.of("msg","updated"));
    }

}

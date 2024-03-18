package bitlab.springtask3_2.controllers;

import bitlab.springtask3_2.entities.ApplicationRequest;
import bitlab.springtask3_2.repositories.ApplicationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/main")
public class ApplicationRequestController {
    @Autowired
    ApplicationRequestRepository applicationRequestRepository;


    @GetMapping(value = "/")
    public String getIndex(Model model){
        List<ApplicationRequest> applicationRequests = applicationRequestRepository.findAll();
        model.addAttribute("applications", applicationRequests);
        return "index";
    }

    @GetMapping(value = "/processed")
    public String getProcessedPage(Model model){
        List<ApplicationRequest> applicationRequests = applicationRequestRepository.findAll();
        model.addAttribute("applications", applicationRequests);
        return "processed";
    }

    @GetMapping(value = "/newApplications")
    public String getNewApplications(Model model){
        List<ApplicationRequest> applicationRequests = applicationRequestRepository.findAll();
        model.addAttribute("applications", applicationRequests);
        return "newApplications";
    }

    @GetMapping(value = "/addAppl")
    public String addApplication(Model model){
        List<ApplicationRequest> applicationRequests = applicationRequestRepository.findAll();
        model.addAttribute("applications", applicationRequests);
        return "addApplication";
    }

    @PostMapping(value = "/addAppl")
    public String addApplication(@RequestParam(name = "username") String username,
                                 @RequestParam(name = "course") String course,
                                 @RequestParam(name = "phone") String phone,
                                 @RequestParam(name = "comment") String comment){

        ApplicationRequest applicationRequest = new ApplicationRequest();
        applicationRequest.setUserName(username);
        applicationRequest.setCourseName(course);
        applicationRequest.setPhone(phone);
        applicationRequest.setCommentary(comment);
        applicationRequest.setHandled(false);

        applicationRequestRepository.save(applicationRequest);

        return "redirect:/main/";
    }

    @GetMapping(value = "/details/{id}")
    public String getApplication(@PathVariable(name = "id") Long id, Model model){
        ApplicationRequest applicationRequest = applicationRequestRepository.findAllById(id);
        model.addAttribute("appl", applicationRequest);
        return "details";
    }

    @PostMapping(value = "/updateAppl")
    public String updateApplication(@RequestParam(name = "username") String username,
                                 @RequestParam(name = "course") String course,
                                 @RequestParam(name = "phone") String phone,
                                 @RequestParam(name = "comment") String comment,
                                    @RequestParam(name = "id") Long id){
        ApplicationRequest applicationRequest = new ApplicationRequest();
        applicationRequest.setId(id);
        applicationRequest.setUserName(username);
        applicationRequest.setCourseName(course);
        applicationRequest.setPhone(phone);
        applicationRequest.setCommentary(comment);
        applicationRequest.setHandled(true);

        applicationRequestRepository.save(applicationRequest);

        return "redirect:/main/";
    }

    @PostMapping(value = "/deleteAppl")
    public String deleteApplication(@RequestParam(name = "id") Long id){
        applicationRequestRepository.deleteById(id);

        return "redirect:/main/";
    }

}

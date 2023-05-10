package io.troof.bigpi.emailsenderdatabase.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import io.troof.bigpi.emailsenderdatabase.resource.EmailMessage;
import io.troof.bigpi.emailsenderdatabase.service.impl.EmailSenderServiceImpl;

@Controller
public class EmailController {
	@Autowired
	private ConfigurableEnvironment env;
	
	@Autowired
	private EmailSenderServiceImpl service;
	
	@GetMapping("/connect")
	public String connectUser(String email, String password) {
	    MutablePropertySources propertySources = env.getPropertySources();
	    PropertySource<?> propertySource = propertySources.get("applicationConfig: [classpath:/application.properties]");
	    if (propertySource != null) {
	        Object value = propertySource.getProperty("spring.mail.username");
	        if (value != null) {
	            propertySources.remove("applicationConfig: [classpath:/application.properties]");
	            propertySources.addFirst(new PropertySource<Object>("applicationConfig: [classpath:/application.properties]") {
	                @Override
	                public Object getProperty(String name) {
	                    if ("spring.mail.username".equals(name)) {
	                        return email;
	                    } else if ("spring.mail.password".equals(name)) {
	                        return password;
	                    }
	                    return null;
	                }
	            });
	        }
	    }
	    return "connect";
	}
	
	@GetMapping("/")
    public String viewHomePage(Model model) {
        List<EmailMessage> listemail = service.listAll();
        model.addAttribute("listemail", listemail);
        System.out.print("Get / ");
        return "index";
    }
	
	@RequestMapping(value = "/send", method = RequestMethod.POST)
    public String sendEmail(@ModelAttribute("emailMessage") EmailMessage email) {
        service.sendEmail(email);
        return "redirect:/";
    }
	
	@RequestMapping("/delete/{id}")
    public String deleteEmail(@PathVariable(name = "id") int id) {
        service.delete(id);
        return "redirect:/";
    }
}

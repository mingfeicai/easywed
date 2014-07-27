package com.easywed.controller;

import com.easywed.dao.MemberDao;
import com.easywed.model.Member;
import com.easywed.model.SignupForm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


/**
 * User: ryan
 * Date: 2/7/13
 */
@Controller
public class IndexController {
	@Autowired private MemberDao memberDao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        return new ModelAndView("index", "signupForm", new SignupForm());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Model model, SignupForm signupForm, BindingResult result, RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            return "index";
        }
	    Member member = new Member();
	    member.setName(signupForm.getFirstName());

	    member.setDes(signupForm.getLastName());
	    memberDao.saveOrUpdate(member);
        return "show";

    }

    @RequestMapping(value = "/security-error", method = RequestMethod.GET)
    public String securityError(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("page_error", "You do have have permission to do that!");
        return "redirect:/";
    }
}



package community.locals;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import community.locals.dto.MemberLogin;

@Controller
@RequestMapping("/")
public class HomeContoller {
	
	@GetMapping
	public String home() {
		return "home";
	}
	
	@GetMapping("loginForm")
	public String loginForm(Model model) {
		MemberLogin memberLogin = new MemberLogin();
		model.addAttribute("memberLogin",memberLogin);
		return "loginForm";
	}
	@PostMapping("loginForm")
	public void login(MemberLogin memberLogin) {
		System.out.println(memberLogin);
	}
}

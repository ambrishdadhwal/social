package com.social.user.controllers;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.social.commonutils.ProfileMapper;
import com.social.domain.Profile;
import com.social.presentation.CountryDTO;
import com.social.presentation.ProfileDTO;
import com.social.presentation.ProfileLoginDTO;
import com.social.security.jwt.JwtTokenUtil;
import com.social.service.IUserService;
import com.social.user.restcontrollers.JwtResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/mvc/user")
@RequiredArgsConstructor
public class MVCAuthController
{

	private final IUserService userService;
	
	final JwtTokenUtil jwtTokenUtil;

	@GetMapping("index")
	public String home()
	{
		return "index";
	}

	@GetMapping("/login")
	public String loginForm(Model model)
	{
		ProfileLoginDTO login = new ProfileLoginDTO();
		model.addAttribute("login", login);
		return "login";
	}
	
	@PostMapping("/login/token")
	public String loginForm(@Valid @ModelAttribute("user") ProfileLoginDTO user, BindingResult result, Model model) throws Exception
	{
		Optional<Profile> existingUser = userService.getUser(ProfileMapper.convert(user));

		if (existingUser.isPresent())
		{
			final String token = jwtTokenUtil.generateToken(existingUser.get());
			JwtResponse jwtResponse = new JwtResponse("Bearer ".concat(token));
			model.addAttribute("token", jwtResponse);
		}
		else
		{
			throw new UsernameNotFoundException("User not found");
		}
		return "redirect:/mvc/user/login?success";
	}

	@GetMapping("register")
	public String showRegistrationForm(Model model)
	{
		ProfileDTO user = new ProfileDTO();
		model.addAttribute("user", user);
		return "register";
	}

	@PostMapping("/register/save")
	public String registration(@Valid @ModelAttribute("user") ProfileDTO user, BindingResult result, Model model) throws Exception
	{
		user.setCountry(CountryDTO.INDIA);
		user.setDob(LocalDate.now());
		Optional<Profile> existing = userService.getUserbyEmail(user.getEmail());
		if (existing.isPresent())
		{
			result.rejectValue("email", null, "There is already an account registered with that email");
		}
		if (result.hasErrors())
		{
			model.addAttribute("user", user);
			return "register";
		}
		userService.saveUser(ProfileMapper.convert(user));
		return "redirect:/mvc/user/register?success";
	}

}

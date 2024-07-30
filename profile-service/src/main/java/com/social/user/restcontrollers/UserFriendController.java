package com.social.user.restcontrollers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.social.domain.Profile;
import com.social.presentation.CommonResponse;
import com.social.service.IUserFriendService;
import com.social.service.ProfileException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserFriendController
{

	private final IUserFriendService userFriendService;

	@PostMapping(value = "/send-fr/{toId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CommonResponse<String>> sendFriendRequest(@PathVariable("toId") Long toId, HttpServletRequest httpRequest)
		throws ProfileException
	{
		Profile profile = (Profile)httpRequest.getAttribute("CurrentUser");
		boolean request = userFriendService.updateFriendRequest(profile.getId(), toId, false);
		CommonResponse<String> dto = new CommonResponse<>();
		dto.setData(request ? "Friend Request sent successfully." : "Something went wrong");
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}

	@PutMapping(value = "/update-fr/{toId}/{decision}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CommonResponse<String>> updateFriendRequest(@PathVariable("decision") boolean decision, @PathVariable("toId") Long toId,
		HttpServletRequest httpRequest)
		throws ProfileException
	{
		Profile profile = (Profile)httpRequest.getAttribute("CurrentUser");
		boolean request = userFriendService.updateFriendRequest(profile.getId(), toId, decision);
		CommonResponse<String> dto = new CommonResponse<>();
		dto.setData(request ? "Friend Request sent successfully." : "Something went wrong");
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
}

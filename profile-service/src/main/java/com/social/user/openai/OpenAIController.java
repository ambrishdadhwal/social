package com.social.user.openai;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/gen-ai")
public class OpenAIController
{

	@Value("${openai.model}")
	private String model;

	@Value("${openai.api.host}")
	private String host;

	@Autowired
	private RestTemplate openaiRestTemplate;

	@PostMapping("/search")
	public ChatResponse completion(@RequestBody @Validated ChatRequest request)
	{
		ChatResponse response = openaiRestTemplate.postForObject(host, request, ChatResponse.class);

		if (ObjectUtils.isEmpty(response) || ObjectUtils.isEmpty(response.getChoices()) || response.getChoices().isEmpty())
		{
			return new ChatResponse();
		}
		return response;
	}
}

package com.social.user.openai;

import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.flashvayne.chatgpt.service.ChatgptService;

@RestController
@RequestMapping("/gen-ai")
public class OpenAIController
{

	@Autowired
	private RestTemplate openaiRestTemplate;

	@Value("${spring.ai.openai.api-key}")
	private String openaiApiKey;

	@Value("${spring.ai.openai.chat.options.model}")
	private String chatModelV;

	@Value("${spring.ai.openai.base-url}")
	private String baseURL;

	@Autowired
	private ChatgptService chatgptService;

	/*
	 * not working
	 * */
	@GetMapping("/generate")
	public Map generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message)
	{

		var openAiApi = new OpenAiApi(baseURL, openaiApiKey);
		var openAiChatOptions = OpenAiChatOptions.builder()
			.withModel(chatModelV)
			.withTemperature(0.4f)
			.withMaxTokens(200)
			.build();

		var chatModelAI = new OpenAiChatModel(openAiApi, openAiChatOptions);

		org.springframework.ai.chat.model.ChatResponse response = chatModelAI.call(
			new Prompt("Generate the names of 5 famous pirates."));

		return Map.of("generation", response);
	}

	/*
	 * working
	 * */
	@PostMapping("/search")
	public ChatResponse completion(@RequestBody @Validated ChatRequest request)
	{
		test2();
		ChatResponse response = openaiRestTemplate.postForObject(baseURL, request, ChatResponse.class);

		if (ObjectUtils.isEmpty(response) || ObjectUtils.isEmpty(response.getChoices()) || response.getChoices().isEmpty())
		{
			return new ChatResponse();
		}
		return response;
	}

	public void test2()
	{
		String responseMessage = chatgptService.sendMessage("how are you");
		System.out.print(responseMessage); // I'm doing well, thank you. How about you?
	}

}

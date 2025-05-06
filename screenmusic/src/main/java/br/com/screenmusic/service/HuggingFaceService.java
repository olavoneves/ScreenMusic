package br.com.screenmusic.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class HuggingFaceService {

    @Value("${huggingface.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final String MODEL = "mistralai/Mistral-7B-Instruct-v0.1";
    private static final String API_URL = "https://api-inference.huggingface.co/models/" + MODEL;

    public String getAIResponse(String prompt) {
        try {
            // 1. Cria o JSON de forma segura
            ObjectNode requestBody = objectMapper.createObjectNode();
            requestBody.put("inputs", prompt);
            String jsonBody = objectMapper.writeValueAsString(requestBody);

            // 2. Configura os headers CORRETAMENTE
            MultiValueMap<String, String> headersMap = new LinkedMultiValueMap<>();
            headersMap.add("Authorization", "Bearer " + apiKey.trim());
            headersMap.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            HttpHeaders headers = new HttpHeaders(headersMap);

            // 3. Faz a chamada HTTP
            HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    API_URL,
                    HttpMethod.POST,
                    requestEntity,
                    String.class
            );

            // 4. Valida a resposta
            if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
                return "Erro na API: " + response.getStatusCode();
            }

            // 5. Processa o JSON
            JsonNode rootNode = objectMapper.readTree(response.getBody());
            if (rootNode.isArray() && !rootNode.isEmpty() && rootNode.get(0).has("generated_text")) {
                return rootNode.get(0).get("generated_text").asText();
            } else {
                return "Resposta inesperada: " + response.getBody();
            }

        } catch (JsonProcessingException e) {
            return "Erro no JSON: " + e.getMessage();
        } catch (Exception e) {
            return "Erro ao chamar a API: " + e.getMessage();
        }
    }
}
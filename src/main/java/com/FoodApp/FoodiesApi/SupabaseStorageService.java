package com.FoodApp.FoodiesApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class SupabaseStorageService {

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.key}")
    private String apiKey;

    @Value("${supabase.bucket}")
    private String bucket;

    public String uploadFile(MultipartFile file) throws Exception {
        // Remove spaces or special characters from filename to avoid url breakdown
        String originalName = file.getOriginalFilename() != null ? file.getOriginalFilename().replaceAll("\\s+", "_") : "image.png";
        String fileName = UUID.randomUUID() + "_" + originalName;

        String url = supabaseUrl + "/storage/v1/object/" + bucket + "/" + fileName;

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("apikey", apiKey); // Dual safety token pairing for Supabase Gateway
        headers.setContentType(MediaType.parseMediaType(
                file.getContentType() != null ? file.getContentType() : "application/octet-stream"
        ));

        HttpEntity<byte[]> requestEntity = new HttpEntity<>(file.getBytes(), headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            System.out.println("===> Attempting Upload to Supabase Endpoint: " + url);
            ResponseEntity<String> response =
                    restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

            System.out.println("===> Supabase Raw Response Status: " + response.getStatusCode());

            if (response.getStatusCode().is2xxSuccessful()) {
                // Return clear clean download link
                return supabaseUrl + "/storage/v1/object/public/" + bucket + "/" + fileName;
            } else {
                throw new RuntimeException("Upload non-2xx status: " + response.getBody());
            }
        } catch (HttpClientErrorException | HttpServerErrorException ex) {
            // This will directly print the actual hidden error from Supabase in IntelliJ console!
            System.err.println("!!! Supabase Exception Response Body: " + ex.getResponseBodyAsString());
            throw new RuntimeException("Supabase storage error: " + ex.getResponseBodyAsString());
        } catch (Exception e) {
            System.err.println("!!! General Exception during upload: " + e.getMessage());
            throw e;
        }
    }
}
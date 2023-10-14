package wedevx.digitalbank.automation.ui.utils;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.*;

public class MockData {
    private FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"), new RandomService());

    public Map<String, String> generateRandomNameAndEmail(){
        String name = fakeValuesService.bothify(new Faker().name().firstName());
        String email = fakeValuesService.bothify(name + "####test@gmail.com");

        Map<String, String> data = new HashMap<>();
        data.put("name", name);
        data.put("email", email);
        return data;
    }

    public String generateRandomSsn(){
        String ssn = String.format("%09d", new Random().nextInt(1000000000));
        return ssn;
    }

//    public String generateUniqueEmail(){
//           String uniqueEmail = UUID.randomUUID().toString().substring(0, 8);
//        return "user" + uniqueEmail + "@domain.com";
//    }

//    public static String generateUniqueSSN() {
//        Random rand = new Random();
//        // assuming SSN format as XXX-XX-XXXX
//        return String.format("%03d-%02d-%04d", rand.nextInt(1000), rand.nextInt(100), rand.nextInt(10000));
//    }

    }
//    public static void main(String[] args) {
//        MockData mockData = new MockData();
//
//
//        Map<String, String> data = mockData.generateRandomNameAndEmail();
//        System.out.println(data.get("name"));
//        System.out.println(data.get("email"));
//
//
//        System.out.println(mockData.generateRandomSsn());
//
//    }


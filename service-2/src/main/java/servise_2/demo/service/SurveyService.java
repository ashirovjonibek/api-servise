package servise_2.demo.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import servise_2.demo.dto.ClientDto;
import servise_2.demo.dto.TemplateDto;
import servise_2.demo.entity.Client;
import servise_2.demo.entity.Credit;
import servise_2.demo.entity.Survey;
import servise_2.demo.repository.ClientRepository;
import servise_2.demo.repository.CreditRepository;
import servise_2.demo.repository.SurveyRepository;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class SurveyService {
    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CreditRepository creditRepository;

    @Autowired
    SurveyRepository surveyRepository;

    public String save(ClientDto dto){
        Double monthlySalary=dto.getSalary();
        Double annualIncome=monthlySalary*(double)12-(monthlySalary*(double)12*0.3);
        if (annualIncome<dto.getCreditAmount()){
            List<Credit> all = creditRepository.findAll();
            String msg="Hurmatli mizoj siz so'ragan summa yillik daromadingizdan ko'p!!! \n";
            if (all.size()>0){
                msg+="Siz quyidagi kridet turlaridan birini tanlashingiz mumkin\n";
                for (int i = 0; i < all.size(); i++) {
                    if (all.get(i).getFromAmount()<=annualIncome){
                        msg+="Kridit turi: "+all.get(i).getName()+" miqdori: "+all.get(i).getFromAmount()+" dan "
                                +all.get(i).getToAmount()+" gacha "+" foizi: "+
                                all.get(i).getPercent()+"% \n";
                    }
                }
                return msg;
            }else return msg;
        }else {
            List<Credit> all = creditRepository.findAll();
            Client client=new Client();
            Survey survey=new Survey();
            TemplateDto dto1=get(dto.getPassport());
            client.setAddress(dto1.getAddress());
            client.setFullName(dto1.getFullName());
            client.setPassport(dto1.getPassport());
            client.setSalary(dto.getSalary());
            Client save = clientRepository.save(client);
            survey.setClient(save);
            survey.setCreditAmount(dto.getCreditAmount());
            for (int i = 0; i < all.size(); i++) {
                if (all.get(i).getFromAmount()<annualIncome){
                    survey.setCredit(all.get(i));
                }
            }
            surveyRepository.save(survey);
            return "Hurmatli "+client.getFullName()+" sizning arizangiz qabul qilindi tez orada xodimlarimiz siz bilan bog'lanishadi";
        }
    }

    public List<Survey> findAll(){
        return surveyRepository.findAll();
    }

    public HttpHeaders createHeaders(String username, String password){
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(StandardCharsets.UTF_8) );
            String authHeader = "Basic " + new String( encodedAuth );
            set( "Authorization", authHeader );
        }};
    }

    public TemplateDto get(String passport){
        RestTemplate restTemplate=new RestTemplate();
        String uri="http://localhost:8081/api/service1/getUser?passport="+passport;
        ResponseEntity<TemplateDto> exchange = restTemplate.exchange
                (uri, HttpMethod.GET,
                        new HttpEntity<TemplateDto>(createHeaders("admin", "admin")),
                        TemplateDto.class);
        TemplateDto dto1=exchange.getBody();
        return dto1;
    }
}

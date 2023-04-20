package hello.pkpass;

import org.cognitor.passbook4j.Pass;
import org.cognitor.passbook4j.Passbook;
import org.cognitor.passbook4j.model.PassInformation;
import org.cognitor.passbook4j.model.UserInfo;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class PkpassGenerator {

    // PKPass 생성 API
    @PostMapping("/pkpass")
    public void createPkPass(HttpServletResponse response) throws IOException {
        // PKPass 정보를 설정합니다.
        PassInformation passInfo = new PassInformation()
                .setFormatVersion(1)
                .setPassTypeIdentifier("pass.com.example.mypass")
                .setSerialNumber("100")
                .setTeamIdentifier("testteam")
                .setOrganizationName("My Organization")
                .setDescription("Pass description")
                .setLogoText("test logo")
                .setForegroundColor("rgb(0, 5, 25)")
                .setBackgroundColor("rgb(255, 65, 76)");

        // PKPass에 추가할 사용자 정보를 설정합니다.
        UserInfo userInfo = new UserInfo()
                .setFullName("John Doe")
                .setEmailAddress("john.doe@example.com");

        // PKPass 객체를 생성합니다.
        Pass pass = new Pass(passInfo, userInfo);

        // PKPass에 추가할 파일 정보를 설정합니다.
        List<Passbook> passbooks = new ArrayList<>();
        Passbook passbook = new Passbook("icon.png", getClass().getResourceAsStream("/icon.png"), "image/png");
        passbooks.add(passbook);

        // PKPass 파일을 생성합니다.
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        pass.write(stream, passbooks);

        // HttpServletResponse를 이용하여 파일을 반환합니다.
        response.setContentType("application/vnd.apple.pkpass");
        response.setHeader("Content-Disposition", "attachment; filename=mypass.pkpass");
        response.setContentLength(stream.size());
        response.getOutputStream().write(stream.toByteArray());
    }

}
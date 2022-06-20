package com.peanart.ExhibitRegisteration.web;

import com.peanart.ExhibitRegisteration.service.ExRegServiceImpl;
import com.peanart.ExhibitRegisteration.vo.ExhibitRegisterVO;
import com.peanart.main.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class ExhibitRegisterController {

    @Autowired
    ExRegServiceImpl exRegService;

    @Value("${spring.servlet.multipart.location}")
    String path;

    @RequestMapping("/test.do")
    public String dd() {
        return "exhibinsert";
    }



    @PostMapping(value = "/exhib/register", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity reg(@RequestPart("uploadFile") MultipartFile[] uploadFile,
                              @RequestPart("posterFile") MultipartFile posterFile,
                              HttpServletRequest req,
                              HttpSession session,
                              @RequestPart("exhibData") ExhibitRegisterVO exhibitRegisterVO) throws IOException {
        System.out.println("입구에용");

        int usrSeq = Integer.parseInt(session.getAttribute("usrSeq").toString());
        try {
            if(usrSeq != 0){
                exhibitRegisterVO.setUsrSeq(usrSeq);
                exhibitRegisterVO.setGoodsAllow(exhibitRegisterVO.getGoodsAllow());
                exhibitRegisterVO.setExhibKind(exhibitRegisterVO.getExhibKind());

                // 업로드 이미지들
                List<FileVO> files = new ArrayList<>();

                // 고유 폴더 이름 만들기 ( UUID_전시회이름 )
                String dirUuid = UUID.randomUUID().toString();
                String folderName = dirUuid;

                exhibitRegisterVO.setFileDirName(folderName);
                String posterFileName = "poster" + "." + posterFile.getOriginalFilename().split("\\.")[1].toLowerCase();
                exhibitRegisterVO.setFileName(dirUuid + "_" + posterFileName);

                File directory = new File(path + "/" + folderName);
                if (!directory.exists()) {
                    directory.mkdir();
                }

                File poster= new File(path + "/" +folderName + "/" + dirUuid + "_" + posterFileName );

                posterFile.transferTo(poster);

                //String path = "http://15.164.142.253:8080/imagePath/" + fvo.getUuid() + '_' + fvo.getFileName();asdasd




                exRegService.insertExhib(exhibitRegisterVO);
                int exhibSeq = (int)exRegService.getLastID();
                System.out.println(exhibSeq);
                for (MultipartFile file : uploadFile) {
                    if (!file.isEmpty()) {
                        int fileIndex = 1;
                        String fileExtension = file.getOriginalFilename().split("\\.")[1].toLowerCase();

                        FileVO fvo = new FileVO(UUID.randomUUID().toString(), file.getContentType());
                        files.add(fvo);

                        File newFileName = new File(path + "/" +folderName + "/" + fvo.getFileUuid() + "." + fileExtension);

                        fvo.setFileDirName(folderName);
                        fvo.setExhibSeq(exhibSeq);
                        fvo.setFileUuid(fvo.getFileUuid() + "." + fileExtension);
                        exRegService.insertExExhibFile(fvo);
                        file.transferTo(newFileName);


                    }
                }
//
//                // AI 모델 업데이트 실행 (flask 서버)
//                String uri = "http://15.164.142.253:5000/updateModel";
//                RestTemplate rt = new RestTemplate();
//                rt.getForObject(uri, String.class);

                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build(); //400 400 400 400 400 400 0400 400
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

    }
}

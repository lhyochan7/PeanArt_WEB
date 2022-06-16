package com.peanart.ExhibitRegisteration.web;

import com.peanart.ExhibitRegisteration.service.ExRegServiceImpl;
import com.peanart.ExhibitRegisteration.vo.ExhibitRegisterVO;
import com.peanart.main.vo.FileVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    @PostMapping("/exhib/register")
    public ResponseEntity reg(@RequestParam MultipartFile[] uploadFile, @RequestParam MultipartFile posterFile, HttpServletRequest req, HttpSession session, ExhibitRegisterVO exhibitRegisterVO) throws IOException {
        Map<String, Object> rtn = new HashMap<>();
        System.out.println("입구에용");
        int usrSeq = Integer.parseInt(session.getAttribute("usrSeq").toString());
        try {
            if(usrSeq != 0){
                //int usrSeq = (int)session.getAttribute("usrSeq");
                //연결할때 주석 풀어서 usrSeq 값 부여 ^^
                exhibitRegisterVO.setUsrSeq(usrSeq);
                exhibitRegisterVO.setGoodsAllow((int)exhibitRegisterVO.getGoodsAllow());
                exhibitRegisterVO.setExhibKind((int)exhibitRegisterVO.getExhibKind());


                // 업로드 이미지들
                List<FileVO> files = new ArrayList<>();

                // 고유 폴더 이름 만들기 ( UUID_전시회이름 )
                String dirUuid = UUID.randomUUID().toString();
                String folderName = dirUuid + "_" + exhibitRegisterVO.getExhibTitle();

                exhibitRegisterVO.setFileDirName(folderName);
                exhibitRegisterVO.setFileName(dirUuid + "_" + posterFile.getOriginalFilename());

                File directory = new File(path + "/" + folderName);
                if (!directory.exists()) {
                    directory.mkdir();
                }

                File poster = new File(path + "/" +folderName + "/" + dirUuid + "_" + posterFile.getOriginalFilename());
                posterFile.transferTo(poster);

                //String path = "http://localhost:8080/imagePath/" + fvo.getUuid() + '_' + fvo.getFileName();

                // rtn에 포스터 값 전달, Multipart type
                rtn.put("poster", posterFile);


                exRegService.insertExhib(exhibitRegisterVO);
                int exhibSeq = (int)exRegService.getLastID();
                System.out.println(exhibSeq);
                for (MultipartFile file : uploadFile) {
                    if (!file.isEmpty()) {
                        int fileIndex = 1;
                        FileVO fvo = new FileVO(UUID.randomUUID().toString(), file.getOriginalFilename(), file.getContentType());
                        files.add(fvo);

                        File newFileName = new File(path + "/" +folderName + "/" + fvo.getfile_Uuid() + "_" + fvo.getFileName());

                        fvo.setFileDirName(folderName);
                        fvo.setExhibSeq(exhibSeq);
                        exRegService.insertExExhibFile(fvo);
                        file.transferTo(newFileName);

                        // rtn에 key : 파일+순서(1부터) value : 파일 multipart type
                        rtn.put("fileIndex" + fileIndex, file);
                    }
                }
                return ResponseEntity.ok().body(rtn);
            }
            return ResponseEntity.badRequest().build(); //400 400 400 400 400 400 0400 400
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }

    }
}

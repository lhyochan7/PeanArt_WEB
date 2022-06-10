package com.peanart.ExhibitRegisteration.web;

import com.peanart.ExhibitRegisteration.service.ExRegService;
import com.peanart.ExhibitRegisteration.service.ExRegServiceImpl;
import com.peanart.ExhibitRegisteration.vo.ExhibitRegisterVO;
import com.peanart.main.vo.FileVO;
import com.peanart.member.service.impl.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class ExhibitRegisterController {

    @Autowired
    ExRegServiceImpl exRegService;

    @Value("${spring.servlet.multipart.location}")
    String path;


    @RequestMapping("/test.do")
    public String dd() {
        return "upload";
    }



    @RequestMapping("/exhibRegister.do")
    public String reg(@RequestParam MultipartFile[] uploadFile, @RequestParam MultipartFile posterFile, HttpServletRequest req, HttpSession session, ModelMap model, ExhibitRegisterVO exhibitRegisterVO) throws IOException {

        //int usrSeq = (int)session.getAttribute("usrSeq");
        //연결할때 주석 풀기 ^^
        exhibitRegisterVO.setUsrSeq(1);
        exhibitRegisterVO.setGoodsAllow((int)exhibitRegisterVO.getGoodsAllow());
        exhibitRegisterVO.setExhibKind((int)exhibitRegisterVO.getExhibKind());

        // 파일 업로드
        // 업로드 이미지들
        List<FileVO> files = new ArrayList<>();

        // 고유 폴더 이름 만들기 ( UUID_전시회이름 )
        String dirUuid = UUID.randomUUID().toString();
        String folderName = dirUuid + "_" + exhibitRegisterVO.getExhibTitle();
        System.out.println(folderName);

        exhibitRegisterVO.setExhibPosterFileDirName(folderName);
        exhibitRegisterVO.setExhibPosterFileName(dirUuid + "_" + posterFile.getOriginalFilename());
        System.out.println(exhibitRegisterVO.getExhibPosterFileDirName());
        System.out.println(exhibitRegisterVO.getExhibPosterFileName());

        File directory = new File(path + "/" + folderName);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File poster = new File(path + "/" +folderName + "/" + dirUuid + "_" + posterFile.getOriginalFilename());
        posterFile.transferTo(poster);



        //String path = "http://localhost:8080/imagePath/" + fvo.getUuid() + '_' + fvo.getFileName();
        model.addAttribute("files", files);
        exRegService.insertExhib(exhibitRegisterVO);
        int exhibSeq = (int)exRegService.getLastID();
        System.out.println(exhibSeq);
        for (MultipartFile file : uploadFile) {
            if (!file.isEmpty()) {
                FileVO fvo = new FileVO(UUID.randomUUID().toString(), file.getOriginalFilename(), file.getContentType());
                files.add(fvo);

                File newFileName = new File(path + "/" +folderName + "/" + fvo.getUuid() + "_" + fvo.getFileName());

                fvo.setFileDirName(folderName);
                fvo.setExhibSeq(exhibSeq);
                exRegService.insertExExhibFile(fvo);
                file.transferTo(newFileName);
            }
        }

        return "exhibinsert";
    }
}

package com.peanart.mypage.web;

import com.peanart.main.vo.FileVO;
import com.peanart.mypage.service.MyPageService;
import com.peanart.mypage.vo.MyPageFileVO;
import com.peanart.mypage.vo.MyPageFollowForm;
import com.peanart.mypage.vo.MyPageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class MyPageController {

    @Value("${spring.servlet.multipart.location}")
    String path;

    @Autowired
    MyPageService myPageService;

    // mypage 상단 user info
    @GetMapping("/my-page")
    public ResponseEntity <Map<String, Object>> getUserInfo(HttpServletRequest req, HttpSession session, ModelMap model){
        Map<String, Object> rtn = new HashMap<>();

        try {
            // 세션에서 유저 판별값 받아오기
            int usrSeq = 0;
            usrSeq = Integer.parseInt(session.getAttribute("usrSeq").toString());

            //MyPage User Info
            if(usrSeq != 0){
                MyPageVO user = myPageService.getUserInfo(usrSeq);
                rtn.put("userInfo", user);

                String folderName = user.getFileDirName();
                String fileName = user.getFileName();

                String profileImg = "http://localhost:8080/imagePath/" + folderName + "/" + fileName;

                rtn.put("profileImg", profileImg);

                // follow list 폴더 이름, 파일 이름, 팔로우당한 사람 아이디, 닉네임
                List<MyPageFollowForm> followList = myPageService.getFollowList(usrSeq);
                rtn.put("followList", followList);

                // rtn에 profile Img 호출 URI, Follo List 담겨있음
                return ResponseEntity.ok()
                        .body(rtn);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    // mypage profile 사진 등록 / 변경
    @PostMapping("/profile-img")
    public ResponseEntity<MultipartFile> profileImg(@RequestParam MultipartFile profileImg, @RequestParam String usrId, Model model) throws IllegalStateException, IOException {
        try {
            // 세션에서 아이디 받는 걸로 바꿔줘잉
            int usrSeq = 0;
            int isExist = myPageService.isThereImg(usrSeq);
            System.out.println(isExist);
            if(isExist == 0){

                // 고유 폴더 이름 만들기 ( UUID_userId )
                String dirUuid = UUID.randomUUID().toString();
                String folderName = dirUuid + "_" + usrId;
                System.out.println(folderName);

                File directory = new File(path + "/" + folderName);
                if (!directory.exists()) {
                    directory.mkdir();
                }

                FileVO fvo = new FileVO(UUID.randomUUID().toString(), profileImg.getOriginalFilename(), profileImg.getContentType());
                File newFileName = new File(path + "/" +folderName + "/" + fvo.getfile_Uuid() + "_" + fvo.getFileName());
                profileImg.transferTo(newFileName);

                model.addAttribute("profileImg", profileImg);

                MyPageFileVO myPageFileVO = new MyPageFileVO();
                myPageFileVO.setFileDirName(folderName);
                myPageFileVO.setFileName(fvo.getfile_Uuid() + "_" + fvo.getFileName());

                // 나중에 session에서 user 분류값 넣기
                myPageFileVO.setUsrSeq(usrSeq);

                myPageService.setProfileImg(myPageFileVO);

                // 프로필 사진 생성 후 URI / body에는 profileImg multipart 타입
                return ResponseEntity.created(URI.create("프로필 사진 등록 후 URI")).body(profileImg);
            }
            else{
                MyPageFileVO userProfile = myPageService.getProfileImg(usrSeq);

                String folderName = userProfile.getFileDirName();

                File directory = new File(path + "/" + folderName);
                if (!directory.exists()) {
                    directory.mkdir();
                }

                FileVO fvo = new FileVO(UUID.randomUUID().toString(), profileImg.getOriginalFilename(), profileImg.getContentType());
                File newFileName = new File(path + "/" +folderName + "/" + fvo.getfile_Uuid() + "_" + fvo.getFileName());
                profileImg.transferTo(newFileName);

                model.addAttribute("profileImg", profileImg);

                MyPageFileVO myPageFileVO = new MyPageFileVO();
                myPageFileVO.setFileDirName(folderName);
                myPageFileVO.setFileName(fvo.getfile_Uuid() + "_" + fvo.getFileName());

                // 나중에 session에서 user 분류값 넣기
                myPageFileVO.setUsrSeq(usrSeq);

                myPageService.updateProfileImg(myPageFileVO);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}

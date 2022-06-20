package com.peanart.mypage.web;

import com.peanart.main.vo.FileVO;
import com.peanart.mypage.service.MyPageService;
import com.peanart.mypage.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.*;

@Controller
public class MyPageController {

    @Value("${spring.servlet.multipart.location}")
    String path;

    @Autowired
    MyPageService myPageService;


    // 테스트 페이지 이동용
    @GetMapping("/upload")
    public String goUpload(HttpSession session){
        return "upload";
    }
    @GetMapping("/imgtest")
    public String imgtest(HttpSession session){
        return "imgtest";
    }

    // mypage 상단 user info / 불러오기 성공 : ok 실패 : badRequest 그외 오류 notFound
    @GetMapping("/my-page")
    public ResponseEntity <Map> getUserInfo(HttpServletRequest req, HttpSession session){
        Map<String, Object> rtn = new HashMap<>();

        try {
            // 세션에서 유저 판별값 받아오기
            int usrSeq = 0;
            usrSeq = Integer.parseInt(session.getAttribute("usrSeq").toString());

            //MyPage User Info / profileImg Url(15.164.142.253:8080/imagePath 포함)
            if(usrSeq != 0){
                MyPageVO user = myPageService.getUserInfo(usrSeq);
                rtn.put("userInfo", user);

                String folderName = user.getFileDirName();
                String fileName = user.getFileName();

                String profileImg = "http://localhost:8080/imagePath/" + folderName + "/" + fileName;

                rtn.put("profileImg", profileImg);

                // 다녀온 전시회 list / 타이틀, 포스터 Url(15.164.142.253:8080/imagePath 포함)
                List<MyPageExhibVO> exhibList = myPageService.getExhibList(usrSeq);
                List<MyPageExhibForm> exhibFormList = new ArrayList<>();
                for(MyPageExhibVO exhibVO : exhibList){
                    MyPageExhibForm exhibForm = new MyPageExhibForm();
                    exhibForm.setUsrSeq(exhibVO.getUsrSeq());
                    exhibForm.setExhibSeq(exhibVO.getExhibSeq());
                    exhibForm.setExhibTitle(exhibVO.getExhibTitle());
                    exhibForm.setExhibPosterUrl("http://localhost:8080/imagePath/" + exhibVO.getFileDirName() + "/" + exhibVO.getFileName());
                    System.out.println(exhibForm);
                    exhibFormList.add(exhibForm);
                }
                rtn.put("exhibList", exhibFormList);

                // follow list 폴더 이름, 파일 이름, 팔로우당한 사람 아이디, 닉네임 / 팔로우 당한사람 닉네임, 사진(15.164.142.253:8080/imagePath 포함)
                List<MyPageFollowVO> followList = myPageService.getFollowList(usrSeq);
                List<MyPageFollowForm> followFormList = new ArrayList<>();
                for(MyPageFollowVO followed : followList){
                    MyPageFollowForm followForm = new MyPageFollowForm();
                    followForm.setUsrId(followed.getUsrId());
                    followForm.setUsrNickname(followed.getUsrNickname());
                    followForm.setFollowedImgUrl("http://localhost:8080/imagePath/" + followed.getFileDirName() + "/" + followed.getFileName());
                    System.out.println(followForm);
                    followFormList.add(followForm);
                }
                rtn.put("followList", followFormList);

                // rtn에 profile Img 호출 URI, Follow List 담겨있음
                return ResponseEntity.ok()
                        .body(rtn);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    // mypage profile 사진 등록 / 변경 성공 : created(URI : /my-page) 실패 : badRequest 그 외 : notFound
    @PostMapping("/profile-img")
    public ResponseEntity profileImg(@RequestParam MultipartFile profileImg, HttpSession session) throws IllegalStateException, IOException {
        try {
            // 세션에서 아이디 받는 걸로 바꿔줘잉
            int usrSeq = Integer.parseInt(session.getAttribute("usrSeq").toString());
            int isExist = myPageService.isThereImg(usrSeq);

            if(isExist == 0){

                // 고유 폴더 이름 만들기 ( UUID_userId )
                String dirUuid = UUID.randomUUID().toString();
                String folderName = dirUuid + "_" + session.getAttribute("usrId").toString();
                System.out.println(folderName);

                File directory = new File(path + "/" + folderName);
                if (!directory.exists()) {
                    directory.mkdir();
                }

                FileVO fvo = new FileVO(UUID.randomUUID().toString(), profileImg.getContentType());

                // 확장자  소문자로
                String fileName = profileImg.getOriginalFilename().split("\\.")[0] + "." + profileImg.getOriginalFilename().split("\\.")[1].toLowerCase();
                System.out.println("lowerCase : " + fileName);
                File newFileName = new File(path + "/" +folderName + "/" + fvo.getFileUuid() + "_" + fileName);

                profileImg.transferTo(newFileName);

                MyPageFileVO myPageFileVO = new MyPageFileVO();
                myPageFileVO.setFileDirName(folderName);
                myPageFileVO.setFileName(fvo.getFileUuid() + "_" + fileName);

                myPageFileVO.setUsrSeq(usrSeq);

                //DB INSERT
                myPageService.setProfileImg(myPageFileVO);
                return ResponseEntity.created(URI.create("/my-page")).build();
            }
            else if(isExist != 0){
                // DB에서 프로필 이미지 디렉토리, 파일이름 받아서
                MyPageFileVO userProfile = myPageService.getProfileImg(usrSeq);

                String folderName = userProfile.getFileDirName();
                String filename = userProfile.getFileName();
                File profile = new File(path + "/" + folderName +"/" +  filename);

                // 존재하면 지웁니다
                if(profile.exists()){
                    profile.delete();
                    System.out.println("file deleted");
                }

                // 폴더가 없으면 만들구요
                File directory = new File(path + "/" + folderName);
                if (!directory.exists()) {
                    directory.mkdir();
                }

                // 프로필 이미지 새로 만듭니다
                FileVO fvo = new FileVO(UUID.randomUUID().toString(), profileImg.getContentType());

                // 확장자  소문자로
                String fileName = profileImg.getOriginalFilename().split("\\.")[0] + "." + profileImg.getOriginalFilename().split("\\.")[1].toLowerCase();
                System.out.println("lowerCase : " + fileName);
                File newFileName = new File(path + "/" +folderName + "/" + fvo.getFileUuid() + "_" + fileName);

                profileImg.transferTo(newFileName);

                MyPageFileVO myPageFileVO = new MyPageFileVO();
                myPageFileVO.setFileDirName(folderName);
                myPageFileVO.setFileName(fvo.getFileUuid() + "_" + fileName);

                myPageFileVO.setUsrSeq(usrSeq);

                //DB 값 변경
                myPageService.updateProfileImg(myPageFileVO);
                return ResponseEntity.created(URI.create("/my-page")).build();
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
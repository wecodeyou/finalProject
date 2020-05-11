package com.it.wecodeyou;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@GetMapping("/greeting")
	public String greetingPage() {
		return "intro/greeting";
	}
	@GetMapping("/intro")
	public String introPage() {
		return "intro/intro";
	}
	@GetMapping("/location")
	public String locationPage() {
		return "intro/location";
	}
	
	@GetMapping("/test01")
	public String test01() {
		return "test/test01";
	}

	@GetMapping("/test02")
	public String test02() {
		return "test/test02";
	}
	@RequestMapping("/daumeditor/img-popup")
	public String imagePopup() {
		return "/include/image";
	}
	
	@PostMapping("/singleUploadImageAjax")
	public @ResponseBody HashMap singleUploadImageAjax(
			@RequestParam("Filedata") MultipartFile multipartFile, HttpSession session) {
		
		HashMap fileInfo = new HashMap(); //CallBack할 때 이미지 정보를 담을 Map
		
		if(multipartFile != null && !(multipartFile.getOriginalFilename().equals(""))) {
			
			//확장자 제한
			String originalName = multipartFile.getOriginalFilename(); //실제 파일명
			String originalNameExtension = originalName.substring(originalName.lastIndexOf(".")+1).toLowerCase();
			if(!(originalNameExtension.equals("jpg")) || !(originalNameExtension.equals("gif")) ||
					!(originalNameExtension.equals("png")) || !(originalNameExtension.equals("bmp"))){
				fileInfo.put("result", -1);
				return fileInfo;
			}
			
			long fileSize = multipartFile.getSize();
			long limitFileSize = 1*1024*1024;
			if(limitFileSize < fileSize) {
				fileInfo.put("result", -2);
			}
			//저장경로
			String defaultPath = session.getServletContext().getRealPath("/");
			String path = defaultPath + File.separator + "upload" + File.separator + "board" + File.separator + "images" + File.separator + "";
			
			File file = new File(path);
			//디렉토리 없을시 생성
			if(!file.exists()) {
				file.mkdirs();
			}
			//파일 저장명 처리
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
			String today=formatter.format(new Date());
			String modifyName = today + "-" + UUID.randomUUID().toString().substring(20) + "." + originalNameExtension;
			
			//Multipart 처리
			try {
				multipartFile.transferTo(new File(path + modifyName));
				
				//로그
				System.out.println("파일 업로드 정보");
				System.out.println("path : " + path);
				System.out.println("originalName: " + originalName);
				System.out.println("modifyName: "+ modifyName);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("이미지 파일 업로드 실패 -singleUploadimageAjax");
			}
			
			// CallBack - Map에 담기
			String imageurl = session.getServletContext().getContextPath() + "/upload/board/images/" + modifyName; // separator와는 다름! 
			fileInfo.put("imageurl", imageurl); // 상대파일경로(사이즈변환이나 변형된 파일) 
			fileInfo.put("filename", modifyName); // 파일명 
			fileInfo.put("filesize", fileSize); // 파일사이즈 
			fileInfo.put("imagealign", "C"); // 이미지정렬(C:center)
			fileInfo.put("originalurl", imageurl); // 실제파일경로 
			fileInfo.put("thumburl", imageurl); // 썸네일파일경로(사이즈변환이나 변형된 파일)
			
			fileInfo.put("result", 1); // -1, -2를 제외한 아무거나 싣어도 됨 }
			}
		return fileInfo; // @ResponseBody 어노테이션을 사용하여 Map을  
	}
	
	//커리큘럼소개 main 요청
	@GetMapping("/curriculum/on_main")
	public ModelAndView curriculumOnMain(ModelAndView mv, HttpServletRequest req) {
	      
	mv.setViewName("curriculum/onMain");
	mv.addObject("seq", req.getParameter("seq"));
	return mv;
	      
	}

	//커리큘럼소개 main 요청
	@GetMapping("/curriculum/off_main")
	public ModelAndView curriculumOffMain(ModelAndView mv, HttpServletRequest req) {
		
		mv.setViewName("curriculum/offMain");
		mv.addObject("seq", req.getParameter("seq"));
		return mv;
		
	}
	
	//커리큘럼소개 sub 요청
	@GetMapping("/curriculum/sub")
	public ModelAndView curriculumSub(ModelAndView mv, HttpServletRequest req) {
		
		mv.setViewName("curriculum/sub");
		mv.addObject("seq", req.getParameter("seq"));
		return mv;
		
	}
	
}

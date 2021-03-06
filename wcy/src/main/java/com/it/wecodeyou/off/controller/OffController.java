package com.it.wecodeyou.off.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.it.wecodeyou.member.model.MemberVO;
import com.it.wecodeyou.member.service.IMemberService;
import com.it.wecodeyou.off.model.OffProductVO;
import com.it.wecodeyou.off.model.OffVO;
import com.it.wecodeyou.off.model.SeatVO;
import com.it.wecodeyou.off.service.IOffService;
import com.it.wecodeyou.off.service.ISeatService;
import com.it.wecodeyou.on.model.OnVO;
import com.it.wecodeyou.on.service.IOnService;
import com.it.wecodeyou.product.model.ProductVO;
import com.it.wecodeyou.product.service.IProductService;
import com.it.wecodeyou.purchase.model.PurchaseVO;
import com.it.wecodeyou.purchase.service.IPurchaseService;

@RestController
@RequestMapping("/off")
public class OffController {
	@Autowired
	private ISeatService service;

	@Autowired
	IProductService productService;

	@Autowired
	IOffService offService;

	@Autowired
	IOnService onService;

	@Autowired
	IMemberService memberService;

	@Autowired
	IPurchaseService purchaseService;

	// 강의실 체크 페이지 요청
	@GetMapping("/seat_main")
	public ModelAndView selectSeat(ModelAndView mv) {

		mv.setViewName("off/seat_main");
		return mv;

	}

	// 강의실 페이지 요청
	@GetMapping("/seat")
	public ModelAndView viewSeat(ModelAndView mv, HttpServletRequest req) {

		mv.setViewName("off/seat");
		mv.addObject("off_no", req.getParameter("off_no"));
		return mv;

	}

	@GetMapping(value = "/register")
	public ModelAndView register(ModelAndView mv) {
		mv.setViewName("/off/OffForm");
		return mv;
	}

	// 오프라인강의랑 상품 정보를 다 가져오기 위해 만든 커스텀 VO로 한번에 가져옴
	@PostMapping(value = "/register")
	public String register(@RequestBody OffProductVO opvo, HttpSession session) {
		System.out.println("/register - param received \n\r " + opvo.toString());
		String result = null;
		MemberVO login = (MemberVO) session.getAttribute("login");
		ProductVO pvo = new ProductVO();
		pvo.setProductName(opvo.getProductName());
		pvo.setProductPrice(opvo.getProductPrice());
		pvo.setProductType(opvo.getProductType());
		pvo.setProductThumb(opvo.getProductThumb());
		pvo.setProductDetail(opvo.getProductDetail());
		
		OnVO onvo = new OnVO();
		OffVO ovo = new OffVO();
		
		// tag number list
		ArrayList<Integer> sendTagList = opvo.getSendTagList();

		
		switch (pvo.getProductType()) {
			
			case "0": 
					  onvo.setOnAuthor(login.getUserEmail());
					  onvo.setOnCategory(opvo.getOffCategory());
					  onvo.setOnDays(0);
					  
						if (onService.insert(pvo, onvo, sendTagList) == 1) {
							result = "off_success";
						} else {
							result = "off_fail";
						}
					  break;
			case "1": 
					  ovo.setOffAuthor(login.getUserEmail());
					  ovo.setOffCategory(opvo.getOffCategory());
					  ovo.setOffPlace(opvo.getOffPlace());
					  ovo.setOffSeats(opvo.getOffSeats());
					  ovo.setOffStartAt(opvo.getOffStartAt());
					  ovo.setOffEndAt(opvo.getOffEndAt());
					  ovo.setOffRoom(opvo.getOffRoom());
					  
						if (offService.insert(pvo, ovo, sendTagList) == 1) {
							result = "off_success";
						} else {
							result = "off_fail";
						}
					  break;
		}		

		return result;
	}

	@GetMapping("/{productNo}")
	public ModelAndView info(@PathVariable Integer productNo, ModelAndView mv) {

		ProductVO pvo = productService.getOneInfo(productNo);
		OffVO ovo = offService.getInfoByProductNo(productNo);
		System.out.println("/off/{productNo} ProductVO : \r\n" + pvo.toString());
		System.out.println("/off/{productNo} OffVO : \r\n" + ovo.toString());
		mv.addObject("product", pvo);
		mv.addObject("off", ovo);
		mv.setViewName("/off/detail");
		return mv;
	}

	/*
	 * @GetMapping("/test") public ModelAndView test(ModelAndView mv,
	 * HttpServletRequest req) { if (req.getParameter("seat") != null) {
	 * service.bookedList2(Integer.parseInt(req.getParameter("seat"))); }
	 * mv.setViewName("off/test"); mv.addObject("data", req.getParameter("seat"));
	 * 
	 * return mv;
	 * 
	 * }
	 */

	@PostMapping("/selectChecked")
	public Map<String, ArrayList<Integer>> bookedList(@RequestBody Integer seat_off_no) {

		Map<String, ArrayList<Integer>> retVal = new HashMap<String, ArrayList<Integer>>();
		ArrayList<Integer> booked_list = service.bookedList(seat_off_no);
		retVal.put("list", service.bookedList(seat_off_no)); // list란 키로 bookedList의 값을 넣어줍니다.

		if (booked_list == null) {
			System.out.println("비어있음");
		} else {
			System.out.println("정보있음");
			for (Integer integer : booked_list) {
				System.out.println(integer);
			}
		}
		return retVal;

	}

	/*
	 * @GetMapping("/myoff") public ModelAndView Test(ModelAndView mv) {
	 * mv.setViewName("/off/selectOff"); return mv; }
	 * 
	 * @PostMapping(value = "/myoff") public ModelAndView
	 * getMyList(@RequestParam(value="userEmail") String userEmail, ModelAndView mv)
	 * { System.out.println("POST /myoff/" + userEmail + " "); List<PurchaseVO>
	 * purchaseList = null; List<OffProductVO> temp = new ArrayList<OffProductVO>();
	 * MemberVO mvo = null;
	 *//**
		 * 나중에 미리세션처리하고 지금은 인자로 받은 이메일로 세션만듬
		 *//*
			 * 
			 * if (memberService.checkEmail(userEmail) == 1) { mvo =
			 * memberService.findMemberById(userEmail); System.out.println("유저 정보: " +
			 * mvo.toString()); purchaseList =
			 * purchaseService.selectUsersPurchase(mvo.getUserNo()); for(PurchaseVO pvo :
			 * purchaseList) { ProductVO productVO =
			 * productService.getOneInfo(pvo.getPurchaseProNo());
			 * if(productVO.getProductType().equals("1")) {
			 * temp.add(offService.getOffProduct(productVO.getProductNo())); } } } else {
			 * System.out.println("유저를  찾을 수 없습니다"); } mv.addObject("purchaseList", temp);
			 * mv.setViewName("/off/selectOff"); return mv;
			 * 
			 * }
			 * 
			 * @GetMapping("/myclass") public ModelAndView getMyClass(ModelAndView mv) {
			 * System.out.println("GET /myclass"); mv.setViewName("/off/authorSelection");
			 * MemberVO mvo = null; List<OffVO> offList = null; List<OffProductVO> opvo =
			 * new ArrayList<OffProductVO>(); if (memberService.checkEmail(userEmail) == 1)
			 * { mvo = memberService.findMemberById(userEmail); System.out.println("유저 정보: "
			 * + mvo.toString()); offList = offService.getInfoByAuthor(mvo.getUserEmail());
			 * System.out.println(offList.size()); for(OffVO ovo: offList) {
			 * System.out.println(ovo.toString());
			 * opvo.add(offService.getOffProduct(ovo.getOffProductNo())); }
			 * 
			 * } else { System.out.println("유저 정보가 없습니다"); } mv.addObject("offList", opvo);
			 * mv.setViewName("/off/authorSelection"); return mv; }
			 * 
			 * @PostMapping("/myclass") public ModelAndView
			 * getMyClass(@RequestParam(value="userEmail") String userEmail, ModelAndView
			 * mv) { System.out.println("POST /myclass"); MemberVO mvo = null; List<OffVO>
			 * offList = null; List<OffProductVO> opvo = new ArrayList<OffProductVO>(); if
			 * (memberService.checkEmail(userEmail) == 1) { mvo =
			 * memberService.findMemberById(userEmail); System.out.println("유저 정보: " +
			 * mvo.toString()); offList = offService.getInfoByAuthor(mvo.getUserEmail());
			 * System.out.println(offList.size()); for(OffVO ovo: offList) {
			 * System.out.println(ovo.toString());
			 * opvo.add(offService.getOffProduct(ovo.getOffProductNo())); }
			 * 
			 * } else { System.out.println("유저 정보가 없습니다"); } mv.addObject("offList", opvo);
			 * mv.setViewName("/off/authorSelection"); return mv; }
			 * 
			 * 
			 * 
			 * 
			 * 
			 * 
			 */

}
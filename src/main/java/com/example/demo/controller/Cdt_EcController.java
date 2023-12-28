package com.example.demo.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cdt_EcController {
	String baseurl  = "https://www.cdt-ec.com/potal-web/pendingGxnotice/selectbyname?";
	String url = "https://www.cdt-ec.com/potal-web/pendingGxnotice/selectbyname?messagetitle=矿压&pagesize=20";
		public String addMineGoodsSearchInfo() {
			HttpHeaders headers = new HttpHeaders();
			headers.add(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64)"+
			" AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36");
			headers.add(HttpHeaders.COOKIE, "acw_tc=2760824817006442166958531eb06aba052f8aef55f0895b0f5cefd5eed126;"+
			" acw_sc__v2=655dc57943ee43ec53ab83466628c5ba35834447;"+
			" acw_sc__v2=655dc57f2e969ce07d6de12b30bef3b7710f829d; ssxmod_itna=Cqfx9DuGDQitDteGHD8D2Y6wcRiEeYF=sOYqODlO=xA5D8D6DQeGTb2WNdbDRjnw0+5qboerIKVF=C23pta+YpTzYxCPGnDBK+zOQYxiicDCeDIDWeDiDG4Gm94GtDpxG=Djjtz1M6xYPDEj5DRpPDuxBGDGP1LaKhDeKD0ZbFDQ9hU6DDB2x5W7G7KQYDepPhn1ECYWfPFjKD91YDsrD6dj9mczM4Sa8LXK73px0kPq0O9g7CH4GdU2y1xSqPEnqPb0TNNYxxLYRqmjD4zmrN=iGb=ji9hT0Lq2iK/iV4DDpPdGr9ZnwDD=; ssxmod_itna2=Cqfx9DuGDQitDteGHD8D2Y6wcRiEeYF=sOYqD6hb7xYqDsKe=DLGlWX=l4nbdZq88ShGKxhMBOh9NdKzE7OLx=HQBrNqnBrrB+zF066GideDTB5Ne=EnLcrz519dANhNXLeoB6d5UyznEyDlPAPV6u4kEP1dUKsWjir3Oiw6ixqegHaZnpCcQWC6nnTsKnidoBtd6APYB9NU/mFu/4+k=5F7RoziZOdVOf86GStOUP4u1lwvYWqXQcFl8rjN9+LjfKUnOHwMiO+rTw6RRHwvkqs5cnt8ae11oCs44dZuXAL3lb/lZBb+BqluN9D3kxaNEK3rChhrlxFouHKih0qdeD74gQhGC4xdb+KRMo7wK=SoxxzGTY/TpE+weBdV2HURiIdxZOrvh53jogjii4i/CzlAiOqQVuiwGwhx=3=vUGpoEi4azK8tK+A/8A3=o5EAChGqRCDfa1CG4SQ+aTmawQowXAd6uGudI6Cr3wa3Zd=2Hm1YMptbhNx9Xfuri1Gd4tFDPD7QuDddCdZ6Gw7DWP+dCLV0G3wfoogQF=bUe37oeAgiyPqjmKjDDjKDedRFgx0erqD8dDKd0QxqIFbuluz8D8Y+sHhs6/FU2Py8Dv9BTSddYuawar9Q7xr4D===");

			return "success666";
		}
}

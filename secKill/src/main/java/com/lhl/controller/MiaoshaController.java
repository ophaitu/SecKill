package com.lhl.controller;

import com.lhl.domain.MiaoshaOrder;
import com.lhl.domain.MiaoshaUser;
import com.lhl.domain.OrderInfo;
import com.lhl.redis.RedisService;
import com.lhl.result.CodeMsg;
import com.lhl.service.GoodsService;
import com.lhl.service.MiaoshaService;
import com.lhl.service.OrderService;
import com.lhl.vo.GoodsVo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/miaosha")
public class MiaoshaController implements InitializingBean {
	@Autowired
	MiaoshaService miaoshaService;
	@Autowired
	RedisService redisService;
	@Autowired
	OrderService orderService;
	@Autowired
	GoodsService goodsService;
	@Override
	public void afterPropertiesSet() throws Exception {

	}
	@RequestMapping(value="/do_miaosha", method= RequestMethod.POST)
	public String miaosha(Model model, MiaoshaUser user,@RequestParam("goodsId")long goodsId) {
		model.addAttribute("user", user);
		if(user == null) {
			return "login";
		}
		GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
		int stock = goods.getStockCount();
		if(stock<=0){
			model.addAttribute("errmsg",CodeMsg.MIAO_SHA_OVER);
			return "miaosha_fail";
		}
		MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(),goodsId);
		if(order!=null){
			model.addAttribute("errmsg",CodeMsg.REPEATE_MIAOSHA);
			return "miaosha_fail";
		}
		OrderInfo orderInfo = miaoshaService.miaosha(user,goods);
		model.addAttribute("orderInfo", orderInfo);
		model.addAttribute("goods", goods);
		return "order_detail";

	}
}

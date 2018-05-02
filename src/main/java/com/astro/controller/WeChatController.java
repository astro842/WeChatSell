package com.astro.controller;

import com.astro.Util.ResultVOUtil;
import com.astro.VO.ResultVO;
import com.astro.dataobject.BuyerInfo;
import com.astro.service.BuyerService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by astro on 2018/4/4.
 */
@Controller
@RequestMapping("/wechat")
@Slf4j
public class WeChatController {

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private BuyerService buyerService;

    /**
     *
     *  http://o2owechat.free.ngrok.cc/sell/wechat/authorize?returnUrl=http://o2owechat.free.ngrok.cc/sell/index/index
     *
     **/
//    static WxMpUser wxMpUser=null;

    @GetMapping("/authorize")
    public String authorize(@RequestParam("returnUrl") String returnUrl) {
        String testUrl="http://zdm47b.natappfree.cc";
        String url=testUrl+"/sell/wechat/userInfo";
        log.info("--------进入WeChat认证--------");
        //String url = "http://o2owechat.free.ngrok.cc/sell/wechat/userInfo";
        String redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAUTH2_SCOPE_USER_INFO, URLEncoder.encode(returnUrl));
        log.info("--------进入WeChat认证，重定向到/sell/wechat/userInfo--------" + redirectUrl);
        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken = new WxMpOAuth2AccessToken();
        log.info("--------进入/userInfo------获取code:"+code);
        log.info("--------进入/userInfo------通过code获取openId");
        WxMpUser wxMpUser =null;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
            wxMpUser = wxMpService.oauth2getUserInfo(wxMpOAuth2AccessToken, null);
            log.info("-------微信用户信息-----"+wxMpUser.toString());
        } catch (WxErrorException e) {
            log.error("【微信授权失败】");
        }
        String openId = wxMpOAuth2AccessToken.getOpenId();
        log.info("--------进入/userInfo------获取openId:"+openId);
        log.info("returnUrl==============" + returnUrl + "?openId=" + openId);

        log.info("-------判断数据库是否有这个openID，无则添加------");
        BuyerInfo buyer = buyerService.findByOpenId(openId);
        if (buyer == null){
            buyer =new BuyerInfo();
            buyer.setOpenid(openId);
            buyer.setHeadImgUrl(wxMpUser.getHeadImgUrl());
            buyer.setCity(wxMpUser.getCity());
            buyer.setNickName(wxMpUser.getNickname());
            buyer.setCreateTime(new Date());
            buyerService.save(buyer);
            log.info("------把buyer加入数据库成功------");
        }
        return "redirect:" + returnUrl + "?openId=" + openId;

    }


    @GetMapping("/logintest")
    public ResultVO<Object> login(@RequestParam("code") String code) {
        log.info("进入成功");
        log.info("code=" + code);
        String appId = "wx3c64c76a13e70aaf";
        String appsecret = "c5e7f39c0f35826f030dff6ad35c5499";

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appId + "&secret=" + appsecret + "&code=" + code + "&grant_type=authorization_code";
        RestTemplate rest = new RestTemplate();
        String req = rest.getForObject(url, String.class);
        log.info("req={}", req);
        return ResultVOUtil.success("234");
    }

    @GetMapping("/login1")
    public void login1() {
        log.info("进入成功");


    }
}

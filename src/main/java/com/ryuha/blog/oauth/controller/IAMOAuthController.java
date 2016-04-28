package com.ryuha.blog.oauth.controller;

import com.ryuha.blog.oauth.service.IAMOAuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Misaka on 2016-04-22.
 */

@Controller(value = "iamOAuthController")
@RequestMapping("/iamOAuth")
public class IAMOAuthController {
    @Resource(name = "iamOAuthService")
    public IAMOAuthService iamOAuthService;

    @Value("#{conf['iam.client_id']}")
    private String clientId;

    @Value("#{conf['iam.client_secret']}")
    private String clientSecret;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView load() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("client_id", clientId);
        modelAndView.setViewName("oauth/iam/index");

        return modelAndView;
    }

    // 1. Authorization Code
    @RequestMapping(value = "/receive/authorization/code", method = RequestMethod.GET)
    public RedirectView receiveAuthorizationCode(@RequestParam String code, RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/iamOAuth/receive/token");

        redirectAttributes.addFlashAttribute("type", "authorization");
        redirectAttributes.addFlashAttribute("code", code);
        redirectAttributes.addFlashAttribute("client_id", clientId);

        return redirectView;
    }

    // 2. Implicit Grant Flow
    @RequestMapping(value = "/receive/implicit/code", method = RequestMethod.GET)
    public RedirectView receiveImplicitGrantFlowToken(@RequestParam String code, RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/iamOAuth/receive/token");

        redirectAttributes.addFlashAttribute("type", "implicit");
        redirectAttributes.addFlashAttribute("code", code);
        redirectAttributes.addFlashAttribute("client_id", clientId);

        return redirectView;
    }

    // 3. Resource Owner Password Credentials Flow
    @RequestMapping(value = "/receive/resource/token", method = RequestMethod.POST)
    public @ResponseBody String receiveResourceOwnerPasswordCredentialsFlowToken(@RequestParam String client_id, @RequestParam String username,
                                                            @RequestParam String password, @RequestParam String scope) {

        String decodedUsername = iamOAuthService.decodeBase64(username);
        String decodedPassword = iamOAuthService.decodeBase64(password);
        String token = iamOAuthService.getTokenResource(client_id, clientSecret, decodedUsername, decodedPassword, scope);

        return token;
    }

    // 4. Client Credentials Grant Flow
    @RequestMapping(value = "/receive/client/token", method = RequestMethod.POST)
    public @ResponseBody String receiveClientCredentialsGrantFlowToken(@RequestParam String client_id, @RequestParam String scope) {
        String token = iamOAuthService.getTokenClient(client_id, clientSecret, scope);
        return token;
    }

    // receive Token
    @RequestMapping(value = "/receive/token", method = RequestMethod.GET)
    public ModelAndView receiveToken(HttpServletRequest request) {
        // redirectAttributes get Data from RequestContextUtils
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        String type = flashMap.get("type").toString();
        String code = flashMap.get("code").toString();
        String client_id = flashMap.get("client_id").toString();
        String token = iamOAuthService.getToken(code, client_id, clientSecret);

        ModelAndView modelAndView = new ModelAndView();
        if("authorization".equals(type)) {
            modelAndView.addObject("authorizationToken", token);

        } else if("implicit".equals(type)) {
            modelAndView.addObject("implicitToken", token);

        } else {
            // otherwise...
            ;
        }
        modelAndView.setViewName("oauth/iam/index");

        return modelAndView;
    }

    @RequestMapping(value = "/receive/tokenInfo", method = RequestMethod.POST)
    public @ResponseBody String receiveTokenInfo(@RequestParam String accessToken) {
        String tokenInfo = iamOAuthService.getTokenInfo(accessToken);
        return tokenInfo;
    }

    @RequestMapping(value = "/receive/refreshToken", method = RequestMethod.POST)
    public @ResponseBody String receiveRefreshToken(@RequestParam String client_id, @RequestParam String refresh_token) {
        String refreshToken = iamOAuthService.getRefreshToken(client_id, clientSecret, refresh_token);
        return refreshToken;
    }
}

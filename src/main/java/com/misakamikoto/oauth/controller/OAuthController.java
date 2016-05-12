package com.misakamikoto.oauth.controller;

import com.misakamikoto.oauth.service.IAMOAuthService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;

/**
 * Created by Misaka on 2016-04-22.
 */
@Controller(value = "oauthController")
@RequestMapping("/oauth")
public class OAuthController {
    /**
     * The Iam oauth service.
     */
    @Resource(name = "iamOAuthService")
    public IAMOAuthService iamOAuthService;

    @Value("#{secretConf['iam.client_id']}")
    private String clientId;

    @Value("#{secretConf['iam.client_secret']}")
    private String clientSecret;

    @Value("#{serverConf['server.ip']}")
    private String serverIp;

    @Value("#{serverConf['server.port']}")
    private String serverPort;

    /**
     * Load model and view.
     *
     * @return the model and view
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView load() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("client_id", clientId);
        modelAndView.setViewName("oauth/iam/index");

        return modelAndView;
    }

    /**
     * Create authorize popup model and view.
     *
     * @param uri the uri
     * @return the model and view
     */
    @RequestMapping(value = "/popup/authorization", method = RequestMethod.POST)
    public ModelAndView createAuthorizePopup(@RequestParam String uri) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("uri", uri);
        modelAndView.setViewName("oauth/iam/popup/authorize");

        return modelAndView;
    }

    /**
     * Receive authorization code string.
     *
     * @param code               the code
     * @param redirectAttributes the redirect attributes
     * @return the string
     */
    @RequestMapping(value = "/receive/authorization/code", method = RequestMethod.GET)
    public String receiveAuthorizationCode(@RequestParam String code, RedirectAttributes redirectAttributes) {

        String ip = serverIp + ":" + serverPort;

        redirectAttributes.addFlashAttribute("code", code);
        redirectAttributes.addFlashAttribute("client_id", clientId);

        return "redirect:" + ip + "/oauth/receive/token";
    }

    /**
     * Receive implicit grant flow token model and view.
     *
     * @param access_token the access token
     * @return the model and view
     */
    @RequestMapping(value = "/receive/implicit/token", method = RequestMethod.GET)
    public ModelAndView receiveImplicitGrantFlowToken(@RequestParam String access_token) {
        JSONObject token = new JSONObject();

        try {
            token.put("access_token", access_token);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("implicitToken", token);
        modelAndView.setViewName("/oauth/iam/index");

        return modelAndView;
    }

    /**
     * Receive resource owner password credentials flow token string.
     *
     * @param client_id the client id
     * @param username  the username
     * @param password  the password
     * @param scope     the scope
     * @return the string
     */
    @RequestMapping(value = "/receive/resource/token", method = RequestMethod.POST)
    public @ResponseBody String receiveResourceOwnerPasswordCredentialsFlowToken(@RequestParam String client_id,
                                                                                 @RequestParam String username,
                                                                                 @RequestParam String password,
                                                                                 @RequestParam String scope) {

        String decodedUsername = iamOAuthService.decodeBase64(username);
        String decodedPassword = iamOAuthService.decodeBase64(password);
        String token = iamOAuthService.getTokenResource(client_id, clientSecret, decodedUsername, decodedPassword, scope);

        return token;
    }

    /**
     * Receive client credentials grant flow token string.
     *
     * @param client_id the client id
     * @param scope     the scope
     * @return the string
     */
    @RequestMapping(value = "/receive/client/token", method = RequestMethod.POST)
    public @ResponseBody String receiveClientCredentialsGrantFlowToken(@RequestParam String client_id, @RequestParam String scope) {
        String token = iamOAuthService.getTokenClient(client_id, clientSecret, scope);
        return token;
    }

    /**
     * Receive token model and view.
     *
     * @param code      the code
     * @param client_id the client id
     * @return the model and view
     */
    @RequestMapping(value = "/receive/token", method = RequestMethod.GET)
    public ModelAndView receiveToken(@ModelAttribute("code") String code, @ModelAttribute("client_id") String client_id) {
        String token = iamOAuthService.getToken(code, client_id, clientSecret);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("authorizationToken", token);
        modelAndView.setViewName("oauth/iam/index");

        return modelAndView;
    }

    /**
     * Receive token info string.
     *
     * @param access_token the access token
     * @return the string
     */
    @RequestMapping(value = "/receive/tokenInfo", method = RequestMethod.POST)
    public @ResponseBody String receiveTokenInfo(@RequestParam String access_token) {
        String tokenInfo = iamOAuthService.getTokenInfo(access_token);
        return tokenInfo;
    }

    /**
     * Receive refresh token string.
     *
     * @param client_id     the client id
     * @param refresh_token the refresh token
     * @return the string
     */
    @RequestMapping(value = "/receive/refreshToken", method = RequestMethod.POST)
    public @ResponseBody String receiveRefreshToken(@RequestParam String client_id, @RequestParam String refresh_token) {
        String refreshToken = iamOAuthService.getRefreshToken(client_id, clientSecret, refresh_token);
        return refreshToken;
    }
}

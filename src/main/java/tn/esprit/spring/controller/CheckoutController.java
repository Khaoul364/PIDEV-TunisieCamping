package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tn.esprit.spring.entity.ChargeRequest;

@RequestMapping("api")
@Controller
public class CheckoutController {
    @Value("$pk_test_51NBjShAWJGqw0ShHxRLCzTXxaPV00TnWUZhXthGMB8S3aPYSVUapJPYN0p9IWsl32jLmaetpRMrk03JwB255qZGN00cSE01WJc")
    private String stripePublicKey;

    @RequestMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("amount", 50 * 100); // in cents
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.EUR);
        return "checkout";
    }
}

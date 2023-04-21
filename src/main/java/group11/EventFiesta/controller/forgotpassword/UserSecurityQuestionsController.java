package group11.EventFiesta.controller.forgotpassword;

import group11.EventFiesta.account.IState;
import group11.EventFiesta.account.forgotpassword.IForgotPassword;
import group11.EventFiesta.account.forgotpassword.securityquestion.SecretQuestionHandler;
import group11.EventFiesta.account.forgotpassword.securityquestion.VerifyEmailHandler;
import group11.EventFiesta.db.MySQLDBPersistence;
import group11.EventFiesta.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
public class UserSecurityQuestionsController {
    @GetMapping("/userSecurityQuestion")
    public String getSecurityQuestionPage(Model model)
    {
        model.addAttribute("user", new User());
        return "UserSecurityQuestion";
    }

    @PostMapping("/handleUserSecurityQuestion")
    public String handleSecurityQuestion(Model model, @ModelAttribute User user) throws Exception
    {
        int user_id;
        String statusMessage;
        String email = user.getEmail();
        Object[] params1 = new Object[]{"UserInfo", "*", "email", email};
        VerifyEmailHandler verifyEmailHandler = new VerifyEmailHandler(new MySQLDBPersistence(), params1);
        List<Map<String, Object>> result = verifyEmailHandler.validateEmail(user);
        if(result != null && result.size() > 0) {

            user_id = Integer.parseInt(result.get(0).get("user_id").toString());

            String securityQuestion = user.getSecurityQuestion();
            String securityAnswer = user.getSecurityAnswer();

            Object[] params2 = new Object[]{"UserSensitive", "*", "user_id", user_id};
            IForgotPassword secretQuestionHandler = new SecretQuestionHandler(new MySQLDBPersistence(), params2, securityQuestion, securityAnswer);
            IState state  = secretQuestionHandler.validate(user);
            model.addAttribute("statusMsg", state.getStatus());

            user.setUserId(user_id);

            if(state.getStatus().equals("Security Details validated successfully!"))
            {
                model.addAttribute("user", user);
                return "redirect:/userResetPassword?email="+email;
            }
            else
            {
                return "UserSecurityQuestion";
            }
        }
        else
        {
            model.addAttribute("statusMsg", "INVALID ACCOUNT");
            return "UserSecurityQuestion";
        }
    }
}

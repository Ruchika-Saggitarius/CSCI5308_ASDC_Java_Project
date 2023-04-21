package group11.EventFiesta.account.forgotpassword.securityquestion;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.account.IState;
import group11.EventFiesta.account.forgotpassword.IForgotPassword;
import group11.EventFiesta.model.Account;

import java.util.List;
import java.util.Map;

public class SecretQuestionHandler implements IForgotPassword {

    IDBPersistence idbPersistence;
    Object[] params;
    String securityQuestion;
    String securityAnswer;

    public SecretQuestionHandler(IDBPersistence idbPersistence, Object [] params, String securityQuestion, String securityAnswer) {
        this.idbPersistence = idbPersistence;
        this.params = params;
        this.securityQuestion = securityQuestion;
        this.securityAnswer = securityAnswer;
    }

    @Override
    public IState validate(Account account){
        List<Map<String, Object>> data = null;
        try {
            data = idbPersistence.loadData("getFromDBUsingWhere", params);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (data.size() > 0) {
            String secretQuestionFromDB = data.get(0).get("security_question").toString();
            String secretAnswerFromDB = data.get(0).get("security_answer").toString();

            if (secretQuestionFromDB.equals(securityQuestion)) {
                if (secretAnswerFromDB.equals(securityAnswer)) {
                    return new CorrectSecurityCredential(account);
                }
                else {
                    return new IncorrectSecurityCredential(account);
                }
            } else {
                return new IncorrectSecurityCredential(account);
            }
        }
        return new IncorrectSecurityCredential(account);
    }

}



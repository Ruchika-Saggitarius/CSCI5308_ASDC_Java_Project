package group11.EventFiesta.account.login.organizer;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.account.IState;
import group11.EventFiesta.model.Account;

import java.util.List;
import java.util.Map;

public class AccountCheckHandler extends LoginHandler {

    IDBPersistence idbPersistence;
    Object [] params;

    public AccountCheckHandler(IDBPersistence idbPersistence, Object[] params) {
        this.idbPersistence = idbPersistence;
        this.params = params;
    }

    @Override
    public IState execute(Account account) throws Exception {
        List<Map<String, Object>> data = idbPersistence.loadData("getFromDBUsingWhere", params);
        Integer accountId;
        if (data.size() > 0) {
            String accoundIdColumn = params[1].toString();
            accountId = Integer.parseInt(data.get(0).get(accoundIdColumn).toString());
            account.setAccountId(accountId);
            return nextHandler.execute(account);
        } else {
            return new InvalidAccount(account);
        }
    }
}

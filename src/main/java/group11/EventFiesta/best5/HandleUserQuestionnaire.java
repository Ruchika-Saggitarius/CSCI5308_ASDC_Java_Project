package group11.EventFiesta.best5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import group11.EventFiesta.model.UserEventQuestionnaire;

public class HandleUserQuestionnaire implements IHandleUserQuestionnaire {

    private UserEventQuestionnaire userEventQuestionnaire;
    List<GroupComponent> organizersList = new ArrayList<GroupComponent>();
    private IHelperForDB helperForDB = new HelperForDB();

    public HandleUserQuestionnaire(UserEventQuestionnaire userEventQuestionnaire) {
        this.userEventQuestionnaire = userEventQuestionnaire;
        init();
    }

    private void init() {
        List<Map<String, Object>> resultSet = helperForDB.getOrganizersFromDB(userEventQuestionnaire);
        setOrganizerServiceObject(resultSet);
    }

    public Map<String, List<GroupComponent>> getMapValuePairOfService() {
        Map<String, List<GroupComponent>> listOfAllOrganizers = new HashMap<String, List<GroupComponent>>();
        String[] servicesByUser = userEventQuestionnaire.getService().split(",");
        for (int j = 0; j < servicesByUser.length; j++) {
            List<GroupComponent> organizerOfferingSameService = new ArrayList<GroupComponent>();
            for (int i = 0; i < organizersList.size(); i++) {
                DecoratedOrganizerService service = (DecoratedOrganizerService) organizersList.get(i);
                if (servicesByUser[j].equalsIgnoreCase(service.serviceName)) {
                    organizerOfferingSameService.add(service);
                    listOfAllOrganizers.put(servicesByUser[j], organizerOfferingSameService);
                }
            }
        }
        return listOfAllOrganizers;
    }

    public void setOrganizerServiceObject(List<Map<String, Object>> resultSet) {
        for (int i = 0; i < resultSet.size(); i++) {
            OrganizerService organizerService = new OrganizerService();
            organizerService.orgranizerId = Integer.parseInt(resultSet.get(i).get("organizer_id").toString());
            organizerService.id = Integer.parseInt(resultSet.get(i).get("service_id").toString());
            organizerService.serviceName = resultSet.get(i).get("service_type").toString();
            organizerService.price = Double.parseDouble(resultSet.get(i).get("price").toString());
            // organizerService.rating =
            // Double.parseDouble(resultSet.get(i).get("rating").toString());

            organizerService.budget = (double) userEventQuestionnaire.getBudget();
            organizerService.headCount = (double) userEventQuestionnaire.getGuestCount();
            organizerService.dateTime = userEventQuestionnaire.getDateTime();
            organizerService.event = userEventQuestionnaire.getEvent();
            GroupComponent decoratedComponent = new DecoratedOrganizerService(organizerService, helperForDB);
            organizersList.add(decoratedComponent);
        }
    }

}

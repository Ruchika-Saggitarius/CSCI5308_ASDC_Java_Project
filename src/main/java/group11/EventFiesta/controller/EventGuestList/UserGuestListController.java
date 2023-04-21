package group11.EventFiesta.controller.EventGuestList;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.db.MySQLDBPersistence;
import group11.EventFiesta.event.GuestListHandler;
import group11.EventFiesta.model.Guest;
import group11.EventFiesta.model.User;
import group11.EventFiesta.model.UserGuestList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SessionAttributes({"user"})
@Controller
public class UserGuestListController {
    private IDBPersistence dbPersistence = new MySQLDBPersistence();

    @GetMapping("/user/{event_id}/guests")
    public String getGuestList(Model model, HttpServletRequest request, @PathVariable("event_id") int eventId) throws Exception {
        HttpSession session = request.getSession(false);
        int userId = (int) session.getAttribute("accountId");
        model.addAttribute("userId", userId);
        model.addAttribute("eventId", eventId);
        GuestListHandler glHandler = new GuestListHandler(dbPersistence);
        UserGuestList ugl = glHandler.loadGuestList(eventId);
        model.addAttribute("userGuestList", ugl);
        model.addAttribute("guest", new Guest());
        return "UserGuestList";
    }

    @PostMapping("/handleAddGuest/{user_id}/{event_id}")
    public String addGuest(Model model, @ModelAttribute Guest guest,
                           @PathVariable("event_id") int eventId,
                           @PathVariable("user_id") int userId) throws Exception {
        GuestListHandler glHandler = new GuestListHandler(dbPersistence);
        UserGuestList ugl = new UserGuestList();
//        User user = model.getAttribute("user");
        ugl.setUserId(userId);
        ugl.setEventId(eventId);
        ugl.addGuest(guest);
        glHandler.storeGuestList(ugl);
        model.addAttribute("userId", userId);
        model.addAttribute("eventId", eventId);
        ugl = glHandler.loadGuestList(eventId);
        model.addAttribute("userGuestList", ugl);
        model.addAttribute("guest", new Guest());
        return "UserGuestList";
    }

    @PostMapping("/handleUpdateGuest/{event_id}")
    public String updateGuest(Model model,
                              @ModelAttribute UserGuestList guestList,
                         @PathVariable("event_id") int eventId, @ModelAttribute("user") User user) throws Exception {
        GuestListHandler glHandler = new GuestListHandler(dbPersistence);
        glHandler.updateGuestList(guestList);
        guestList = glHandler.loadGuestList(eventId);
        model.addAttribute("userId", user.getUserId());
        model.addAttribute("eventId", eventId);
        model.addAttribute("userGuestList", guestList);
        model.addAttribute("guest", new Guest());
        return "UserGuestList";
    }
}

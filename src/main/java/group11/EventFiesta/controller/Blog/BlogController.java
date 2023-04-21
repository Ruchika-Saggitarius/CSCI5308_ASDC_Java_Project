package group11.EventFiesta.controller.Blog;

import group11.EventFiesta.db.MySQLDBPersistence;
import group11.EventFiesta.event.BlogHandler;
import group11.EventFiesta.model.Reviews;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BlogController
{
    @GetMapping("/blog")
    public String showBlog(Model model) throws Exception
    {
        Object[] params = new Object[]{"ServiceReviews", "*"};
        BlogHandler blogHandler = new BlogHandler(new MySQLDBPersistence(), params);
        List<Reviews> reviewsList = blogHandler.getReviewList();
        model.addAttribute("userReviews", reviewsList);
        return "Blog";
    }
}

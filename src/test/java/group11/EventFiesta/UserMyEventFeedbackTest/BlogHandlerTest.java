package group11.EventFiesta.UserMyEventFeedbackTest;

import group11.EventFiesta.db.IDBPersistence;
import group11.EventFiesta.event.BlogHandler;
import group11.EventFiesta.model.Reviews;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class BlogHandlerTest {

    @Test
    public void getReviewsListPassTest()
    {
        IDBPersistence mockDb = new MockUserMyEventFeedback();
        Object[] params = new Object[]{"ServiceReviews", "*"};
        BlogHandler blogHandler = new BlogHandler(mockDb, params);
        List<Reviews> reviewsList = blogHandler.getReviewList();

        Assertions.assertEquals(3, reviewsList.size());
    }

    @Test
    public void getReviewsListFailTest()
    {
        IDBPersistence mockDb = new MockUserMyEventFeedback();
        Object[] params = new Object[]{"ServiceReviews", "*"};
        BlogHandler blogHandler = new BlogHandler(mockDb, params);
        List<Reviews> reviewsList = blogHandler.getReviewList();

        Assertions.assertNotEquals(5, reviewsList.size());
    }
}

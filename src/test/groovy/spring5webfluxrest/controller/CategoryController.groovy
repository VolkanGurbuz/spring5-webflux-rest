import com.volkangurbuz.spring5webfluxrest.controller.CategoryController
import com.volkangurbuz.spring5webfluxrest.repositories.CategoryRepository
import jdk.nashorn.internal.runtime.Specialization
import org.springframework.test.web.reactive.server.WebTestClient
import spock.lang.Specification

import java.util.logging.Logger

class CategoryControllerTest extends Specification {

    WebTestClient webTestClient;
    CategoryRepository categoryRepository;
    CategoryController categoryController;
    private static final Logger log = Logger.getLogger(CategoryControllerTest.class);


    def "category list"() {
        def x = 1

        assert x == 2

    }


}




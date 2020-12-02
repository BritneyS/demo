import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class Service {

    fun getBooks(): Mono<List<BookDto>> {

    }
}
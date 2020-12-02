import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class Handler(
    private val bookService: Service
) {

    fun getBooks(request: ServerRequest): Mono<ServerResponse> {
        return bookService.getBooks()
            .flatMap(ServerResponse.ok()::bodyValue)
    }

}
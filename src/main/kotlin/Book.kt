import org.springframework.data.annotation.Id
import java.util.UUID

data class Book(
    @Id
    val id: String = UUID.randomUUID().toString(),
    val author: String,
    val title: String
)

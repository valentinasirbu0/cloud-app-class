package md.utm.cloudapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@SpringBootApplication
class CloudAppApplication

fun main(args: Array<String>) {
    runApplication<CloudAppApplication>(*args)
}

@Entity
@Table(name = "users")
data class User(
    @Id
    val id: Long,
    val name: String
)

@Repository
interface UserRepository : JpaRepository<User, Long>

@RestController
class UserController(private val userRepository: UserRepository) {

    @GetMapping("/users")
    fun getAllUsers(): List<User> = userRepository.findAll()
}

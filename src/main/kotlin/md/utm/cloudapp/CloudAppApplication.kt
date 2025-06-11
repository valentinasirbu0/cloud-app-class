package md.utm.cloudapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import jakarta.persistence.Entity // Changed from javax.persistence.Entity
import jakarta.persistence.Id     // Changed from javax.persistence.Id
import jakarta.persistence.Table  // Changed from javax.persistence.Table
import jakarta.persistence.GeneratedValue // You'll likely need this for auto-generating IDs
import jakarta.persistence.GenerationType // And this for ID generation strategy

@SpringBootApplication
class CloudAppApplication

fun main(args: Array<String>) {
    runApplication<CloudAppApplication>(*args)
}

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Recommended for auto-incrementing IDs
    val id: Long? = null, // Make ID nullable for new entities before they get an ID
    val name: String
)

@Repository
interface UserRepository : JpaRepository<User, Long>

@RestController
class UserController(private val userRepository: UserRepository) {

    @GetMapping("/users")
    fun getAllUsers(): List<User> = userRepository.findAll()
}
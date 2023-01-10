package com.exo.pomodoro
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.Document
import java.util.*

fun main() {
    // Connect to the MongoDB server
    val client: MongoClient = MongoClients.create("mongodb://localhost")
    val database: MongoDatabase = client.getDatabase("myDatabase")
    val usersCollection: MongoCollection<Document> = database.getCollection("users")
    val scanner = Scanner(System.`in`)
    while (true) {
        println("Welcome to our registration and login system!")
        println("1. Register")
        println("2. Login")
        println("3. Exit")
        val option = scanner.nextInt()

        when (option) {
            1 -> {
                println("Enter your username:")
                val username = scanner.next()
                println("Enter your password:")
                val password = scanner.next()
                val user = Document("username", username)
                    .append("password", password)
                usersCollection.insertOne(user)
                println("Thank you for registering, $username")
            }
            2 -> {
                println("Enter your username:")
                val username = scanner.next()
                println("Enter your password:")
                val password = scanner.next()
                val user = usersCollection.find(Document("username", username)).first()
                if (user != null && user["password"] == password) {
                    println("Welcome back, $username")
                } else {
                    println( "Invalid username or password")
                }
            }
            3 -> {
                println("Goodbye!")
                break
            }
            else -> {
                println("Invalid option, please try again")
            }
        }
    }
    // Close the MongoClient
    client.close()
}

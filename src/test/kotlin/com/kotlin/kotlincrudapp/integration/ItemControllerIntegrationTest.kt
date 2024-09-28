package com.kotlin.kotlincrudapp.integration

import com.fasterxml.jackson.databind.ObjectMapper
import com.kotlin.kotlincrudapp.model.Item
import com.kotlin.kotlincrudapp.model.ItemType
import com.kotlin.kotlincrudapp.repository.ItemRepository
import jakarta.transaction.Transactional
import org.flywaydb.core.Flyway
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import java.util.UUID.randomUUID
import javax.sql.DataSource
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@Transactional
class ItemControllerIntegrationTest () {

    companion object {
        @Container
        private val postgresContainer = createPostgresContainer()

        private fun createPostgresContainer(): PostgreSQLContainer<*> {
            return PostgreSQLContainer<Nothing>("postgres:16").apply {
                withDatabaseName("grocery")
                withUsername("postgres")
                withPassword("my123456")
            }
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgresContainer::getJdbcUrl)
            registry.add("spring.datasource.username", postgresContainer::getUsername)
            registry.add("spring.datasource.password", postgresContainer::getPassword)
        }
    }

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var itemRepository: ItemRepository

    @Autowired
    private lateinit var dataSource: DataSource

    private val BASE_URL = "/item"
    private val JSON_MEDIA_TYPE = MediaType.APPLICATION_JSON

    @BeforeEach
    fun setup() {
        val flyway = Flyway.configure()
            .dataSource(dataSource)
            .locations("classpath:db/migration")
            .cleanDisabled(false)
            .load()

        flyway.clean()
        flyway.migrate()
        itemRepository.deleteAll()
    }

    @Test
    fun testFindItemsReturnsAll() {
        val items = listOf(
            Item(null, "Bread", 2.5, ItemType.BAKERY),
            Item(null, "Cola", 3.5, ItemType.DRINKS)
        )
        itemRepository.saveAll(items)

        performGet("/")
            .andExpect(status().isOk)
            .andExpect(content().contentType(JSON_MEDIA_TYPE))
            .andExpect(jsonPath("$").isArray)
            .andExpect(jsonPath("$.length()").value(2))
            .andExpect(jsonPath("$[0].name").value("Bread"))
            .andExpect(jsonPath("$[0].price").value(2.5))
            .andExpect(jsonPath("$[0].type").value(ItemType.BAKERY.name))
            .andExpect(jsonPath("$[1].name").value("Cola"))
            .andExpect(jsonPath("$[1].price").value(3.5))
            .andExpect(jsonPath("$[1].type").value(ItemType.DRINKS.name))
    }

    @Test
    fun testFindItemByID() {
        val item1 = Item(null, "Milk", 4.0, ItemType.DAIRY)
        val savedItem = itemRepository.save(item1)

        performGet("/${savedItem.id}")
            .andExpect(status().isOk)
            .andExpect(content().contentType(JSON_MEDIA_TYPE))
            .andExpect(jsonPath("$.id").value(savedItem.id.toString()))
            .andExpect(jsonPath("$.name").value("Milk"))
            .andExpect(jsonPath("$.price").value(4.0))
            .andExpect(jsonPath("$.type").value(ItemType.DAIRY.name))
    }

    @Test
    fun testSaveNewItem() {
        val item = Item(null, "Orange Juice", 3.0, ItemType.DRINKS)

        performPost(BASE_URL, item)
            .andExpect(status().isOk)

        val savedItem = itemRepository.findAll().firstOrNull()
        assertNotNull(savedItem)
        assertEquals("Orange Juice", savedItem!!.name)
        assertEquals(3.0, savedItem.price)
        assertEquals(ItemType.DRINKS, savedItem.type)
    }

    @Test
    fun testNonExistentItemReturnsNotFound() {
        val nonExistentId = randomUUID().toString()
        performGet("/${nonExistentId}")
            .andExpect(status().isNotFound)
    }

    private fun performGet(url: String) = mockMvc.perform(get(url))

    private fun performPost(url: String, body: Any) = mockMvc.perform(
        post(url)
            .contentType(JSON_MEDIA_TYPE)
            .content(objectMapper.writeValueAsString(body))
    )

}

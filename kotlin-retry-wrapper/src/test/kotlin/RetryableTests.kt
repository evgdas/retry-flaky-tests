import core.retryableTest
import org.junit.jupiter.api.*
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS

@TestInstance(PER_CLASS)
@DisplayName("Дожатие тестов с помощью обертки над тестом")
class RetryableTest {

    private var tryCount = 0

    @BeforeAll
    fun beforeAll() {
        println("BeforeAll")
    }

    @AfterAll
    fun afterAll() {
        println("AfterAll")
    }

    @BeforeEach
    fun beforeEach() {
        println("BeforeEach")
    }

    @AfterEach
    fun afterEach() {
        println("AfterEach")
    }

    @Test
    fun failedTest() = retryableTest {
        println("testFailed")
        throw Exception("FAIL")
    }

    @Test
    fun successTest() = retryableTest {
        println("testSuccess")
    }

    @Test
    fun successAfterRetryTest() = retryableTest {
        if (tryCount < 2) {
            tryCount++
            println("testFailed")
            throw Exception("testFailed")
        }
        println("testSuccess")
    }

}
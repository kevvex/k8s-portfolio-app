import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers.shouldBe

import scala.language.postfixOps

class HelloTest extends AnyFunSuite {
  test("arithmetic") {
    val hello = "Hello"
    hello shouldBe "Hello"
  }
}

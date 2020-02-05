import AttoDeriver._
import atto._, Atto._
import cats.implicits._

case class Foo(int: Int, s: String)
object Foo {
  implicit val intParser = int
  implicit val stringParser = takeWhile(_.isLetter)
  implicit val fooParser: Parser[Foo] = AttoDeriver.gen[Foo]
}

object Example extends App {
  println(Foo.fooParser.parseOnly("123abc").either)
}

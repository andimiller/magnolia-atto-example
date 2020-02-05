import AttoDeriver._
import atto._, Atto._
import cats.implicits._

case class Foo(int: Int, s: String)
object Foo {
  implicit val intParser = int
  implicit val stringParser = takeWhile(_.isLetter)
  implicit val fooParser: Parser[Foo] = AttoDeriver.gen[Foo]
}

sealed trait Letter
case object A extends Letter
case object B extends Letter
case object C extends Letter
object Letter {
  implicit val aParser: Parser[A.type] = char('a').as(A)
  implicit val bParser: Parser[B.type] = char('b').as(B)
  implicit val cParser: Parser[C.type] = char('c').as(C)
  implicit val letterParser: Parser[Letter] = AttoDeriver.gen[Letter]
}

object Example extends App {
  println(Foo.fooParser.parseOnly("123abc").either)
  "abc".foreach { l =>
    println(Letter.letterParser.parseOnly(l.toString))
  }
}

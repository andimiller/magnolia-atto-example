import atto._
import cats.data.NonEmptyList
import cats.implicits._

import language.experimental.macros
import magnolia._

object AttoDeriver {
  type Typeclass[T] = Parser[T]

  def combine[T](ctx: CaseClass[Parser, T]): Parser[T] =
    ctx.parameters.toList.traverse(_.typeclass).map(ctx.rawConstruct)

  def dispatch[T](ctx: SealedTrait[Parser, T]): Parser[T] =
    NonEmptyList.fromListUnsafe(ctx.subtypes.toList.map(_.typeclass)).reduceK

  implicit def gen[T]: Parser[T] = macro Magnolia.gen[T]
}
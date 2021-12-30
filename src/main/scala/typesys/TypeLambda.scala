package typesys

object TypeLambda extends App {

  trait Mapper[A, +M[_]] {
    def mapWith[B](f: A => B): M[B]
  }

  object Mapper {
    implicit class SeqMapper[A](s: Seq[A]) extends Mapper[A, Seq] {
      override def mapWith[B](f: A => B): Seq[B] = s map f
    }

    implicit class OptionMapper[A](o: Option[A]) extends Mapper[A, Option] {
      override def mapWith[B](f: A => B): Option[B] = o map f
    }

    implicit class MapMapper[K, V1](m: Map[K, V1]) extends Mapper[V1, ({type L[T] = Map[K, T]})#L] {
      override def mapWith[V2](f: V1 => V2): Map[K, V2] = m.view.mapValues(f).toMap
    }
  }


  import Mapper._

  val seq = Seq(1, 2, 3).mapWith(a => a + 1)
  val opt = Option(1).mapWith(a => a + 1)
  val map = Map(1 -> 2, 3 -> 4).mapWith(a => a + 1)

  println(seq)
  println(opt)
  println(map)
}

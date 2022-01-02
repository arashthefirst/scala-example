package option

import java.util.NoSuchElementException

object OptionOps extends App {

  trait M[+A] {
    def map[B](f: A => B): B

    def getOrElse[B >: A](v: => B): B

    def orElse[B >: A](v: => Opt[B]): Opt[B]

  }

  abstract class Opt[+A] extends M[A] {
    override def map[B](f: A => B): B = f(get)

    override def getOrElse[B >: A](v: => B): B = if (this == Non) v else get

    override def orElse[B >: A](v: => Opt[B]): Opt[B] = if (this == Non) v else this


    def get: A
  }

  object Opt {
    def apply[A](x: A): Opt[A] = if (x == null) Non else Som(x)
  }

  case class Som[+T](t: T) extends Opt[T] {
    override def map[B](f: T => B): B = f(t)

    override def getOrElse[B >: T](v: => B): B = super.getOrElse(v)

    override def orElse[B >: T](v: => Opt[B]): Opt[B] = super.orElse(v)

    override def get: T = t

  }

  object Non extends Opt[Nothing] {
    override def get: Nothing = throw new NoSuchElementException("empty")
  }

  val s = Opt(10)
  println(s)
  println(s.get)
  println(Non.orElse(Som(12)))
  println(Non.getOrElse(89))
}

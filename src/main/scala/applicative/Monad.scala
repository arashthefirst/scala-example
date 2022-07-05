package applicative

object Monad extends App{
  trait Functor[F[_]] {
    def map[A, B]: F[A] => (A => B) => F[B]
  }

  trait Applicative[F[_]] extends Functor[F] {
    def pure[A]: A => F[A]
    def <*>[A, B]: F[A] => F[A => B] => F[B]
    def map2[A, B, C]: F[A] => F[B] => ((A, B) => C) => F[C]  = fa => fb => f => <*>(fb)(<*>(fa)(pure(f.curried)))
    def map3[A, B, C, D]: F[A] => F[B] => F[C] => ((A, B, C) => D) => F[D]= fa => fb => fc => f =>
      <*>(fc)(<*>(fb)( <*>(fa)(pure(f.curried))))
    def map4[A, B, C, D, E]: F[A] => F[B] => F[C] => F[D] => ((A, B, C, D) => E) => F[E]= fa => fb=> fc => fd => f =>
      <*>(fd)(<*>(fc)(<*>(fb)(<*>(fa)(pure(f.curried)))))
  }

  trait Monad[F[_]] extends Applicative[F] {
    def flatMap[A, B]: F[A] => (A => F[B]) => F[B]
    def unit[A] : A => F[A] = pure
    override def <*>[A, B]: F[A] => F[A => B] => F[B]= fa => ff =>    flatMap(fa)(a => map(ff)(f => f(a)))
  }

  val lst = new Monad[List] {
    override def flatMap[A, B]:List[A] => (A => List[B]) => List[B] = fa => f => fa.flatMap(f)
    override def pure[A]: A => List[A] = a => List(a)
    override def map[A, B]: List[A] => (A => B) => List[B] = fa => f => fa.map(f)
  }

  implicit class OpsImlicit[A, B](ff: List[A => B]) {
    def <*>(fa: List[A]): List[B] = lst.<*>(fa)(ff)
  }

  implicit class ListImplicit[A, B](fa: List[A]) {
    def <*>(ff: List[A => B]): List[B] = lst.<*>(fa)(ff)
  }




  val first = List(1)
  val second = List(1, 2)
  val third = List(1, 2, 3)
  val fourth = List(1, 2, 3, 4)
  //functor
  val funcRes = lst.map(fourth)(_ + 1)
  println(funcRes)


  // Applicatives with map2,3,4

  val functions = List[Int => Int => Int](a => b => a + b)

  val appRes = first <*> functions <*> second
  println(appRes)
  val m2Res = lst.map2(first)(second)(_ + _)
  val m3Res = lst.map3(first)(second)(third)(_ + _ + _)
  val m4Res = lst.map4(first)(second)(third)(fourth)(_ + _ + _ + _)
  println(m2Res)
  println(m3Res)
  println(m4Res)


  // Monad
  val pair = lst.flatMap(second)(i => lst.map(third)(j => (i,j)))
  println(pair)
}

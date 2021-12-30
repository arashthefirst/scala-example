package typesys

object UpperAndLowerBound extends App {

  class A {
    override def toString: String = s"( ${getClass.getSimpleName} )"
  }

  class B extends A

  class C extends B

  class D extends C

  def testBounds[T >: C <: B](t: T, f: T => String): T = { // means type T is C's super class and B's subclass (inclusive)
    println(t)
    t
  }

  //  val b = testBounds[D](new D, a => new String) will not compile
  val res = testBounds[B](new D, a => a.toString)
}

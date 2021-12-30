package typesys

object TypeProjection extends App {

  class A {
    class B

    object o
  }

  val a1 = new A
  val b1 = new a1.B
  val a2 = new A
  val b2 = new a2.B

  //  val b3 : a1.B = new a2.B won't compile in scala inner classes in different objects are not of the same type
  // to fix this use type projection

  val b3 : A#B = new a1.B // A#B is type projection, its common for all instances
  val b4 : A#B = new a2.B


}

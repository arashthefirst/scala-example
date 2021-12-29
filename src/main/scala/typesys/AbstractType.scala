package typesys

object AbstractType extends App {


  // abstract type
  trait T1 {
    type T // <== abstract type
    val a: T

    def printA: Unit = println(this.a)

  }

  class C1[A](i: A) extends T1 {
    type T = A
    val a: T = this.i

  }

  new C1(1).printA
  new C1("this is a test").printA
}

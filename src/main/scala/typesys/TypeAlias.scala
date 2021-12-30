package typesys

object TypeAlias extends App {

  class MyClass {
    override def toString: String = s"(${getClass.getSimpleName})"
  }

  // type alias
  type M = MyClass
  val m = new M

  type Func2 = (Int, Int) => Int
  val add: Func2 = (a, b) => a + b

}

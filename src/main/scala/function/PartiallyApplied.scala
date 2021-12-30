package function

object PartiallyApplied extends App {
  // inc and inc2 are partially applied functions (these are not partial function)
  def add(a: Int, b: Int): Int = a + b

  val inc = add(1, _) // this will return a Function1[Int,Int]
  println(inc(9))

  //or
  def add2(a: Int)(b: Int) = a + b

  val inc2 = add2(1) _
  println(inc2(9))



}

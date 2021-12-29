package typesys

object ContextBound extends App {
  /*
  the function below will be converted to
  def printWithCondition[T](f: T => Boolean)(implicit ev: List[T]): Unit

   */

  def printWithCondition[T: List](f: T => Boolean): Unit = {
    println(implicitly[List[T]].filter(f(_)))
  }

  implicit val a: List[Int] = List(1, 2, 3)
  printWithCondition[Int](_ < 2)

}

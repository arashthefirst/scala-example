package implicits

object OverloadIssue extends App {

  val intLst = List(1, 2, 3)
  val dblList = List(1.2, 2.3, 3.3)

  implicit case object IntList

  implicit case object DoubleList

  // list will loose their type, due to Type Erasure in Java, but implicit case objects will help to use the same name
  def printList(lst: List[Int])(implicit intType: IntList.type): Unit = println(lst.mkString(" - "))

  def printList(lst: List[Double])(implicit doubleType: DoubleList.type): Unit = println(lst.mkString(" - "))

  printList(intLst)
  printList(dblList)
}

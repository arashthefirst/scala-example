package recursion

import scala.annotation.tailrec

object RecursiveCollectionOps extends App {

  @tailrec
  def printList[T](lst: List[T]): Unit = lst match {
    case Nil =>
    case x :: xs =>
      println(x)
      printList(xs)
  }

  def findFirst[T](lst: List[T], search: T): Int = {

    @tailrec
    def loop(index: Int = 0, items: List[T] = lst): Int = items match {
      case Nil => -1
      case x :: _ if x == search => index
      case _ :: xs => loop(index + 1, xs)
    }

    loop()
  }

  //without tail rec will fail on bigger numbers
  def sum1(lst: List[Int]): Int = {
    if (lst == Nil) 0
    else lst.head + sum1(lst.tail)
  }

  // no stack overflow , but fr bigger numbers change acc and the return types o long
  def sum2(lst: List[Int]): Int = {
    @tailrec
    def loop(items: List[Int] = lst, acc: Int = 0): Int = items match {
      case Nil => acc
      case x :: xs => loop(xs, x + acc)
    }

    loop()
  }

  def reverse[T](lst: List[T]): List[T] = {
    @tailrec
    def loop(items: List[T] = lst, acc: List[T] = Nil): List[T] = items match {
      case Nil => acc
      case x :: xs => loop(xs, x +: acc)
    }
    loop()
  }


  def foldLeft[A, B](z: B)(list: List[A])(f: (A, B) => B): B = {
    @tailrec
    def loop(items: List[A] = list, acc: B = z): B = items match {
      case Nil => acc
      case x :: xs => loop(xs, f(x, acc))
    }
    loop()
  }


  def sum3(lst: List[Int]): Int = foldLeft(0)(lst)(_ + _)

  def product(lst: List[Int]): Int = foldLeft(1)(lst)(_ * _)

  def size[T](lst: List[T]): Int = foldLeft(0)(lst)((a, b) => 1 + b)

  printList(List(1, 2, 3))
  println(findFirst(List(1, 2, 3, 4), 0))
  println(findFirst(List(1, 2, 3, 4), 2))
  println(sum1(List(1, 2)))
  //    println(sum1((1 to 1000000).toList))  stack overflow error due to high number of stack calls
  println(sum2((1 to 1000000).toList)) // tail rec version works fine
  println(reverse(List(1, 2, 3)))
  println(foldLeft(0)(List(1, 2, 3))(_ + _))
  println(sum3(List(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)))
  println(product(List(1, 2, 3, 4)))
  println(size(List(1, 2, 3, 4, 5, 6, 7)))

}

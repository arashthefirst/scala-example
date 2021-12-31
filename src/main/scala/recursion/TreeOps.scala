package recursion

object TreeOps extends App {

  trait Tree[+T]

  case class Node[T](value: T, left: Tree[T] = Empty, right: Tree[T] = Empty) extends Tree[T]

  object Empty extends Tree[Nothing] {
    override def toString: String = s" ()"
  }

  val tree = Node(1, Node(2, Node(4)), Node(3, Node(5)))

  //conventional recursive function, non of the functions below are tail recursive, will work on it
  def sum(tree: Tree[Int]): Int = tree match {
    case Empty => 0
    case Node(v, l, r) => v + sum(l) + sum(r)
  }

  def max(tree: Tree[Int]): Int = {

    def loop(nodes: Tree[Int] = tree, max: Int = Int.MinValue): Int = nodes match {
      case Empty => max
      case Node(v, l, r) =>
        val leftMax = loop(l, if (v > max) v else max)
        val rightMax = loop(r, if (v > max) v else max)
        if (leftMax > rightMax) leftMax else rightMax
    }

    if (tree == Empty) throw new IllegalArgumentException("empty list")
    loop()

  }

  def map[A, B](tree: Tree[A])(f: A => B): Tree[B] = tree match {
    case Empty => Empty
    case Node(v, l, r) => Node(f(v), map(l)(f), map(r)(f))
  }

  println(sum(tree))
  println(max(tree))
  println(tree)

}
